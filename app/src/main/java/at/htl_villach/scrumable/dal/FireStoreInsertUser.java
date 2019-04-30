package at.htl_villach.scrumable.dal;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import at.htl_villach.scrumable.bll.User;
import at.htl_villach.scrumable.misc.OnReadWriteCompleted;

public class FireStoreInsertUser extends AsyncTask<List<User>, Void, String> implements OnReadWriteCompleted {
    private FirebaseFirestore db;
    private int cnt = 0, cntMax = 0;
    private OnReadWriteCompleted listener;

    private ArrayList<User> arrUsers = new ArrayList<>();

    public FireStoreInsertUser(OnReadWriteCompleted listener) throws Exception {
        this.listener = listener;
        initFirebase();
    }

    private void initFirebase() throws Exception {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected String doInBackground(List<User>... paraUsers) {
        boolean unique = true;
        cntMax = paraUsers[0].size();
        FireStoreUser user;
        Gson gson = new Gson();

        FireStoreGetUser fsget = new FireStoreGetUser(this);
        fsget.execute();

        for (User u : arrUsers) {
            for (User u2 : paraUsers[0]) {
                if (u.getUsername() == u2.getUsername()) {
                    unique = false;
                }
            }
        }

        if(unique){
            try {
                for (User u : paraUsers[0]) {
                    user = new FireStoreUser(gson.toJson(u));

                    System.out.println("User JSON String: " + gson.toJson(u));

                    db.collection("users").document(u.getUsername())
                            .set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    System.out.println("******** Insert was a success");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    System.out.println(e.getStackTrace());
                                }
                            })
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    System.out.println("finished writing");
                                }
                            });
                }
            } catch (Exception e) {
                System.out.println("**********" + e.getMessage());
                listener.onWriteCompleted(e.getMessage());
                e.printStackTrace();
            }
        }

        return "save completed";
    }

    @Override
    public void onReadCompleted(ArrayList<User> collUsers) {
        this.arrUsers = collUsers;
    }

    @Override
    public void onWriteCompleted(String message) {
        System.out.println("Writing completed");
    }
}
