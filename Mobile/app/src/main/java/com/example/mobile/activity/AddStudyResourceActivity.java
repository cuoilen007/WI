package com.example.mobile.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.model.StudyResource;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddStudyResourceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_study_resource);

        Button btnAddStudyResource = findViewById(R.id.btnSaveAddStudyResource);
        EditText editName = findViewById(R.id.editName);
        EditText editLinkUrl = findViewById(R.id.editLinkUrl);

        String name = editName.getText().toString();
        String linkUrl = editLinkUrl.getText().toString();

        // Create a new user with a first, middle, and last name
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("link", "asd");
        hashMap.put("name", "asd");
        hashMap.put("subjectId", "asd");

        StudyResource studyResource = new StudyResource(linkUrl,name,"asd" );

        btnAddStudyResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("StudyResource");
                myRef.setValue("Hello World!! Im Rival :)");
                myRef.push();
            }
        });
    }
}