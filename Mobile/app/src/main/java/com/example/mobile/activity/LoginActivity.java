package com.example.mobile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView tvRegister = findViewById(R.id.a_tv_register);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, TeacherMenuActivity.class);
                startActivity(intent);
            }
        });
        EditText etUsername = ((TextInputLayout)findViewById(R.id.a_login_et_username)).getEditText();
        EditText etPassword = ((TextInputLayout)findViewById(R.id.a_login_et_password)).getEditText();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        Button btnLogin = findViewById(R.id.a_login_btn_signin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"username: "+username, Toast.LENGTH_LONG).show();
            }
        });
    }
}