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
import com.example.mobile.adapter.StudyResourceAdapter;
import com.example.mobile.model.StudyResource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ListStudyResourceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<StudyResource> studyResources = new ArrayList<>();
    List<String> keySet=new ArrayList<>();
    StudyResourceAdapter adapter;
    Button btnCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_study_resource);
        getActionBar().hide();
        recyclerView=findViewById(R.id.list_StudyResource);
        getList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter=new StudyResourceAdapter(studyResources,keySet,this);
        recyclerView.setAdapter(adapter);

        btnCreate=findViewById(R.id.btnCreateStudyResource);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListStudyResourceActivity.this,AddStudyResourceActivity.class);
                startActivity(intent);
            }
        });


    }
    public void getList(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("StudyResource")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                StudyResource studyResource=document.toObject(StudyResource.class);
                                studyResources.add(studyResource);
                                keySet.add(document.getId());

                            }
                            adapter.notifyDataSetChanged();

                        } else {
                            //
                        }
                    }
                });
    }
}