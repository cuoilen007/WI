package com.example.mobile.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile.R;
import com.example.mobile.activity.EditRevisionClassActivity;
import com.example.mobile.model.RevisionClass;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RevisionClassAdapter extends RecyclerView.Adapter<RevisionClassAdapter.ViewHolder> {


    List<RevisionClass> list;
    Context context;
    List<String> keyList;


    public RevisionClassAdapter(List<RevisionClass> list, List<String> keyList, Context context) {
        this.list = list;
        this.context = context;
        this.keyList=keyList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.extras_class_list_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,@SuppressLint("RecyclerView") int position) {
     RevisionClass revisionClass =list.get(position);
        holder.txtTeacher.setText(revisionClass.getTeacher());
        holder.txtSubject.setText(revisionClass.getSubject());
        holder.txtGrade.setText(revisionClass.getGrade());
        holder.txtStartDate.setText(revisionClass.getStartDate());
        holder.txtEndDate.setText(revisionClass.getEndDate());
        holder.txtSchedule.setText(revisionClass.getSchedule());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //xoa theo dialog
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("Confirm");
                b.setMessage("Are you sure delete this Revision Class?");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.collection("RevisionClass")
                                .document(keyList.get(position))
                                .delete();
                        list.remove(position);
                        notifyDataSetChanged();
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog al = b.create();
                //Hiển thị
                al.show();
            }
        });
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditRevisionClassActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("objectRevisionClass", list.get(position));
                intent.putExtras(bundle);
                intent.putExtra("key",keyList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTeacher,txtSubject,txtStartDate,txtEndDate,txtSchedule,txtGrade;
        ImageButton btnEdit,btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTeacher=itemView.findViewById(R.id.extrasclass_item_teacher);
            txtSubject=itemView.findViewById(R.id.extrasclass_item_subject);
            txtGrade=itemView.findViewById(R.id.extrasclass_item_grade);
            txtStartDate=itemView.findViewById(R.id.extrasclass_item_startDate);
            txtEndDate=itemView.findViewById(R.id.extrasclass_item_endDate);
            txtSchedule=itemView.findViewById(R.id.extrasclass_item_schedule);

            btnEdit=itemView.findViewById(R.id.imbEditExtralass);
            btnDelete=itemView.findViewById(R.id.imbDeleteExtralass);

        }


    }

}
