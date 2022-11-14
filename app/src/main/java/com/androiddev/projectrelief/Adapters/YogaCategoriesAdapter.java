package com.androiddev.projectrelief.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.projectrelief.Models.model;
import com.androiddev.projectrelief.R;

import java.util.List;

public class YogaCategoriesAdapter extends RecyclerView.Adapter<YogaCategoriesAdapter.myViewHolder> {
    private List<model> categories;
    private onNoteListener ConNoteListener;

    public YogaCategoriesAdapter(List<model> categories,onNoteListener onNoteListener){
        this.categories = categories;
        this.ConNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public YogaCategoriesAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_yogaasanas,parent,false);
        return new myViewHolder(view,ConNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull YogaCategoriesAdapter.myViewHolder holder, int position) {
        int imgAdap = categories.get(position).getImg();
        String nameAdap = categories.get(position).getName();
        holder.setData(imgAdap,nameAdap);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView img;
        private TextView name;
        onNoteListener onNoteListener;

        public myViewHolder(@NonNull View itemView,onNoteListener onNoteListener) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            name = (TextView) itemView.findViewById((R.id.name));
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        public void setData(int imgAdap, String nameAdap) {
            img.setImageResource(imgAdap);
            name.setText(nameAdap);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface onNoteListener{
        void onNoteClick(int position);
    }
}
