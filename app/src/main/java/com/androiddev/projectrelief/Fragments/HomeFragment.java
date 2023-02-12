package com.androiddev.projectrelief.Fragments;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Intent.getIntent;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androiddev.projectrelief.Activities.MainActivity;
import com.androiddev.projectrelief.Adapters.MusicVerticalAdapter;
import com.androiddev.projectrelief.Adapters.NestedRVAdapter;
import com.androiddev.projectrelief.Adapters.ViewPagerAdapter;
import com.androiddev.projectrelief.Adapters.music_adapter;
import com.androiddev.projectrelief.Adapters.ydadapter;
import com.androiddev.projectrelief.Models.NestedModel;
import com.androiddev.projectrelief.Models.songs;
import com.androiddev.projectrelief.Models.ydmodel;
import com.androiddev.projectrelief.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class HomeFragment extends Fragment{

    CardView yogaCard, musicCard;
    ViewPager viewPager;
    ArrayList<Integer> images = new ArrayList<>();
    ViewPagerAdapter adapter;
    private RecyclerView recyclerView1;
    ydadapter ydAdapter1;
    LinearLayoutManager ydLayoutManager1;
    FirebaseRecyclerOptions<NestedModel> options1;
    private RecyclerView recyclerView2;
    MusicVerticalAdapter musicAdapter2;
    LinearLayoutManager musicLayoutManager2;
    FirebaseRecyclerOptions<songs> options2;
    TextView nameView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences prefs = getActivity().getSharedPreferences("login",Context.MODE_PRIVATE);
        String name = prefs.getString("name","");
        recyclerView1 = view.findViewById(R.id.dailyyoga);
        ydLayoutManager1 = new LinearLayoutManager(getActivity());
        ydLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView1.setLayoutManager(ydLayoutManager1);
        options1 =
                new FirebaseRecyclerOptions.Builder<NestedModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("dailyoga"), NestedModel.class)
                        .build();
        ydAdapter1 = new ydadapter(options1,getContext());
        recyclerView1.setAdapter(ydAdapter1);
//        recyclerView2.stopScroll();
        ydAdapter1.notifyDataSetChanged();

        recyclerView2 = view.findViewById(R.id.music_recent);
        musicLayoutManager2 = new LinearLayoutManager(getActivity());
        musicLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView2.setLayoutManager(musicLayoutManager2);
        options2 =
                new FirebaseRecyclerOptions.Builder<songs>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("songs"), songs.class)
                        .build();
        musicAdapter2 = new MusicVerticalAdapter(options2,getContext());
        recyclerView2.setAdapter(musicAdapter2);
//        recyclerView2.stopScroll();
        musicAdapter2.notifyDataSetChanged();
        musicAdapter2.setOnItemClickListener(new MusicVerticalAdapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position, FirebaseRecyclerOptions<songs> options) {
                Bundle bundle = new Bundle();
//                bundle.putString("songName",model.getSongName());
//                bundle.putString("songLink",model.getSongLink());
//                bundle.putString("imgLink",model.getImgLink());
//                bundle.putString("songDuration",model.getSongDuration());
//                bundle.putString("description",model.getDescription());
                ArrayList<songs> songsL = new ArrayList<>();
                for(int i=0;i<musicAdapter2.getItemCount();i++){
                    songsL.add(options.getSnapshots().get(i));
                }
                bundle.putSerializable("LIST",(Serializable) songsL);
                Fragment fragment = new MusicPlayerFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragment,"MusicPlayerFragment");
                fragmentTransaction.addToBackStack("MusicPlayerFragment");
                fragmentTransaction.commit();
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage(name+"Loading...");
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


//        String name = getActivity().getIntent().getStringExtra("name");
        nameView = view.findViewById(R.id.add_text);
        nameView.setText(name);


        yogaCard = (CardView) view.findViewById(R.id.yoga);
        musicCard = (CardView) view.findViewById(R.id.music_card);

        yogaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame,new YogaCategoriesFragment(),"YogaFragment")
                        .addToBackStack("YogaFragment")
                        .commit();
                ProgressDialog progressDialog = new ProgressDialog(getContext());
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

        musicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.frame,new HealingMusicFragment(),"MusicFragment")
                        .addToBackStack("MusicFragment")
                        .commit();
                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Healing Music Collection...");
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

        viewPager = view.findViewById(R.id.viewPager);
        images.add(R.drawable.quote_1);
        images.add(R.drawable.quote_2);
        images.add(R.drawable.quote_3);

        adapter = new ViewPagerAdapter(getContext(),images);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(20,0,20,0);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ydAdapter1.startListening();
        musicAdapter2.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        ydAdapter1.stopListening();
        musicAdapter2.stopListening();
    }

}