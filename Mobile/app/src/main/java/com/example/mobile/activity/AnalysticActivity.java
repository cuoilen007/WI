package com.example.mobile.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mobile.model.ScoreDetails;
import com.example.mobile.session.DocumentId;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import com.example.mobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AnalysticActivity extends AppCompatActivity {
    Double Biology,Maths,Physics,English,Chemistry,Geography,Literature,History;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analystic);
        BarChart chart = findViewById(R.id.barchart);
        Biology =0.0;
        Maths=0.0;Physics=0.0;English=0.0;Chemistry=0.0;Geography=0.0;Literature=0.0;History=0.0;


        List<ScoreDetails> scoreDetailsList = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("ScoreDetails")
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
                                    switch (score.getSubjectName()){
                                        case "Biology":
                                            if(score.getTestname().equals("fast-test1")){
                                                Biology = Biology + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test1")){
                                                Biology = Biology + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test1")){
                                                Biology = Biology + score.getScoreReceived()*0.3;
                                            }
                                            if(score.getTestname().equals("fast-test2")){
                                                Biology = Biology + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test2")){
                                                Biology = Biology + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test2")){
                                                Biology = Biology + score.getScoreReceived()*0.3;
                                            }
                                            break;
                                        case "Maths":
                                            if(score.getTestname().equals("fast-test1")){
                                                Maths = Maths + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test1")){
                                                Maths = Maths + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test1")){
                                                Maths = Maths + score.getScoreReceived()*0.3;
                                            }
                                            if(score.getTestname().equals("fast-test2")){
                                                Maths = Maths + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test2")){
                                                Maths = Maths + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test2")){
                                                Maths = Maths + score.getScoreReceived()*0.3;
                                            }
                                            break;
                                        case "Physics":
                                            if(score.getTestname().equals("fast-test1")){
                                                Physics = Physics + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test1")){
                                                Physics = Physics + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test1")){
                                                Physics = Physics + score.getScoreReceived()*0.3;
                                            }
                                            if(score.getTestname().equals("fast-test2")){
                                                Physics = Physics + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test2")){
                                                Physics = Physics + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test2")){
                                                Physics = Physics + score.getScoreReceived()*0.3;
                                            }
                                            break;
                                        case "English":
                                            if(score.getTestname().equals("fast-test1")){
                                                English = English + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test1")){
                                                English = English + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test1")){
                                                English = English + score.getScoreReceived()*0.3;
                                            }
                                            if(score.getTestname().equals("fast-test2")){
                                                English = English + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test2")){
                                                English = English + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test2")){
                                                English = English + score.getScoreReceived()*0.3;
                                            }
                                            break;
                                        case "Chemistry":
                                            if(score.getTestname().equals("fast-test1")){
                                                Chemistry = Chemistry + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test1")){
                                                Chemistry = Chemistry + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test1")){
                                                Chemistry = Chemistry + score.getScoreReceived()*0.3;
                                            }
                                            if(score.getTestname().equals("fast-test2")){
                                                Chemistry = Chemistry + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test2")){
                                                Chemistry = Chemistry + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test2")){
                                                Chemistry = Chemistry + score.getScoreReceived()*0.3;
                                            }
                                            break;
                                        case "Geography":
                                            if(score.getTestname().equals("fast-test1")){
                                                Geography = Geography + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test1")){
                                                Geography = Geography + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test1")){
                                                Geography = Geography + score.getScoreReceived()*0.3;
                                            }
                                            if(score.getTestname().equals("fast-test2")){
                                                Geography = Geography + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test2")){
                                                Geography = Geography + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test2")){
                                                Geography = Geography + score.getScoreReceived()*0.3;
                                            }
                                            break;
                                        case "Literature":
                                            if(score.getTestname().equals("fast-test1")){
                                                Literature = Literature + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test1")){
                                                Literature = Literature + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test1")){
                                                Literature = Literature + score.getScoreReceived()*0.3;
                                            }
                                            if(score.getTestname().equals("fast-test2")){
                                                Literature = Literature + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test2")){
                                                Literature = Literature + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test2")){
                                                Literature = Literature + score.getScoreReceived()*0.3;
                                            }
                                            break;
                                        case "History":
                                            if(score.getTestname().equals("fast-test1")){
                                                History = History + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test1")){
                                                History = History + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test1")){
                                                History = History + score.getScoreReceived()*0.3;
                                            }
                                            if(score.getTestname().equals("fast-test2")){
                                                History = History + score.getScoreReceived()*0.05;
                                            }
                                            if(score.getTestname().equals("middle-test2")){
                                                History = History + score.getScoreReceived()*0.15;
                                            }
                                            if(score.getTestname().equals("final-test2")){
                                                History = History + score.getScoreReceived()*0.3;
                                            }
                                            break;
                                    }

                                }
                            }

                            ArrayList<BarEntry> barEntries = new ArrayList<>();
                            barEntries.add(new BarEntry(0, Biology.intValue()));
                            barEntries.add(new BarEntry(1, Maths.intValue()));
                            barEntries.add(new BarEntry(2, Physics.intValue()));
                            barEntries.add(new BarEntry(3, English.intValue()));
                            barEntries.add(new BarEntry(4, Chemistry.intValue()));
                            barEntries.add(new BarEntry(5, Geography.intValue()));
                            barEntries.add(new BarEntry(6, Literature.intValue()));
                            barEntries.add(new BarEntry(7, History.intValue()));

                            BarDataSet bardataset = new BarDataSet(barEntries, "Subject");

                            BarData data = new BarData(bardataset);
                            bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                            chart.animateY(1000);
                            chart.setData(data);

                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });


    }
}

