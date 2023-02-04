package com.androiddev.projectrelief.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androiddev.projectrelief.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
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
    String img,name,tag,gif,info,yt;
//    TextView asanaHeading;
//    GifImageView asanaGif;
//    TextView asanaInfo;
//    YouTubePlayerView asanaYoutube;

    public YogaInfoFragment() {
        // Required empty public constructor
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

        Bundle bundle = this.getArguments();
        if(bundle!=null){
            gif = (String)bundle.getString("gifKey");
            info = (String)bundle.getString("infoKey");
            name = (String)bundle.getString("nameKey");
            yt = (String)bundle.getString("ytKey");
        }

        TextView headingHolder = view.findViewById(R.id.asana_heading);
        GifImageView asanaGif = view.findViewById(R.id.asana_gif);
        TextView asanaInfo = view.findViewById(R.id.asanas_info);
        YouTubePlayerView asanaYt = view.findViewById(R.id.asana_video);

        headingHolder.setText(name);
        Glide.with(this).load(gif).into(asanaGif);
        asanaInfo.setText(info);
        asanaYt.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = yt;
                youTubePlayer.loadVideo(videoId,0);
            }
        });
        return view;
    }
}