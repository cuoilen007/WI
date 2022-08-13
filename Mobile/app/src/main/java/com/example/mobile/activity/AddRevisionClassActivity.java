package com.example.mobile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobile.R;
import com.example.mobile.model.User;
import com.example.mobile.session.Session;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.mobile.R;
public class AddRevisionClassActivity extends AppCompatActivity {

    EditText edtGrade,edtStartDate,edtEndDate;
    Button btnSave;
    CheckBox checkMon,checkTue,checkWed,checkThu,checkFri,checkSat,checkSun;
    List<String> schedule=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_revision_class);
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

        String sche= schedule();
        //    ExtraClass extraClass=new ExtraClass("Maths","Thanh",grade,startDate,endDate,sche);

        btnSave.setOnClickListener(new View.OnClickListener() {
            User user= (User) Session.getSession();
            @Override
            public void onClick(View view) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String,Object> item=new HashMap<>();
                item.put("subject",user.getSubjectTeach());
                item.put("teacher",user.getFirstName()+ " "+user.getLastName());
                item.put("grade",edtGrade.getText().toString());
                item.put("startDate",edtStartDate.getText().toString());
                item.put("endDate",edtEndDate.getText().toString());
                item.put("schedule",schedule());
                db.collection("RevisionClass")
                        .add(item)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(AddRevisionClassActivity.this,"Add Success! DocumentSnapshot added with ID: " + documentReference.getId(),Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(AddRevisionClassActivity.this,ListRevisionClassActivity.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddRevisionClassActivity.this,"Add Fail!",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


    }
    private String schedule(){
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