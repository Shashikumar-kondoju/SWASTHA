package com.androiddev.projectrelief.Fragments;



import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androiddev.projectrelief.Models.model;
import com.androiddev.projectrelief.R;
import com.androiddev.projectrelief.Adapters.YogaCategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

public class YogaCategoriesFragment extends Fragment implements YogaCategoriesAdapter.onNoteListener {


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

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);


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
        categories.add(new model("Basic Exercises",R.drawable.exercise));
        categories.add(new model("Health Related",R.drawable.health));
        categories.add(new model("Stress and Anxiety",R.drawable.stressb));

        rec_view = view.findViewById(R.id.rec_view);
        layoutManager= new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rec_view.setLayoutManager(layoutManager);
        adapter = new YogaCategoriesAdapter(categories,this);
        rec_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onNoteClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("key",categories.get(position).getName());
        Fragment fragment = new NestedRVFragment();
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }
}