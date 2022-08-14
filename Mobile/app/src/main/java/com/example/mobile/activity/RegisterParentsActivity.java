package com.example.mobile.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RegisterParentsActivity extends AppCompatActivity {
    private TextInputLayout etFirstName, etLastName, etEmail, etContact, etPassword;
    private Button btnRegister;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private User SessionUser;
    private String userIdSession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_parents);
        SessionUser = (User) Session.getSession();

        mAuth = FirebaseAuth.getInstance();
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
        etFirstName = findViewById(R.id.a_register_et_firstname_parent);
        etLastName = findViewById(R.id.a_register_et_lastname_parent);
        etContact = findViewById(R.id.a_register_et_contact_parent);
        etPassword = findViewById(R.id.a_register_et_password_parent);
        etEmail = findViewById(R.id.a_register_et_email_parent);
        btnRegister = findViewById(R.id.a_register_btn_submit_parent);

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
                            userIdSession = lKey.get(0);
                        }
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


    private void submit() {
        if (validateEmail() && validateFirstName() && validateLastName() && validatePhone() && validatePassword()) {
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
                                        Toast.makeText(RegisterParentsActivity.this, "Username already exists!!", Toast.LENGTH_LONG).show();
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
                            user.setChildId("");
                            user.setPassword(etPassword.getEditText().getText().toString());
                            user.setCategoryUser("PARENTS");
                            Toast.makeText(RegisterParentsActivity.this, userIdSession, Toast.LENGTH_SHORT).show();

                            //edit userSession field createdParent
                            SessionUser.setCreatedParent(true);
                            db.collection("User").document(userIdSession)
                                    .set(SessionUser)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Intent intent;
                                            intent = new Intent(RegisterParentsActivity.this, StudentMenuActivity.class);
                                            startActivity(intent);
                                            return;
                                        }
                                    });

                            db.collection("User").document()
                                    .set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Intent intent;
                                                intent = new Intent(RegisterParentsActivity.this, StudentMenuActivity.class);
                                                startActivity(intent);
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
                            //Intent intent
                                    //= new Intent(RegisterParentsActivity.this,
                                    //MainActivity.class);
                            //startActivity(intent);
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