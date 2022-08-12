package com.example.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobile.R;
import com.example.mobile.adapter.RevisionClassAdapter;
import com.example.mobile.model.RevisionClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListRevisionClassActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<RevisionClass> revisionClassList = new ArrayList<>();
    RevisionClassAdapter adapter;
    Button btnCreate;
    List<String> keyList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_revision_class);
        getActionBar().hide();
        recyclerView=findViewById(R.id.list_extraClass);
        getList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter=new RevisionClassAdapter(revisionClassList,keyList,this);
        recyclerView.setAdapter(adapter);
        btnCreate=findViewById(R.id.btnCreateExtraClass);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListRevisionClassActivity.this, AddRevisionClassActivity.class);
                startActivity(intent);
            }
        });
    }
    public void getList(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("RevisionClass")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                RevisionClass revisionClass =document.toObject(RevisionClass.class);
                                revisionClassList.add(revisionClass);
                                keyList.add(document.getId());
                            }
                            adapter.notifyDataSetChanged();

                        } else {
                            //
                        }
                    }
                });
    }


}