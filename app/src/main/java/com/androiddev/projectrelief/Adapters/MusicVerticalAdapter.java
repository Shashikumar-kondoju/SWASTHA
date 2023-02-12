package com.androiddev.projectrelief.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;

import com.androiddev.projectrelief.Fragments.MusicPlayerFragment;
import com.androiddev.projectrelief.Models.NestedModel;
import com.androiddev.projectrelief.Models.songs;
import com.androiddev.projectrelief.MyMediaPlayer;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.projectrelief.Models.ydmodel;
import com.androiddev.projectrelief.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MusicVerticalAdapter extends FirebaseRecyclerAdapter<songs,MusicVerticalAdapter.ViewHolder> {
    private Context context;
    FirebaseRecyclerOptions<songs> songsList;
    private static onRecyclerViewItemClickListener mItemClickListener;

    public MusicVerticalAdapter(@NonNull FirebaseRecyclerOptions<songs> options, Context context) {
        super(options);
        this.songsList = options;
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
        holder.songName.setText(model.getSongName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyMediaPlayer.getInstance().reset();
                MyMediaPlayer.currentIndex = holder.getAdapterPosition();
                mItemClickListener.onItemClickListener(view, holder.getAdapterPosition(),songsList);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView songName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.musicimg);
            songName = itemView.findViewById(R.id.song_daily);
        }
    }

    public void setOnItemClickListener(onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, int position,FirebaseRecyclerOptions<songs> options);
    }
}
