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

public class YogaCategoriesAdapter extends RecyclerView.Adapter<YogaCategoriesAdapter.myViewHolder> {
    private List<model> categories;

    public YogaCategoriesAdapter(List<model> categories){
        this.categories = categories;
    }

    @NonNull
    @Override
    public YogaCategoriesAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow_yogaasanas,parent,false);
        return new myViewHolder(view);
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

    public class myViewHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView name;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            name = (TextView) itemView.findViewById((R.id.name));
        }

        public void setData(int imgAdap, String nameAdap) {
            img.setImageResource(imgAdap);
            name.setText(nameAdap);
        }
    }
}
