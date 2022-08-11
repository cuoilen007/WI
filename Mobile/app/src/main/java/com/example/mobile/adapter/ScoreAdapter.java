package com.example.mobile.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile.R;
import com.example.mobile.model.User;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {
        Context context;
        ArrayList<User> list;

    public ScoreAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
        @Override
        public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item_list_score, parent,false);
            return new ScoreViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
            User user = list.get(position);
            holder.name.setText(user.getFirstName());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public static class ScoreViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item);
        }
    }

}
