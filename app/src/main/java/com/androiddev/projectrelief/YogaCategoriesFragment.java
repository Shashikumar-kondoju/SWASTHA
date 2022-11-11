package com.androiddev.projectrelief;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class YogaCategoriesFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView rec_view;
    YogaCategoriesAdapter adapter;
    List<model> categories;
    LinearLayoutManager layoutManager;


    private String mParam1;
    private String mParam2;

    public YogaCategoriesFragment() {

    }


    public static YogaCategoriesFragment newInstance(String param1, String param2) {
        YogaCategoriesFragment fragment = new YogaCategoriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_yoga, container, false);

        categories = new ArrayList<>();
        categories.add(new model("Basic Asanas",R.drawable.basic));
        categories.add(new model("Health Related",R.drawable.health));
        categories.add(new model("Stress and Anxiety",R.drawable.stress));

        rec_view = view.findViewById(R.id.rec_view);
        layoutManager= new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rec_view.setLayoutManager(layoutManager);
        adapter = new YogaCategoriesAdapter(categories);
        rec_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

}