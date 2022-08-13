package com.example.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mobile.R;
import com.example.mobile.adapter.ViewRevisionClassAdapter;
import com.example.mobile.adapter.ViewStudyResourceAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        Button btnViewScore = findViewById(R.id.btnViewScore);
        Button btnAcademyProgress = findViewById(R.id.btnAcademyProgress);
        Button btnStudyResources = findViewById(R.id.btnStudyResources);
        Button btnRevisionClasses = findViewById(R.id.btnRevisionClasses);
        Button btnHelplines = findViewById(R.id.btnHelplines);

        btnViewScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentMenuActivity.this, ViewScoresActivity.class);
                startActivity(intent);
            }
        });

        btnAcademyProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMenuActivity.this, AcademyProgressActivity.class);
                startActivity(intent);
            }
        });

        btnStudyResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMenuActivity.this, ViewStudyResourceActivity.class);
                startActivity(intent);
            }
        });

        btnRevisionClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMenuActivity.this, ViewRevisionClassActivity.class);
                startActivity(intent);
            }
        });

        btnHelplines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMenuActivity.this, ContactusActivity.class);
                startActivity(intent);
            }
        });
    }
}