package com.example.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.model.RevisionClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditRevisionClassActivity extends AppCompatActivity {

    EditText edtGrade,edtStartDate,edtEndDate;
    Button btnSave;
    CheckBox checkMon,checkTue,checkWed,checkThu,checkFri,checkSat,checkSun;
    List<String> schedule=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_revision_class);

        edtGrade=findViewById(R.id.editGrade);
        edtStartDate=findViewById(R.id.editStartDate);
        edtEndDate=findViewById(R.id.editEndDate);

        btnSave=findViewById(R.id.btnSaveAddExtrasClass);
        checkMon=findViewById(R.id.checkMon);
        checkTue=findViewById(R.id.checkTue);
        checkWed=findViewById(R.id.checkWeb);
        checkThu=findViewById(R.id.checkThu);
        checkFri=findViewById(R.id.checkFri);
        checkSat=findViewById(R.id.checkSat);
        checkSun=findViewById(R.id.checkSun);

        //get du lieu tu list qua dialog
        String key =getIntent().getStringExtra("key");
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            return;
        }
        RevisionClass revisionClass = (RevisionClass) bundle.get("objectRevisionClass");
        edtGrade.setText(revisionClass.getGrade());
        edtStartDate.setText(revisionClass.getStartDate());
        edtEndDate.setText(revisionClass.getEndDate());

        String[] scheduleArray= revisionClass.getSchedule().split(",");
        for (String s:scheduleArray){
            if (s.equals(checkMon.getText().toString())){
                checkMon.setChecked(true);
            }
            if (s.equals(checkTue.getText().toString())){
                checkTue.setChecked(true);
            }
            if (s.equals(checkWed.getText().toString())){
                checkWed.setChecked(true);
            }
            if (s.equals(checkThu.getText().toString())){
                checkThu.setChecked(true);
            }
            if (s.equals(checkFri.getText().toString())){
                checkFri.setChecked(true);
            }
            if (s.equals(checkSat.getText().toString())){
                checkSat.setChecked(true);
            }
            if (s.equals(checkSun.getText().toString())){
                checkSun.setChecked(true);
            }
        }
        String sche= schedule();
        //    ExtraClass extraClass=new ExtraClass("Maths","Thanh",grade,startDate,endDate,sche);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String,Object> item=new HashMap<>();
                item.put("subject",revisionClass.getSubject());
                item.put("teacher",revisionClass.getTeacher());
                item.put("grade",edtGrade.getText().toString());
                item.put("startDate",edtStartDate.getText().toString());
                item.put("endDate",edtEndDate.getText().toString());
                item.put("schedule",schedule());
                db.collection("RevisionClass").document(key)
                        .set(item)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getBaseContext(),"Edit Success!",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(EditRevisionClassActivity.this,ListRevisionClassActivity.class);
                                startActivity(intent);
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


    }
    private String schedule(){
        schedule.clear();
        if (checkMon.isChecked()){
            schedule.add(checkMon.getText().toString());
        }
        if (checkTue.isChecked()){
            schedule.add(checkTue.getText().toString());
        }
        if (checkWed.isChecked()){
            schedule.add(checkWed.getText().toString());
        }
        if (checkThu.isChecked()){
            schedule.add(checkThu.getText().toString());
        }
        if (checkFri.isChecked()){
            schedule.add(checkFri.getText().toString());
        }
        if (checkSat.isChecked()){
            schedule.add(checkSat.getText().toString());
        }
        if (checkSun.isChecked()){
            schedule.add(checkSun.getText().toString());
        }
        String sche = "";
        for (String s: schedule){
            sche=sche+","+s;
        }
        int length=sche.length();
        if (length>1){
            return sche.substring(1);
        }else{
            return null;
        }


    }
}