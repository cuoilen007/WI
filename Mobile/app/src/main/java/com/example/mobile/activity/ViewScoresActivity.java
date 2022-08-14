package com.example.mobile.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.fkfirebase.FkFireBase;
import com.example.mobile.model.ScoreDetails;
import com.example.mobile.model.Subject;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewScoresActivity extends AppCompatActivity {
    List<Subject> subjectList = new ArrayList<>();
    List<ScoreDetails> scoreDetailsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);

        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Title Bar
        actionBar.setTitle("View Score");
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);

        Spinner spSubject = findViewById(R.id.spSubject);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvClass = findViewById(R.id.tvClass);
        TextView tvFastTest1 = findViewById(R.id.tvFastTest1);
        TextView tvMiddleTest1 = findViewById(R.id.tvMiddleTest1);
        TextView tvFinalTest1 = findViewById(R.id.tvFinalTest1);
        TextView tvTotal1 = findViewById(R.id.tvTotal1);
        TextView tvFastTest2 = findViewById(R.id.tvFastTest2);
        TextView tvMiddleTest2 = findViewById(R.id.tvMiddleTest2);
        TextView tvFinalTest2 = findViewById(R.id.tvFinalTest2);
        TextView tvTotal2 = findViewById(R.id.tvTotal2);


        List<String> subjectAdapter = new ArrayList<String>() {
        };
        subjectAdapter.add("Select Subject");

        FirebaseFirestore db1 = FirebaseFirestore.getInstance();
        db1.collection("Subject")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                subjectAdapter.add(document.toObject(Subject.class).getSubjectName());
                                subjectList.add(document.toObject(Subject.class));
                            }

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        spSubject.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, subjectAdapter));
        tvName.setText("John Smith");
        tvClass.setText("12A");




        spSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FirebaseFirestore db2 = FirebaseFirestore.getInstance();
                db2.collection("ScoreDetails")
                        .whereEqualTo("SubjectName", spSubject.getSelectedItem().toString())
                        .whereEqualTo("studentid","2")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        scoreDetailsList.add(document.toObject(ScoreDetails.class));
                                    }

                                    for(ScoreDetails score : scoreDetailsList){
                                        if(score.getTestname().equals("fast-test1")){
                                            tvFastTest1.setText(String.valueOf(score.getScoreReceived()));
                                        }
                                        if(score.getTestname().equals("middle-test1")){
                                            tvMiddleTest1.setText(String.valueOf(score.getScoreReceived()));
                                        }
                                        if(score.getTestname().equals("final-test1")){
                                            tvFinalTest1.setText(String.valueOf(score.getScoreReceived()));
                                        }
                                        if(score.getTestname().equals("fast-test2")){
                                            tvFastTest2.setText(String.valueOf(score.getScoreReceived()));
                                        }
                                        if(score.getTestname().equals("middle-test2")){
                                            tvMiddleTest2.setText(String.valueOf(score.getScoreReceived()));
                                        }
                                        if(score.getTestname().equals("final-test2")){
                                            tvFinalTest2.setText(String.valueOf(score.getScoreReceived()));
                                        }
                                    }

                                } else {
                                    Log.w(TAG, "Error getting documents.", task.getException());
                                }
                            }
                        });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });


        //chart



    }
    //back
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return true;
    }


}