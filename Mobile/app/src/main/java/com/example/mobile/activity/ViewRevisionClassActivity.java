package com.example.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;

import com.example.mobile.R;
import com.example.mobile.adapter.ViewRevisionClassAdapter;
import com.example.mobile.model.RevisionClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewRevisionClassActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<RevisionClass> revisionClassList = new ArrayList<>();
    ViewRevisionClassAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_revision_class);


        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Title Bar
        actionBar.setTitle("");
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);

        recyclerView=findViewById(R.id.list_view_revision_class);
        getList();
        //GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new ViewRevisionClassAdapter(revisionClassList,this);
        recyclerView.setAdapter(adapter);
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