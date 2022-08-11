package com.example.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mobile.R;

public class TeacherMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_menu);

        Button btnAddStudyResources = findViewById(R.id.btnAddStudyResources);
        btnAddStudyResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherMenuActivity.this, AddStudyResourceActivity.class);
                startActivity(intent);
            }
        });
    }
}