package com.example.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.example.mobile.R;
import com.example.mobile.adapter.ScoreAdapter;
import com.example.mobile.fkfirebase.FkFireBase;
import com.example.mobile.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListScoreActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    ScoreAdapter adapter;
    ArrayList<User> list;
    User user=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_score);

        recyclerView = findViewById(R.id.user_list);
        database = FirebaseDatabase.getInstance().getReference("User");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new ScoreAdapter(this, list);
        recyclerView.setAdapter(adapter);
        User user1 = new User("test634");
        FkFireBase<User> _userRepo = new FkFireBase<>(new User());
        _userRepo.update(user1,"6upfyxOrlUtNDyv5a0mj");
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    }
}