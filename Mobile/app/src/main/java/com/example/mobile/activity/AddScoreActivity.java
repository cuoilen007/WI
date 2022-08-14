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

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
    List<User> list;

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
        User user = (User) Session.getSession();
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
                if (!validateSpTest() && !validateSpTest()){
                    listView.setAdapter(null);
                    return;
                }
                String text = spClass.getSelectedItem().toString();
                loadListStudent("classed", text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spTest.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                checkSpTest();
                if (!validateSpTest() && !validateSpTest()){
                    listView.setAdapter(null);
                    return;
                }
                String text = spClass.getSelectedItem().toString();
                loadListStudent("classed", text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    HashMap<String, String> hDocId;

    private void loadListStudent(String field, Object value) {
        list = new ArrayList<>();
        hDocId = new HashMap<>();
        db.collection("User").whereEqualTo(field, value).whereEqualTo("categoryUser", "STUDENT")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                int i = 0;
                                User user = (User) document.toObject(User.class);
                                hDocId.put(user.getEmail(), document.getId());
                                list.add(user);
                                i++;
                            }
                            adapter = new ListStudentAddScoreAdapter(AddScoreActivity.this, list);
                            listView.setAdapter(adapter);
                            loadScore();
                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
    private void loadScore(){
        for (String key : hDocId.keySet()){
            db.collection("ScoreDetails").whereEqualTo("studentid", hDocId.get(key)).whereEqualTo("testname", spTest.getSelectedItem().toString()).whereEqualTo("subjectName", ((User)Session.getSession()).getSubjectTeach())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ScoreDetails scoreDetails = (ScoreDetails) document.toObject(ScoreDetails.class);
                                    for (int i = 0; i < listView.getCount(); i++) {
                                        View v = listView.getChildAt(i);
                                        EditText et = (EditText) v.findViewById(R.id.add_score_item_tv_student_score);
                                        TextView tv = (TextView) v.findViewById(R.id.add_score_item_tv_student_email);
                                        if (tv.getText().equals(key)){
                                            et.setText(String.valueOf(scoreDetails.getScoreReceived()));
                                            break;
                                        }
                                    }
                                }
                            } else {
                                //Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });
        }
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

    int call = 0;
    int i = 0;
    int call1 = 0;

    private void submit() {
        if (!validateSpTest() || !validateSpTest()){
            return;
        }
        call = 0;
        for (User item : list) {
            //find
            db.collection("ScoreDetails").whereEqualTo("studentid", hDocId.get(item.getEmail())).whereEqualTo("testname",spTest.getSelectedItem().toString())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                View v = listView.getChildAt(call);
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    ScoreDetails item = (ScoreDetails) document.toObject(ScoreDetails.class);
                                    //add
                                    if (item != null) {
                                        call1 = 1;
                                        EditText score = (EditText) v.findViewById(R.id.add_score_item_tv_student_score);
                                        item.setDate((LocalDate.now()).toString());
                                        item.setScoreReceived(Integer.valueOf(score.getText().toString()));
                                        db.collection("ScoreDetails").document(document.getId())
                                                .set(item)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
//                                                        AlertDialog builder = new AlertDialog.Builder(AddScoreActivity.this).create();
//                                                        builder.setIcon(R.drawable.ic_baseline_add_home_work_24);
//                                                        builder.setTitle("Success");
//                                                        builder.setMessage("Add Score Successfully!");
//                                                        builder.show();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                });
                                        call++;
                                    }
                                }
                                if (call1 == 0) {
                                    EditText score = (EditText) v.findViewById(R.id.add_score_item_tv_student_score);
                                    TextView email = (TextView) v.findViewById(R.id.add_score_item_tv_student_email);
                                    ScoreDetails scoreDetails = new ScoreDetails();
                                    scoreDetails.setTestname(spTest.getSelectedItem().toString());
                                    scoreDetails.setClassName(spClass.getSelectedItem().toString());
                                    scoreDetails.setSubjectName(subject.getText().toString());
                                    scoreDetails.setDate((LocalDate.now()).toString());
                                    scoreDetails.setScoreReceived(Integer.valueOf(score.getText().toString()));
                                    scoreDetails.setStudentid(hDocId.get(email.getText().toString()));
                                    db.collection("ScoreDetails").document()
                                            .set(scoreDetails)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
//                                                    AlertDialog builder = new AlertDialog.Builder(AddScoreActivity.this).create();
//                                                    builder.setIcon(R.drawable.ic_baseline_add_home_work_24);
//                                                    builder.setTitle("Success");
//                                                    builder.setMessage("Add Score Successfully!");
//                                                    builder.show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    e.printStackTrace();
                                                }
                                            });
                                    call++;
                                }

                                call1 = 0;
                            }
                        }
                    });
            i++;
        }
        //end for
        AlertDialog builder = new AlertDialog.Builder(AddScoreActivity.this).create();
        builder.setIcon(R.drawable.ic_baseline_add_home_work_24);
        builder.setTitle("Success");
        builder.setMessage("Add Score Successfully!");
        builder.show();
    }
    private boolean validateSpTest(){
        if (spTest.getSelectedItem().toString().equals("Select Test")){
            return false;
        }else{
            return true;
        }
    }
    private boolean validateSpClass(){
        if (spClass.getSelectedItem().toString().equals("Select Test")){
            return false;
        }else{
            return true;
        }
    }
    private void checkSpTest() {
        HashMap<Integer, String> hmTest = new HashMap<>();
        hmTest.put(1, "fast-test1");
        hmTest.put(2, "middle-test1");
        hmTest.put(3, "final-test1");
        hmTest.put(4, "fast-test2");
        hmTest.put(5, "middle-test2");
        hmTest.put(6, "final-test2");
        for (Integer key : hmTest.keySet()){
            if (hmTest.get(key).equals(spTest.getSelectedItem().toString()) && key!=1){
                db.collection("ScoreDetails").whereEqualTo("testname", hmTest.get(key-1)).whereEqualTo("className", spClass.getSelectedItem().toString()).whereEqualTo("subjectName", ((User)Session.getSession()).getSubjectTeach())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        return;
                                    }
                                    AlertDialog builder = new AlertDialog.Builder(AddScoreActivity.this).create();
                                    builder.setIcon(R.drawable.ic_baseline_error_24);
                                    builder.setTitle("Error!");
                                    builder.setMessage("Please enter the score for the previous test first!");
                                    builder.show();
                                    spTest.setSelection(0);
                                }
                            }
                        });
            }
        }
    }

}