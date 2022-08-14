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
import com.example.mobile.activity.EditStudyResourceActivity;
import com.example.mobile.model.StudyResource;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class StudyResourceAdapter extends RecyclerView.Adapter<StudyResourceAdapter.ViewHolder>{

    List<StudyResource> list;
    Context context;
    List<String> keyList;

    public StudyResourceAdapter(List<StudyResource> list,List<String> keyList, Context context) {
        this.list = list;
        this.context = context;
        this.keyList=keyList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.study_resource_list_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,@SuppressLint("RecyclerView") int position) {
        StudyResource studyResource= list.get(position);
        holder.txtTopic.setText(studyResource.getTopic());
        holder.txtLink.setText(studyResource.getLink());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        //delete by dialog
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("Confirm");
                b.setMessage("Are you sure delete this  Study course ?");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.collection("StudyResource")
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
                Intent intent = new Intent(context, EditStudyResourceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("objectStudyResource", list.get(position));
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
        TextView txtTopic,txtLink;
        ImageButton btnEdit,btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTopic=itemView.findViewById(R.id.studyResource_item_topic);
            txtLink=itemView.findViewById(R.id.studyResource_item_link);
            btnEdit=itemView.findViewById(R.id.imbEditStudyResource);
            btnDelete=itemView.findViewById(R.id.imbDeleteStudyResource);

        }

    }
}
