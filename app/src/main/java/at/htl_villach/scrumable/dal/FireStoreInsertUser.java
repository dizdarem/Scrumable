package at.htl_villach.scrumable.dal;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.util.List;

import at.htl_villach.scrumable.bll.User;
import at.htl_villach.scrumable.misc.OnReadWriteCompleted;

public class FireStoreInsertUser extends AsyncTask<List<User>, Void, String>
        implements OnSuccessListener<DocumentReference>, OnFailureListener, OnCompleteListener<DocumentReference> {
    private FirebaseFirestore db;
    private int cnt = 0, cntMax = 0;
    private OnReadWriteCompleted listener;

    public FireStoreInsertUser(OnReadWriteCompleted listener) throws Exception {
        this.listener = listener;
        initFirebase();
    }
    private void initFirebase() throws Exception {
        db = FirebaseFirestore.getInstance();
    }
    @Override
    protected String doInBackground(List<User>... paraUsers) {
        cntMax = paraUsers[0].size();
        FireStoreUser user;
        Gson gson = new Gson();
        try {
            for (User u : paraUsers[0]) {
                user = new FireStoreUser(gson.toJson(u));

                db.collection("cars")
                        .add(user)
                        .addOnSuccessListener(this)
                        .addOnFailureListener(this)
                        .addOnCompleteListener(this);
            }
        } catch (Exception e) {
            System.out.println("**********" + e.getMessage());
            listener.onWriteCompleted(e.getMessage());
            e.printStackTrace();
        }
        return "save completed";
    }

    @Override
    protected void onPostExecute(String message) {
        System.out.println("********onPost() write: " + message); //too early
    }

    @Override
    public void onSuccess(DocumentReference documentReference) {
        System.out.println("********** on Succ");
        cnt++;
        listener.onWriteCompleted("saved doc " + cnt + "/" + cntMax + " : " + documentReference.getId());
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        System.out.println("********** NOT Succ");
        listener.onWriteCompleted("write with problem: " + e.getMessage());
    }

    @Override
    public void onComplete(@NonNull Task<DocumentReference> task) {
        System.out.println("********** onComplete()");
    }
}
