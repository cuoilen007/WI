package com.example.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobile.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ContactusActivity extends AppCompatActivity {

    EditText txtContactName,txtContactEmail,txtContactPhone,txtContactMessage;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        txtContactName=findViewById(R.id.txtContactName);
        txtContactEmail=findViewById(R.id.txtContactEmail);
        txtContactPhone=findViewById(R.id.txtContactPhone);
        txtContactMessage=findViewById(R.id.txtContactMessage);
        btnSend=findViewById(R.id.btnSendContact);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> item = new HashMap<>();
                item.put("name", txtContactName.getText().toString());
                item.put("email", txtContactEmail.getText().toString());
                item.put("phone", txtContactPhone.getText().toString());
                item.put("message", txtContactMessage.getText().toString());
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference reference = db.collection("Contact");
                reference.add(item).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ContactusActivity.this);
                        builder.setIcon(R.drawable.ic_baseline_add_home_work_24);
                        builder.setMessage("Send Successfully! We will to contact as soon as possible. Thank you! ");
                        builder.create();
                        builder.show();
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ContactusActivity.this, "Send Failed! Please you send again!", Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        });
    }

}