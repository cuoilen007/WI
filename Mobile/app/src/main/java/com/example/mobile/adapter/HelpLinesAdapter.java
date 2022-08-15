package com.example.mobile.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile.R;
import com.example.mobile.activity.EditRevisionClassActivity;
import com.example.mobile.model.RevisionClass;
import com.example.mobile.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class HelpLinesAdapter extends RecyclerView.Adapter<HelpLinesAdapter.ViewHolder> {

    @Override
    public int getItemCount() {
        return list.size();
    }
    List<User> list;
    Context context;

    public HelpLinesAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.helplines_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User teacher= list.get(position);
        holder.txtTeacher.setText(teacher.getFirstName()+ " "+teacher.getLastName());
        holder.txtContact.setText(teacher.getContact());
    }



    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTeacher,txtContact;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTeacher=itemView.findViewById(R.id.helpline_teacher);
            txtContact=itemView.findViewById(R.id.helpline_phone);

        }


    }
}
