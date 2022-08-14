package com.example.mobile.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobile.R;
import com.example.mobile.model.StudentData;
import com.example.mobile.model.User;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class ListStudentAddScoreAdapter extends BaseAdapter {
    private List<User> studentData;
    private Activity context;
    public ListStudentAddScoreAdapter(Activity context, List<User> studentData) {
        this.studentData = studentData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return studentData.size();
    }

    @Override
    public Object getItem(int position) {
        return studentData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User item = studentData.get(position);
        // Check if an existing view is being reused, otherwise inflate the view
        View viewContact;
        if (convertView == null){
            viewContact = View.inflate(parent.getContext(), R.layout.add_score_item, null);
        }else{
            viewContact = convertView;
        }
        TextView tvStudentName = (TextView) viewContact.findViewById(R.id.add_score_item_tv_student_name);
        TextView etStudentScore = (TextView) viewContact.findViewById(R.id.add_score_item_tv_student_score);
        TextView tvEmail = (TextView) viewContact.findViewById(R.id.add_score_item_tv_student_email);
        //set text
        tvEmail.setText(item.getEmail());
        tvStudentName.setText(item.getLastName());
        return viewContact;
    }
}
