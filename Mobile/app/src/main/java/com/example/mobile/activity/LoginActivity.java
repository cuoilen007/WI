
package com.example.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile.enumm.Roles;
import com.example.mobile.fkfirebase.FcmNotificationsSender;
import com.example.mobile.session.DocumentId;
import com.example.mobile.session.Session;
import com.example.mobile.session.Session.*;

import com.example.mobile.R;
import com.example.mobile.adapter.ListStudentAddScoreAdapter;
import com.example.mobile.model.StudentData;
import com.example.mobile.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.local.QueryResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private TextInputLayout etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        session = new Session(LoginActivity.this);


        db = FirebaseFirestore.getInstance();
        TextView tvRegister = findViewById(R.id.a_tv_register);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        etUsername = ((TextInputLayout) findViewById(R.id.a_login_et_username));
        etPassword = ((TextInputLayout) findViewById(R.id.a_login_et_password));
        Button btnLogin = findViewById(R.id.a_login_btn_signin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getEditText().getText().toString();
                String password = etPassword.getEditText().getText().toString();
                login(username, password);
            }
        });
        //
        etUsername.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateEmail();
            }
        });
        //
        etPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validatePassword();
            }
        });
    }

    //
    private void login(String email, String password) {
        if ((!validatePassword()) || (!validateEmail())) {
            return;
        }
        db.collection("User").whereEqualTo("email", email).whereEqualTo("password", password)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = (User) document.toObject(User.class);
                                if (user != null) {
                                    Session.setSession(user);
                                    //get id user session
                                    List<String> lKey = new ArrayList<>();
                                    db.collection("User")
                                            .whereEqualTo("email",  ((User) Session.getSession()).getEmail())
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                                            lKey.add(document.getId());
                                                        }
                                                        DocumentId.setDocumentId(lKey.get(0));

                                                        Intent intent;
                                                        switch (user.getCategoryUser()) {
                                                            case "PARENTS":
                                                                DocumentId.setDocumentId(user.getChildId());
                                                                intent = new Intent(LoginActivity.this, ParentsMenuActivity.class);
                                                                startActivity(intent);
                                                                break;
                                                            case "STUDENT":
                                                                intent = new Intent(LoginActivity.this, StudentMenuActivity.class);
                                                                startActivity(intent);
                                                                break;
                                                            case "TEACHER":
                                                                intent = new Intent(LoginActivity.this, TeacherMenuActivity.class);
                                                                startActivity(intent);
                                                                break;
                                                        }
                                                        return;
                                                    }
                                                }
                                            });


                                } else {
                                    Toast.makeText(LoginActivity.this, "User name or password invalid!!", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "User name or password invalid!!", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }

    private boolean validateEmail() {
        if (!etUsername.getEditText().getText().toString().matches("[A-Za-z0-9]+@gmail.com")) {
            etUsername.setError("Please enter a valid your email");
            return false;
        } else {
            etUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        if (etPassword.getEditText().getText().toString().length() < 6) {
            etPassword.setError("Password must be more than 6 characters");
            return false;
        } else {
            etPassword.setError(null);
            return true;
        }
    }

//    private void sendNotification(String userid){
//        FirebaseDatabase.getInstance().getReference().child(userid).child("token").addListenerForSingleValueEvent(
//                new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        usertoken = snapshot.getValue(String.class);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                }
//        );
//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                FcmNotificationsSender notificationsSender = new FcmNotificationsSender(usertoken,"WI", "Test",
//                        getApplicationContext(), LoginActivity.this);
//                notificationsSender.SendNotifications();
//            }
//        },3000);
//
//    }

}