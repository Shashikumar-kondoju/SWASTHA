package com.androiddev.projectrelief.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.projectrelief.Fragments.HealingMusicFragment;
import com.androiddev.projectrelief.Models.NestedModel;
import com.androiddev.projectrelief.Models.songs;
import com.androiddev.projectrelief.R;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.BreakIterator;
import java.util.ArrayList;

public class music_adapter extends FirebaseRecyclerAdapter<songs, music_adapter.myviewholder> {

    public music_adapter(@NonNull FirebaseRecyclerOptions<songs> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull songs model) {
        Glide.with(holder.imag.getContext()).load(model.getImg()).into(holder.imag);
        holder.song.setText(model.getSong());
        holder.description.setText(model.getDescription());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_healing_music,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        ImageView imag;
        TextView song,description;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imag = (ImageView)itemView.findViewById(R.id.imag);
            song = (TextView)itemView.findViewById(R.id.song);
            description = (TextView)itemView.findViewById(R.id.description);
        }
    }
}
