package com.androiddev.projectrelief.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androiddev.projectrelief.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import pl.droidsonroids.gif.GifImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YogaInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YogaInfoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String img,name,tag,gif,info;
//    TextView asanaHeading;
//    GifImageView asanaGif;
//    TextView asanaInfo;
//    YouTubePlayerView asanaYoutube;

    public YogaInfoFragment() {
        // Required empty public constructor
    }

    public YogaInfoFragment(String gif,String img,String info,String name,String tag) {
        this.img = img;
        this.name = name;
        this.gif = gif;
        this.tag = tag;
        this.info = info;
    }

    public static YogaInfoFragment newInstance(String param1, String param2) {
        YogaInfoFragment fragment = new YogaInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_yoga_info, container, false);

        TextView headingHolder = view.findViewById(R.id.asana_heading);
        GifImageView asanaGif = view.findViewById(R.id.asana_gif);
        TextView asanaInfo = view.findViewById(R.id.asanas_info);
        YouTubePlayerView asanaYt = view.findViewById(R.id.asana_video);

        headingHolder.setText(name);
        Glide.with(this).load(gif).into(asanaGif);
        asanaInfo.setText(info);
        return view;
    }
    public void onBackPressed(){
        AppCompatActivity activity = (AppCompatActivity)getContext();
        assert activity != null;
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, new YogaCategoriesFragment())
                .addToBackStack(null)
                .commit();
    }
}