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
import com.example.mobile.session.Session;
import com.google.android.material.navigation.NavigationView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
//        tvName = header.findViewById(R.id.hd_tv_name);
//        tvEmail = header.findViewById(R.id.hd_tv_email);
//        tvBalance = header.findViewById(R.id.hd_tv_balance);
        /*--------------------------------Tool Bar--------------------------*/
        //setSupportActionBar(toolbar);
        /*--------------------------------Navigation Drawer Menu--------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toolbar.setTitle("???");
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //find element
        Button btnViewScore = findViewById(R.id.btnViewScore);
        Button btnAcademyProgress = findViewById(R.id.btnAcademyProgress);
        Button btnStudyResources = findViewById(R.id.btnStudyResources);
        Button btnRevisionClasses = findViewById(R.id.btnRevisionClasses);
        Button btnHelplines = findViewById(R.id.btnHelplines);

        btnViewScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentMenuActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnAcademyProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnStudyResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMenuActivity.this, ViewStudyResourceAdapter.class);
                startActivity(intent);
            }
        });

        btnRevisionClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StudentMenuActivity.this, ViewRevisionClassAdapter.class);
                startActivity(intent);
            }
        });

        btnHelplines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}