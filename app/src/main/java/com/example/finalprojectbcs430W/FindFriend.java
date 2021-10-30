package com.example.finalprojectbcs430W;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FindFriend extends AppCompatActivity {

    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter adapter;

    private FirebaseFirestore db;

    public static final int SHOW_SUB_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewFindFriend);
        adapter = new UserAdapter(userList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        retriveData();
    }

    private void retriveData()
    {
        db = FirebaseFirestore.getInstance();
        db.collection("user").get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    userList.clear();
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                        User user;

                        user = new User(documentSnapshot.getString("name"), documentSnapshot.getString("Preferred Workout Time"));
                        //TO DO: Above, Preferred Workout Time has to be changed to email to match with logic. But there is no email as a field for
                        //each user, hence have to add that first.
                        userList.add(user);

                        adapter.notifyDataSetChanged();
                        Log.d("MYDEBUG", documentSnapshot.getId()+"=>" + documentSnapshot.getData());
                    }
                }
            }
        });
    }
}