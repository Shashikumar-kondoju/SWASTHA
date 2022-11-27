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
import com.androiddev.projectrelief.Models.songs;
import com.androiddev.projectrelief.R;

import java.util.ArrayList;

public class music_adapter extends RecyclerView.Adapter<music_adapter.MyViewHolder> {


    Context context;


    ArrayList<songs> list;

    public music_adapter(HealingMusicFragment context, ArrayList<songs> list) {

        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_healing_music,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        songs songs = list.get(position);
        holder.song.setText(songs.getSong());
        holder.description.setText(songs.getDescription());
        holder.imag.setImageURI(Uri.parse(songs.getImg()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

            ImageView imag;
            TextView description, song;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

           imag = itemView.findViewById(R.id.imag);
           description = itemView.findViewById(R.id.description);
           song = itemView.findViewById(R.id.song);

        }
    }


}
