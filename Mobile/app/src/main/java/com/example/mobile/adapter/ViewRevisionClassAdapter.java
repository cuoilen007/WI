package com.example.mobile.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile.R;
import com.example.mobile.model.RevisionClass;

import java.util.List;

public class ViewRevisionClassAdapter extends RecyclerView.Adapter<ViewRevisionClassAdapter.ViewHolder>{


    List<RevisionClass> list;
    Context context;


    public ViewRevisionClassAdapter(List<RevisionClass> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_revision_class_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RevisionClass revisionClass =list.get(position);
        holder.txtTeacher.setText(revisionClass.getTeacher());
        holder.txtSubject.setText(revisionClass.getSubject());
        holder.txtGrade.setText(revisionClass.getGrade());
        holder.txtStartDate.setText(revisionClass.getStartDate());
        holder.txtEndDate.setText(revisionClass.getEndDate());
        holder.txtSchedule.setText(revisionClass.getSchedule());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTeacher,txtSubject,txtStartDate,txtEndDate,txtSchedule,txtGrade;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTeacher=itemView.findViewById(R.id.View_Revision_class_item_teacher);
            txtSubject=itemView.findViewById(R.id.View_Revision_class_item_subject);
            txtGrade=itemView.findViewById(R.id.View_Revision_class_item_grade);
            txtStartDate=itemView.findViewById(R.id.View_Revision_class_item_startDate);
            txtEndDate=itemView.findViewById(R.id.View_Revision_class_item_endDate);
            txtSchedule=itemView.findViewById(R.id.View_Revision_class_item_schedule);

        }


    }
}
