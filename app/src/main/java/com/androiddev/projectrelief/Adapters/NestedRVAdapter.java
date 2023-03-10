package com.androiddev.projectrelief.Adapters;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.projectrelief.Fragments.YogaInfoFragment;
import com.androiddev.projectrelief.Models.NestedModel;
import com.androiddev.projectrelief.R;
import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class NestedRVAdapter extends FirebaseRecyclerAdapter<NestedModel, NestedRVAdapter.myviewholder> {

    public NestedRVAdapter(@NonNull FirebaseRecyclerOptions<NestedModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull NestedModel model) {
        Glide.with(holder.img.getContext()).load(model.getImg()).into(holder.img);
        holder.name.setText(model.getName());
        holder.tag.setText(model.getTag());
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



    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nested_r_v_item,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,tag;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView)itemView.findViewById(R.id.image);
            name = (TextView)itemView.findViewById(R.id.main_title);
            tag = (TextView)itemView.findViewById(R.id.tag_title);
        }
    }
}
