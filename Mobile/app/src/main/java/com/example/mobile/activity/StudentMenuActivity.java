package com.example.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mobile.R;

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
                Intent intent = new Intent(StudentMenuActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnAcademyProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnStudyResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnRevisionClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnHelplines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}