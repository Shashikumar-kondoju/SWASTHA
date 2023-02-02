package com.androiddev.projectrelief.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;

import com.androiddev.projectrelief.Models.NestedModel;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.projectrelief.Models.ydmodel;
import com.androiddev.projectrelief.R;

import java.util.ArrayList;
import java.util.List;


public class ydadapter extends FirebaseRecyclerAdapter<ydmodel, ydadapter.ViewHolder> {
    private Context context;

    public ydadapter(@NonNull FirebaseRecyclerOptions<ydmodel> options, Context context) {
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.horizontal_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ydmodel model) {
        Glide.with(holder.img.getContext()).load(model.getLink()).into(holder.img);
        holder.txt.setText(model.getName());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.yogaimg);
            txt = itemView.findViewById(R.id.img_daily);
        }
    }
}
