package com.example.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobile.R;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class FeedbackActivity extends AppCompatActivity {
    EditText txtName, txtMail, txtSubject, txtMess;
    Button btnSendMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        txtName = findViewById(R.id.fbName);
        txtSubject = findViewById(R.id.fbSubject);
        txtMess = findViewById(R.id.fbMessage);
        btnSendMail = findViewById(R.id.btnSendMail);

        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"wi.fpt.techwiz@gmail.com"}); // Get Mail
                //
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject: " +txtSubject.getText().toString().trim()); // Subject
                intent.putExtra(Intent.EXTRA_TEXT,
                        "Name: "+txtName.getText().toString().trim()+"\nMessage: "+txtMess.getText().toString().trim()); // Name
                try {
                    if(txtName.getText().toString().trim().length()==0 || txtSubject.getText().toString().trim().length()==0 || txtMess.getText().toString().trim().length()==0){
                        Toast.makeText(getApplicationContext(), "Invalid send mail, please enter in full on the input bar", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(Intent.createChooser(intent, "Other options...")); // Chooser
                    }
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}


