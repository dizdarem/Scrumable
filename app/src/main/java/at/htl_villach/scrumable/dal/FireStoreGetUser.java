package at.htl_villach.scrumable.dal;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;

import at.htl_villach.scrumable.bll.User;
import at.htl_villach.scrumable.misc.OnReadWriteCompleted;

public class FireStoreGetUser extends AsyncTask<Void, Void, Void> implements OnFailureListener, OnCompleteListener<QuerySnapshot>, OnSuccessListener<QuerySnapshot> {

    private final OnReadWriteCompleted listener;
    private FirebaseFirestore db;
    private int cnt = 0;

    public FireStoreGetUser(OnReadWriteCompleted listener) {
        this.listener = listener;
        initFirebase();
    }

    private void initFirebase() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        db.collection("users").get().addOnSuccessListener(this).addOnFailureListener(this);
        return null;
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        System.out.println("************* NOT Succesfull");
        listener.onWriteCompleted("read with problem: " + e.getMessage());
    }


    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        System.out.println("********** onComplete()");
    }

    @Override
    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
        ArrayList<User> tmpList = new ArrayList<>();
        Gson gson = new Gson();
        for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
            cnt++;
            User u = gson.fromJson(String.valueOf(document.getData().get("info")), User.class);
            tmpList.add(u);
        }
        listener.onReadCompleted(tmpList);
        listener.onWriteCompleted("Succesfully loaded " + cnt + " entries");
    }
}