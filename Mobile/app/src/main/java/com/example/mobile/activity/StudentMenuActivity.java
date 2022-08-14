package com.example.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mobile.R;
import com.example.mobile.adapter.ViewRevisionClassAdapter;
import com.example.mobile.adapter.ViewStudyResourceAdapter;
import com.example.mobile.model.User;
import com.example.mobile.session.Session;
import com.google.android.material.navigation.NavigationView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_menu);

        /*---------------------------------Hooks-----------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout_student);
        navigationView = findViewById(R.id.user_nav_view1);
        toolbar = findViewById(R.id.toolbar1);
        /*--------------------------------Load TextView Header --------------------------*/
        View header = navigationView.getHeaderView(0);
        TextView tvName = header.findViewById(R.id.hd_tv_name);
        TextView tvEmail = header.findViewById(R.id.hd_tv_email);
        TextView tvBalance = header.findViewById(R.id.hd_tv_balance);

        tvName.setText(((User)Session.getSession()).getFirstName() + " " + ((User)Session.getSession()).getLastName());
        tvEmail.setText(((User)Session.getSession()).getEmail());
        tvBalance.setText(((User)Session.getSession()).getCategoryUser());
        /*--------------------------------Tool Bar--------------------------*/
        //setSupportActionBar(toolbar);
        /*--------------------------------Navigation Drawer Menu--------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toolbar.setTitle("Student Menu");
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //find element
        Button btnViewScore = findViewById(R.id.btnViewScore);
        Button btnAcademyProgress = findViewById(R.id.btnAcademyProgress);
        Button btnStudyResources = findViewById(R.id.btnStudyResources);
        Button btnRevisionClasses = findViewById(R.id.btnRevisionClasses);
        Button btnHelplines = findViewById(R.id.btnHelplines);
        Button btnAnalystic = findViewById(R.id.btnAnalystic);
        btnViewScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentMenuActivity.this, ViewScoresActivity.class);
                startActivity(intent);
            }
        });

        btnAcademyProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMenuActivity.this, AcademyProgressActivity.class);
                startActivity(intent);
            }
        });

        btnStudyResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMenuActivity.this, ViewStudyResourceActivity.class);
                startActivity(intent);
            }
        });

        btnRevisionClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMenuActivity.this, ViewRevisionClassActivity.class);
                startActivity(intent);
            }
        });

        btnHelplines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMenuActivity.this, ContactusActivity.class);
                startActivity(intent);
            }
        });
        btnAnalystic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMenuActivity.this, AnalysticActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String id = item.toString();
        Intent intent;
        switch (id) {
            case "Logout":
                Session.setSession(null);
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case "Register Parents Account":
                intent = new Intent(this, RegisterParentsActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}