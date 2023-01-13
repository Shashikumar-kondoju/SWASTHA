package com.androiddev.projectrelief.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.projectrelief.Models.songs;
import com.androiddev.projectrelief.R;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class music_adapter extends FirebaseRecyclerAdapter<songs, music_adapter.myviewholder> {

    public music_adapter(@NonNull FirebaseRecyclerOptions<songs> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull songs model) {
        Glide.with(holder.imgLink.getContext()).load(model.getImgLink()).into(holder.imgLink);
        holder.songName.setText(model.getSongName());
        holder.description.setText(model.getDescription());
    }



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder  {

        ImageView imgLink;
        TextView songName, description;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imgLink = (ImageView) itemView.findViewById(R.id.imagem);
            songName = (TextView) itemView.findViewById(R.id.song);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }

}





