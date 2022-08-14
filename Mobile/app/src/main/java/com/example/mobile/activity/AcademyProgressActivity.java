package com.example.mobile.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.model.ScoreDetails;
import com.example.mobile.model.Test;
import com.example.mobile.model.User;
import com.example.mobile.session.DocumentId;
import com.example.mobile.session.Session;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AcademyProgressActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private CombinedChart mChart;
    List<Integer> scoreList = new ArrayList<>();
    private String userIdSession = DocumentId.getDocumentId();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academy_progress);
        Toast.makeText(AcademyProgressActivity.this, userIdSession, Toast.LENGTH_LONG);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("ScoreDetails")
                .whereEqualTo("subjectName", "Biology")
                .whereEqualTo("studentid",userIdSession)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                scoreList.add(document.toObject(ScoreDetails.class).getScoreReceived());
                            }

                            mChart = (CombinedChart) findViewById(R.id.combinedChart);
                            mChart.getDescription().setEnabled(false);
                            mChart.setBackgroundColor(Color.WHITE);
                            mChart.setDrawGridBackground(false);
                            mChart.setDrawBarShadow(false);
                            mChart.setHighlightFullBarEnabled(false);
                            mChart.setOnChartValueSelectedListener(AcademyProgressActivity.this);

                            YAxis rightAxis = mChart.getAxisRight();
                            rightAxis.setDrawGridLines(false);
                            rightAxis.setAxisMinimum(0f);

                            YAxis leftAxis = mChart.getAxisLeft();
                            leftAxis.setDrawGridLines(false);
                            leftAxis.setAxisMinimum(0f);

                            final List<String> xLabel = new ArrayList<>();
                            xLabel.add("Fast Test 1");
                            xLabel.add("Middle Test 1");
                            xLabel.add("Final Test 1");
                            xLabel.add("Fast Test 2");
                            xLabel.add("Middle Test 2");
                            xLabel.add("Final Test 2");



                            XAxis xAxis = mChart.getXAxis();
                            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis.setAxisMinimum(0f);
                            xAxis.setGranularity(1f);
                            xAxis.setValueFormatter(new IAxisValueFormatter() {
                                @Override
                                public String getFormattedValue(float value, AxisBase axis) {
                                    return xLabel.get((int) value % xLabel.size());
                                }
                            });

                            CombinedData data = new CombinedData();
                            LineData lineDatas = new LineData();


                            LineData d = new LineData();

                            ArrayList<Entry> entries = new ArrayList<Entry>();

                            for (int index = 0; index < scoreList.size(); index++) {
                                entries.add(new Entry(index, scoreList.get(index)));
                            }



                            LineDataSet set = new LineDataSet(entries, "Request Ots approved");
                            set.setColor(Color.GREEN);
                            set.setLineWidth(2.5f);
                            set.setCircleColor(Color.GREEN);
                            set.setCircleRadius(5f);
                            set.setFillColor(Color.GREEN);
                            set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
                            set.setDrawValues(true);
                            set.setValueTextSize(10f);
                            set.setValueTextColor(Color.GREEN);

                            set.setAxisDependency(YAxis.AxisDependency.LEFT);
                            d.addDataSet(set);

                            lineDatas.addDataSet((ILineDataSet) set);

                            data.setData(lineDatas);

                            xAxis.setAxisMaximum(data.getXMax() + 0.25f);

                            mChart.setData(data);
                            mChart.invalidate();
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(this, "Value: "
                + e.getY()
                + ", index: "
                + h.getX()
                + ", DataSet index: "
                + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

}