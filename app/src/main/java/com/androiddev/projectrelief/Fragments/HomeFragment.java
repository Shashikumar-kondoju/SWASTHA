package com.androiddev.projectrelief.Fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androiddev.projectrelief.Adapters.ViewPagerAdapter;
import com.androiddev.projectrelief.Adapters.ydadapter;
import com.androiddev.projectrelief.Models.ydmodel;
import com.androiddev.projectrelief.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class HomeFragment extends Fragment implements View.OnClickListener {

    CardView yogaCard, musicCard;
    ViewPager viewPager;
    ArrayList<Integer> images = new ArrayList<>();
    ViewPagerAdapter adapter;
    private RecyclerView recyclerView;
    private ydadapter ydAdapter;
    private List<ydmodel> ydModelList;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


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


        recyclerView = view.findViewById(R.id.dailyyoga);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        ydModelList = new ArrayList<>();
        ydAdapter = new ydadapter(getActivity(), ydModelList);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("dailyoga");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ydmodel ydModel = snapshot.getValue(ydmodel.class);
                    ydModelList.add(ydModel);
                }
                recyclerView.setAdapter(ydAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        yogaCard = (CardView) view.findViewById(R.id.yoga);
        musicCard = (CardView) view.findViewById(R.id.music_card);


        yogaCard.setOnClickListener(this);
        musicCard.setOnClickListener(this);

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
    public void onClick(View view) {
        Fragment fragment;
        switch (view.getId()){
            case R.id.yoga: fragment = new YogaCategoriesFragment();
                break;
            case R.id.music_card: fragment = new HealingMusicFragment();
                break;
            default: fragment = new HomeFragment();
                break;
        }
        getFragmentManager().beginTransaction()
                .replace(R.id.frame,fragment)
                .addToBackStack(null)
                .commit();
    }
}