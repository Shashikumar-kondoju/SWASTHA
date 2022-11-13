package com.androiddev.projectrelief;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class NestedRVAdapter extends FirebaseRecyclerAdapter<NestedModel, NestedRVAdapter.myviewholder> {

    public NestedRVAdapter(@NonNull FirebaseRecyclerOptions<NestedModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull NestedModel model) {
        Glide.with(holder.img.getContext()).load(model.getImg()).into(holder.img);
        holder.name.setText(model.getName());
        holder.tag.setText(model.getTag());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_r_v_item,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,tag;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.image);
            name = (TextView)itemView.findViewById(R.id.main_title);
            tag = (TextView)itemView.findViewById(R.id.tag_title);
        }
    }
}
