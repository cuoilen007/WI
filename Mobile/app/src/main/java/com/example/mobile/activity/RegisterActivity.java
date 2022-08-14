package com.example.mobile.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.AsyncListUtil;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.enumm.Roles;
import com.example.mobile.model.User;
import com.example.mobile.session.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    private AutoCompleteTextView actRole, actClass, actSubject;
    private TextInputLayout etFirstName, etLastName, etEmail, etContact, etPassword, etRole, etClass, etSubject;
    private Button btnRegister;
    private TextView tvLoginHere;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Title Bar
        actionBar.setTitle("Register");
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);
        //find element
        db = FirebaseFirestore.getInstance();
        actRole = findViewById(R.id.a_register_act_role);
        actClass = findViewById(R.id.a_register_act_class);
        actSubject = findViewById(R.id.a_register_act_subject);
        tvLoginHere = findViewById(R.id.a_register_tv_login_here);
        etRole = findViewById(R.id.a_register_et_role);
        etClass = findViewById(R.id.a_register_et_class);
        etSubject = findViewById(R.id.a_register_et_subject);
        etRole = findViewById(R.id.a_register_et_role);
        etFirstName = findViewById(R.id.a_register_et_firstname);
        etLastName = findViewById(R.id.a_register_et_lastname);
        etContact = findViewById(R.id.a_register_et_contact);
        etPassword = findViewById(R.id.a_register_et_password);
        etEmail = findViewById(R.id.a_register_et_email);
        btnRegister = findViewById(R.id.a_register_btn_submit);
        //get value gender
        String[] option = {"Student", "Teacher"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dropdown_item, option);
        actRole.setText("Roles", false);
        actRole.setAdapter(arrayAdapter);
        String[] classArr = {"10A", "10B", "11A", "11B","12A", "12B"};
        actClass.setText("Class", false);
        ArrayAdapter arrayAdapterClass = new ArrayAdapter(this, R.layout.dropdown_item, classArr);
        actClass.setAdapter(arrayAdapterClass);
        String[] subjectArr = {"Biology", "Maths", "Physics", "English", "Chemistry", "Literature", "History", "Geography"};
        actSubject.setText("Subject", false);
        ArrayAdapter arrayAdapterSubject = new ArrayAdapter(this, R.layout.dropdown_item, subjectArr);
        actSubject.setAdapter(arrayAdapterSubject);
        //chose role
        actRole.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String r = parent.getItemAtPosition(position).toString();
                if (r.equals("Student")){
                    etSubject.setVisibility(View.GONE);
                    etClass.setVisibility(View.VISIBLE);
                }else if(r.equals("Teacher")){
                    etClass.setVisibility(View.GONE);
                    etSubject.setVisibility(View.VISIBLE);
                }
            }
        });
        //validate
        etFirstName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateFirstName();
            }
        });
        etLastName.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateLastName();
            }
        });
        etEmail.getEditText().addTextChangedListener(new TextWatcher() {
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
        etContact.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validatePhone();
            }
        });
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
        //submit
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        //login here click
        tvLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    //back
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return true;
    }

    private boolean validateFirstName() {
        if (etFirstName.getEditText().getText().toString().length() < 1) {
            etFirstName.setError("First name is required");
            return false;
        } else {
            etFirstName.setError(null);
            return true;
        }
    }

    private boolean validateLastName() {
        if (etLastName.getEditText().getText().toString().length() < 1) {
            etLastName.setError("Last name is required");
            return false;
        } else {
            etLastName.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        if (!etEmail.getEditText().getText().toString().matches("[A-Za-z0-9]+@gmail.com")) {
            etEmail.setError("Please enter a valid your email");
            return false;
        } else {
            etEmail.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        if (etContact.getEditText().getText().toString().length() < 6 || etContact.getEditText().getText().toString().length() > 13) {
            etContact.setError("Please enter a valid your phone");
            return false;
        } else {
            etContact.setError(null);
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

    private boolean validateRole() {
        if (actRole.getText().toString().equals("Roles")) {
            etRole.setError("Please chose role");
            return false;
        } else {
            etRole.setError(null);
            return true;
        }
    }

    boolean checkExists = false;

    private void submit() {
        if (validateEmail() && validateFirstName() && validateLastName() && validatePhone() && validatePassword() && validateRole()) {
            //check already
            db.collection("User").whereEqualTo("email", etEmail.getEditText().getText().toString())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    User user1 = (User) document.toObject(User.class);
                                    if (user1 != null) {
                                        Toast.makeText(RegisterActivity.this, "Username already exists!!", Toast.LENGTH_LONG).show();
                                        return;
                                    } else {

                                    }
                                }
                            }

                            registerNewUser(etEmail.getEditText().getText().toString(),etPassword.getEditText().getText().toString() );

                            //create user
                            User user = new User();
                            user.setEmail(etEmail.getEditText().getText().toString());
                            user.setFirstName(etFirstName.getEditText().getText().toString());
                            user.setLastName(etLastName.getEditText().getText().toString());
                            user.setContact(etContact.getEditText().getText().toString());
                            user.setActive(false);
                            user.setCategoryUser(actRole.getText().toString().toUpperCase());
                            user.setCreatedParent(false);
                            user.setPassword(etPassword.getEditText().getText().toString());
                            user.setSubjectTeach(actSubject.getText().toString());
                            user.setChildId("");
                            user.setClassed(actClass.getText().toString());
                            db.collection("User").document()
                                    .set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Intent intent;
                                            if (user.getCategoryUser().equals(Roles.STUDENT.toString())) {
                                                intent = new Intent(RegisterActivity.this, StudentMenuActivity.class);
                                                startActivity(intent);
                                                return;
                                            } else if (user.getCategoryUser().equals(Roles.TEACHER.toString())) {
                                                intent = new Intent(RegisterActivity.this, TeacherMenuActivity.class);
                                                startActivity(intent);
                                                return;
                                            }
                                            return;
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
        } else {
            return;
        }
    }

    private void registerNewUser(String email, String password)
    {

        // create new user or register new user
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                                    .show();


                            // if the user created intent to login activity
//                            Intent intent
//                                    = new Intent(RegisterActivity.this,
//                                    MainActivity.class);
//                            startActivity(intent);
                        }
                        else {

                            // Registration failed
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Registration failed!!"
                                            + " Please try again later",
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
    }

}