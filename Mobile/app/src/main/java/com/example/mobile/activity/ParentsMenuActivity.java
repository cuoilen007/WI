package com.example.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobile.R;

public class ParentsMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_menu);
        Button btnViewScore = findViewById(R.id.btnViewScore);
        Button btnAcademyProgress = findViewById(R.id.btnAcademyProgress);
        Button btnHelplines = findViewById(R.id.btnHelplines);

        btnViewScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentsMenuActivity.this, ViewScoresActivity.class);
                startActivity(intent);
            }
        });

        btnAcademyProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParentsMenuActivity.this, AcademyProgressActivity.class);
                startActivity(intent);
            }
        });
        btnHelplines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ParentsMenuActivity.this, ContactusActivity.class);
                startActivity(intent);
            }
        });
    }
}