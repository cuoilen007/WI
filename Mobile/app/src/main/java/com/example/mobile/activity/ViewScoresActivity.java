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
import com.example.mobile.model.User;
import com.example.mobile.session.DocumentId;
import com.example.mobile.session.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewScoresActivity extends AppCompatActivity {
    TextView tvFastTest1;
    TextView tvMiddleTest1;
    TextView tvFinalTest1;
    TextView tvTotal1;
    TextView tvFastTest2 ;
    TextView tvMiddleTest2;
    TextView tvFinalTest2;
    TextView tvTotal2;
    Spinner spSubject;
    Integer countSem1;
    Integer countSem2;
    Double totalScoreSem1;
    Double totalScoreSem2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);

        tvFastTest1 = findViewById(R.id.tvFastTest1);
        tvMiddleTest1 = findViewById(R.id.tvMiddleTest1);
        tvFinalTest1 = findViewById(R.id.tvFinalTest1);
        tvTotal1 = findViewById(R.id.tvTotal1);
        tvFastTest2 = findViewById(R.id.tvFastTest2);
        tvMiddleTest2 = findViewById(R.id.tvMiddleTest2);
        tvFinalTest2 = findViewById(R.id.tvFinalTest2);
        tvTotal2 = findViewById(R.id.tvTotal2);
        spSubject = findViewById(R.id.spSubject);

        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Title Bar
        actionBar.setTitle("View Score");
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvClass = findViewById(R.id.tvClass);
        tvName.setText(((User)Session.getSession()).getFirstName() + " " +((User)Session.getSession()).getLastName());
        tvClass.setText(((User)Session.getSession()).getClassed());

        List<String> subjectAdapter = new ArrayList<String>() {
        };
        subjectAdapter.add("Select Subject");
        subjectAdapter.add("Biology");
        subjectAdapter.add("Maths");
        subjectAdapter.add("Physics");
        subjectAdapter.add("English");
        subjectAdapter.add("Chemistry");
        subjectAdapter.add("Geography");
        subjectAdapter.add("Literature");
        subjectAdapter.add("History");


        spSubject.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, subjectAdapter));

        spSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getScore(subjectAdapter.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });
    }

    public void getScore(String subject){
        tvFastTest1 = findViewById(R.id.tvFastTest1);
        tvMiddleTest1 = findViewById(R.id.tvMiddleTest1);
        tvFinalTest1 = findViewById(R.id.tvFinalTest1);
        tvTotal1 = findViewById(R.id.tvTotal1);
        tvFastTest2 = findViewById(R.id.tvFastTest2);
        tvMiddleTest2 = findViewById(R.id.tvMiddleTest2);
        tvFinalTest2 = findViewById(R.id.tvFinalTest2);
        tvTotal2 = findViewById(R.id.tvTotal2);
        spSubject = findViewById(R.id.spSubject);
        countSem1 = 0;
        countSem2 = 0;
        totalScoreSem1 =0.0;
        totalScoreSem2 =0.0;


        List<ScoreDetails> scoreDetailsList = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("ScoreDetails")
                .whereEqualTo("subjectName", subject)
                .whereEqualTo("studentid", DocumentId.getDocumentId())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                scoreDetailsList.add(document.toObject(ScoreDetails.class));
                            }
                            if(scoreDetailsList.size()!=0){
                                for(ScoreDetails score : scoreDetailsList){
                                    if(score.getTestname().equals("fast-test1")){
                                        tvFastTest1.setText(String.valueOf(score.getScoreReceived()));
                                        totalScoreSem1 = score.getScoreReceived()*0.1;
                                        countSem1++;
                                    }
                                    if(score.getTestname().equals("middle-test1")){
                                        tvMiddleTest1.setText(String.valueOf(score.getScoreReceived()));
                                        totalScoreSem1 = totalScoreSem1 + score.getScoreReceived()*0.3;
                                        countSem1++;
                                    }
                                    if(score.getTestname().equals("final-test1")){
                                        tvFinalTest1.setText(String.valueOf(score.getScoreReceived()));
                                        totalScoreSem1 = totalScoreSem1 + score.getScoreReceived()*0.6;
                                        countSem1++;
                                    }
                                    if(score.getTestname().equals("fast-test2")){
                                        tvFastTest2.setText(String.valueOf(score.getScoreReceived()));
                                        totalScoreSem2 = score.getScoreReceived()*0.1;
                                        countSem2++;
                                    }
                                    if(score.getTestname().equals("middle-test2")){
                                        tvMiddleTest2.setText(String.valueOf(score.getScoreReceived()));
                                        totalScoreSem2 = totalScoreSem2 + score.getScoreReceived()*0.3;
                                        countSem2++;
                                    }
                                    if(score.getTestname().equals("final-test2")){
                                        tvFinalTest2.setText(String.valueOf(score.getScoreReceived()));
                                        totalScoreSem2 = totalScoreSem2 + score.getScoreReceived()*0.6;
                                        countSem2++;
                                    }
                                }
                                if(countSem1 == 3){
                                    tvTotal1.setText(String.valueOf(totalScoreSem1));
                                }else{
                                    tvTotal1.setText("");
                                }

                                if(countSem2== 3){
                                    tvTotal2.setText(String.valueOf(totalScoreSem2));
                                }else{
                                    tvTotal2.setText("");
                                }

                            }else{
                                tvFastTest1.setText("");
                                tvMiddleTest1.setText("");
                                tvFinalTest1.setText("");
                                tvFastTest2.setText("");
                                tvMiddleTest2.setText("");
                                tvFinalTest2.setText("");
                                tvTotal1.setText("");
                                tvTotal2.setText("");
                            }

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }


}