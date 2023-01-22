package com.androiddev.projectrelief.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.projectrelief.Models.songs;
import com.androiddev.projectrelief.MyMediaPlayer;
import com.androiddev.projectrelief.R;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;
import java.util.ArrayList;

public class music_adapter extends FirebaseRecyclerAdapter<songs, music_adapter.myviewholder> implements Serializable {

    Context context;
    private static onRecyclerViewItemClickListener mItemClickListener;
    FirebaseRecyclerOptions<songs> songsList;
    songs model;

    public music_adapter(@NonNull FirebaseRecyclerOptions<songs> options,Context context) {
        super(options);
        this.context = context;
        this.songsList = options;
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull songs model) {
        Glide.with(holder.imgLink.getContext()).load(model.getImgLink()).into(holder.imgLink);
        holder.songName.setText(model.getSongName());
        holder.description.setText(model.getDescription());
        this.model = model;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyMediaPlayer.getInstance().reset();
                MyMediaPlayer.currentIndex = holder.getAdapterPosition();
                mItemClickListener.onItemClickListener(view, holder.getAdapterPosition(),songsList);
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder  {

        ImageView imgLink;
        TextView songName, description;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imgLink = (ImageView) itemView.findViewById(R.id.imagem);
            songName = (TextView) itemView.findViewById(R.id.song);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
    public void setOnItemClickListener(onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, int position,FirebaseRecyclerOptions<songs> options);
    }

}





