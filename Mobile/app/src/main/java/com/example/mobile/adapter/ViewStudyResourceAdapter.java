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
import com.example.mobile.model.StudyResource;

import java.util.List;

public class ViewStudyResourceAdapter extends RecyclerView.Adapter<ViewStudyResourceAdapter.ViewHolder>{
    List<StudyResource> list;
    Context context;

    public ViewStudyResourceAdapter(List<StudyResource> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_study_resource_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        StudyResource studyResource= list.get(position);
        holder.txtTopic.setText(studyResource.getTopic());
        holder.txtLink.setText(studyResource.getLink());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtTopic,txtLink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTopic=itemView.findViewById(R.id.studyResource_item_topic);
            txtLink=itemView.findViewById(R.id.studyResource_item_link);

        }

    }
}
