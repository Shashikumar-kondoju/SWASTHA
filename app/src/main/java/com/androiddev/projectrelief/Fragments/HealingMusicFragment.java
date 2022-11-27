package com.androiddev.projectrelief.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.androiddev.projectrelief.Adapters.music_adapter;
import com.androiddev.projectrelief.Models.songs;
import com.androiddev.projectrelief.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HealingMusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealingMusicFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    music_adapter music_adapter;
    LinearLayoutManager musiclayoutmanager;
    FirebaseRecyclerOptions<songs> options;

    public HealingMusicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HealingMusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HealingMusicFragment newInstance(String param1, String param2) {
        HealingMusicFragment fragment = new HealingMusicFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_healing_music, container, false);

        String str;
        Bundle bundle = this.getArguments();
        str = (String) bundle.getString("key");

        recyclerView = (RecyclerView) view.findViewById(R.id.songview);
        musiclayoutmanager = new LinearLayoutManager(getActivity());
        musiclayoutmanager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(musiclayoutmanager);

        if (str.equals("songs")) {
            options =
                    new FirebaseRecyclerOptions.Builder<songs>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("songs"), songs.class)
                            .build();

        }
        music_adapter = new music_adapter(options);
        recyclerView.setAdapter(music_adapter);
        music_adapter.notifyDataSetChanged();
        return view;

    }
    @Override
    public void onStart() {
        super.onStart();
        music_adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        music_adapter.stopListening();
    }

}