package com.example.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mobile.R;

import java.util.ArrayList;
import java.util.List;

public class ViewScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);

        Spinner spSubject = findViewById(R.id.spSubject);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvClass = findViewById(R.id.tvClass);
        TextView tvSemester = findViewById(R.id.tvSemester);

        List<String> subjectAdapter = new ArrayList<String>() {
            {
                add("Math");
                add("English");
                add("Chemistry");
                add("Physical");
            }
        };
        spSubject.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, subjectAdapter));
        tvName.setText("John Smith");
        tvClass.setText("12A");
        tvSemester.setText("1");
    }
}