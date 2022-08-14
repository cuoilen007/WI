package com.example.mobile.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.adapter.ListStudentAddScoreAdapter;
import com.example.mobile.model.Class;
import com.example.mobile.model.ScoreDetails;
import com.example.mobile.model.StudentData;
import com.example.mobile.model.Subject;
import com.example.mobile.model.Test;
import com.example.mobile.model.User;
import com.example.mobile.session.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddScoreActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private Spinner spClass;
    private Spinner spTest;
    private Button btnSave;
    private ListView listView;
    private List<Class> listClass;
    private ListStudentAddScoreAdapter adapter;
    private TextView subject;
    List<StudentData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);
        //find element
        spClass = findViewById(R.id.a_add_score_sp_class);
        spTest = findViewById(R.id.a_add_score_sp_test);
        listView = (ListView) findViewById(R.id.a_add_score_lv_student);
        btnSave = findViewById(R.id.a_add_score_btn_save);
        subject = findViewById(R.id.a_add_score_tv_subject);
        User user= (User) Session.getSession();
        subject.setText(user.getSubjectTeach());

        db = FirebaseFirestore.getInstance();
        setItemSpClass();
        setItemSpTest();
        //btn save onclick
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        //sp onclick
        spClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String text = spClass.getSelectedItem().toString();
                loadListStudent("userClass", text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadListStudent(String field, Object value) {
        list = new ArrayList<>();
        db.collection("StudentData").whereEqualTo(field, value)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                StudentData user = (StudentData) document.toObject(StudentData.class);
                                user.setDocId(document.getId());
                                list.add(user);
                            }
                            adapter = new ListStudentAddScoreAdapter(AddScoreActivity.this, list);
                            listView.setAdapter(adapter);
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void setItemSpClass() {
        List<String> listItemSpClass = new ArrayList<String>() {
        };
        listItemSpClass.add("Select Class");
        db.collection("Class")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Class item = (Class) document.toObject(Class.class);
                                //listClass.add(item);
                                listItemSpClass.add(item.getClassName());
                            }
                            spClass.setAdapter(new ArrayAdapter<String>(AddScoreActivity.this, android.R.layout.simple_dropdown_item_1line, listItemSpClass));
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
    private void setItemSpTest() {
        List<String> listItemSpTest = new ArrayList<String>() {
        };
        listItemSpTest.add("Select Test");
        db.collection("Test")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Test item = (Test) document.toObject(Test.class);
                                //listClass.add(item);
                                listItemSpTest.add(item.getTestname());
                            }
                            spTest.setAdapter(new ArrayAdapter<String>(AddScoreActivity.this, android.R.layout.simple_dropdown_item_1line, listItemSpTest));
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void submit() {
        for (StudentData item : list) {
            int i = 0;
            View v = listView.getChildAt(i);
            EditText score = (EditText) v.findViewById(R.id.add_score_item_tv_student_score);
            ScoreDetails scoreDetails = new ScoreDetails();
            scoreDetails.setTestname(spTest.getSelectedItem().toString());
            scoreDetails.setClassName(spClass.getSelectedItem().toString());
            scoreDetails.setSubjectName(subject.getText().toString());
            scoreDetails.setDate((LocalDate.now()).toString());
            scoreDetails.setScoreReceived(Integer.valueOf(score.getText().toString()));
            scoreDetails.setStudentid(item.getDocId());
            db.collection("ScoreDetails").document()
                    .set(scoreDetails)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            AlertDialog.Builder builder=new AlertDialog.Builder(AddScoreActivity.this);
                            builder.setIcon(R.drawable.ic_baseline_add_home_work_24);
                            builder.setTitle("Success");
                            builder.setMessage("Add Score Successfully!");
                            builder.create();
                            builder.show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            e.printStackTrace();
                        }
                    });
            i++;
        }


   }

}