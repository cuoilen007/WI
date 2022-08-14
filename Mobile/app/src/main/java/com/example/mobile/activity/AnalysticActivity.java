package com.example.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import com.example.mobile.R;
public class AnalysticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analystic);
        BarChart chart = findViewById(R.id.barchart);

        ArrayList<BarEntry> barEntries=new ArrayList<>();
        barEntries.add(new BarEntry(0,20));
        barEntries.add(new BarEntry(1,40));




        BarDataSet bardataset = new BarDataSet(barEntries, "Subject");
        chart.animateY(1000);
        BarData data = new BarData(bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
    }
}

