package com.androiddev.projectrelief.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.androiddev.projectrelief.Adapters.music_adapter;
import com.androiddev.projectrelief.Models.songs;
import com.androiddev.projectrelief.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HealingMusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HealingMusicFragment extends Fragment  {

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
        // This callback will only be called when MyFragment is at least Started.

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
        recyclerView = (RecyclerView) view.findViewById(R.id.songview);
        musiclayoutmanager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(musiclayoutmanager);

        options =
                new FirebaseRecyclerOptions.Builder<songs>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("songs"), songs.class)
                        .build();
        music_adapter = new music_adapter(options,getContext());
        recyclerView.setAdapter(music_adapter);
        music_adapter.notifyDataSetChanged();
        music_adapter.setOnItemClickListener(new music_adapter.onRecyclerViewItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position,FirebaseRecyclerOptions<songs> songsList) {
                Bundle bundle = new Bundle();
//                bundle.putString("songName",model.getSongName());
//                bundle.putString("songLink",model.getSongLink());
//                bundle.putString("imgLink",model.getImgLink());
//                bundle.putString("songDuration",model.getSongDuration());
//                bundle.putString("description",model.getDescription());
                ArrayList<songs> songsL = new ArrayList<>();
                for(int i=0;i<music_adapter.getItemCount();i++){
                    songsL.add(songsList.getSnapshots().get(i));
                }
                bundle.putSerializable("LIST",(Serializable) songsL);
                Fragment fragment = new MusicPlayerFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame,fragment,"MusicPlayerFragment");
                fragmentTransaction.addToBackStack("MusicPlayerFragment");
                fragmentTransaction.commit();
            }
        });

        return view;
    }

    public void onClickAdapter(FirebaseRecyclerOptions<songs> options){

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