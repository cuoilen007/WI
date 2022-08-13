package com.example.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mobile.R;
import com.example.mobile.session.Session;
import com.google.android.material.navigation.NavigationView;

public class TeacherMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_menu);
        /*---------------------------------Hooks-----------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout_teacher);
        navigationView = findViewById(R.id.teacher_nav_view);
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

        Button btnAddStudyResources = findViewById(R.id.btnAddStudyResources);
        btnAddStudyResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeacherMenuActivity.this, AddStudyResourceActivity.class);
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
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}