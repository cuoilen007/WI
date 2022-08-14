package com.example.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mobile.R;
import com.example.mobile.adapter.StudyResourceAdapter;
import com.example.mobile.adapter.ViewStudyResourceAdapter;
import com.example.mobile.model.StudyResource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewStudyResourceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<StudyResource> studyResources = new ArrayList<>();
    ViewStudyResourceAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_study_resource);

        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Title Bar
        actionBar.setTitle("");
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);

        recyclerView=findViewById(R.id.list_view_study_resource);
        getList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ViewStudyResourceAdapter(studyResources,this);
        recyclerView.setAdapter(adapter);

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
                            }
                            adapter.notifyDataSetChanged();

                        } else {
                            //
                        }
                    }
                });
    }
    //back
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return true;
    }
}