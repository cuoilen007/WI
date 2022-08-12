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
import com.example.mobile.model.User;
import com.example.mobile.session.Session;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddStudyResourceActivity extends AppCompatActivity {

    EditText editTopic,editLinkUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_study_resource);
        Button btnAddStudyResource = findViewById(R.id.btnSaveAddStudyResource);
        editTopic = findViewById(R.id.editTopicResource);
        editLinkUrl = findViewById(R.id.editLinkUrl);
        TextView tvSubject=findViewById(R.id.textSubjectResource);
        String subject=tvSubject.getText().toString();


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference reference=db.collection("StudyResource");

        btnAddStudyResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> item=new HashMap<>();
                item.put("link",editLinkUrl.getText().toString());
                item.put("subject",subject);
                User user= (User) Session.getSession();
                if (user.getCategoryUser().equals("TEACHER")){
                    item.put("teacher",user.getFirstName()+" "+user.getLastName());
                    item.put("topic",editTopic.getText().toString());
                    reference.add(item).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(AddStudyResourceActivity.this,"Add Success! DocumentSnapshot added with ID: " + documentReference.getId(),Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(AddStudyResourceActivity.this,ListStudyResourceActivity.class);
                            startActivity(intent);
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddStudyResourceActivity.this,"Add Fail!",Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                }

        });
    }
}