package com.androiddev.projectrelief.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.projectrelief.Models.ydmodel;
import com.androiddev.projectrelief.R;

import java.util.ArrayList;
import java.util.List;


public class ydadapter extends RecyclerView.Adapter<ydadapter.ViewHolder> {
    private ArrayList<ydmodel> dataModalArrayList;
    private Context context;

    public ydadapter(FragmentActivity dataModalArrayList, List<ydmodel> homeFragment) {
        this.dataModalArrayList = this.dataModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ydadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ydadapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.yogadaily, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ydmodel modal = dataModalArrayList.get(position);
        holder.nam.setText(modal.getName());

        Picasso.get().load(modal.getLink()).into(holder.lin);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // setting on click listener
                // for our items of recycler items.
                Toast.makeText(context, "wait for " + modal.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModalArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nam;
        private ImageView lin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nam = itemView.findViewById(R.id.yoganm);
            lin = itemView.findViewById(R.id.yogaimg);


        }
    }
}
