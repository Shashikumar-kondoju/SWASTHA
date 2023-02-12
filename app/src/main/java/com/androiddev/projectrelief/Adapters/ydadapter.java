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

import com.androiddev.projectrelief.Fragments.YogaInfoFragment;
import com.androiddev.projectrelief.Models.NestedModel;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.projectrelief.Models.ydmodel;
import com.androiddev.projectrelief.R;

import java.util.ArrayList;
import java.util.List;


public class ydadapter extends FirebaseRecyclerAdapter<NestedModel, ydadapter.ViewHolder> {
    private Context context;

    public ydadapter(@NonNull FirebaseRecyclerOptions<NestedModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.horizontal_rv_item, parent, false);
        return new ViewHolder(view);
    }

    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull NestedModel model) {
        Glide.with(holder.img.getContext()).load(model.getImg()).into(holder.img);
        holder.txt.setText(model.getName());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("gifKey",model.getGif());
                bundle.putString("infoKey",model.getInfo());
                bundle.putString("nameKey",model.getName());
                bundle.putString("ytKey",model.getYt());
                YogaInfoFragment yogaInfoFragment = new YogaInfoFragment();
                yogaInfoFragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame,yogaInfoFragment,"YogaInfoFragment").addToBackStack("YogaInfoFragment").commit();
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                progressDialog.setMessage("Asanas Loading...");
//                progressDialog.setTitle();
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                },4000);
            }
        });
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
