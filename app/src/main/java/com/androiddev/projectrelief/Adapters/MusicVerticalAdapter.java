package com.androiddev.projectrelief.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;

import com.androiddev.projectrelief.Models.NestedModel;
import com.androiddev.projectrelief.Models.songs;
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


public class MusicVerticalAdapter extends FirebaseRecyclerAdapter<songs,MusicVerticalAdapter.ViewHolder> {
    private Context context;

    public MusicVerticalAdapter(@NonNull FirebaseRecyclerOptions<songs> options, Context context) {
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vertical_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull songs model) {
        Glide.with(holder.img.getContext()).load(model.getImgLink()).into(holder.img);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.musicimg);
        }
    }
}
