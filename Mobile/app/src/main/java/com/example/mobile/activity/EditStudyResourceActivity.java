package com.example.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.model.StudyResource;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditStudyResourceActivity extends AppCompatActivity {

    EditText editTopic,editLinkUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_study_resource);
        Button btnAddStudyResource = findViewById(R.id.btnSaveAddStudyResource);
        editTopic = findViewById(R.id.editTopicResource);
        editLinkUrl = findViewById(R.id.editLinkUrl);
        TextView tvSubject=findViewById(R.id.textSubjectResource);

        //get du lieu tu list qua dialog
        String key =getIntent().getStringExtra("key");
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            return;
        }
        StudyResource studyResource = (StudyResource) bundle.get("objectStudyResource");
        editTopic.setText(studyResource.getTopic());
        editLinkUrl.setText(studyResource.getLink());
        tvSubject.setText(studyResource.getSubject());
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        btnAddStudyResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> item=new HashMap<>();
                item.put("link",editLinkUrl.getText().toString());
                item.put("subject",tvSubject.getText().toString());
                item.put("teacher",studyResource.getTeacher());
                item.put("topic",editTopic.getText().toString());
                db.collection("StudyResource").document(key)
                        .set(item)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getBaseContext(),"Edit Successfully!",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(EditStudyResourceActivity.this,ListStudyResourceActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                e.printStackTrace();
                            }
                        });
            }
        });
    }
}