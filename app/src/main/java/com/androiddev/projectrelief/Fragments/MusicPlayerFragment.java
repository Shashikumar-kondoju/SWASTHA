package com.androiddev.projectrelief.Fragments;

import android.icu.text.CaseMap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.androiddev.projectrelief.Activities.MainActivity;
import com.androiddev.projectrelief.R;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicPlayerFragment extends Fragment {

    String songNameStr,songLinkStr,imgLinkStr,songDurationStr,descriptionStr;
    MediaPlayer mediaPlayer = new MediaPlayer();
    SeekBar seekBar;
    boolean wasPlaying = false;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MusicPlayerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutUsFragment newInstance(String param1, String param2) {
        AboutUsFragment fragment = new AboutUsFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_player, container, false);

        Bundle bundle = this.getArguments();
        if(bundle!=null){
            songNameStr = (String)bundle.getString("songName");
            songDurationStr = (String)bundle.getString("songDuration");
            songLinkStr = (String)bundle.getString("songLink");
            imgLinkStr = (String)bundle.getString("imgLink");
            descriptionStr = (String)bundle.getString("description");
        }

        ImageView songImg = view.findViewById(R.id.img_music);
        MaterialTextView songName = view.findViewById(R.id.txt_song_name);
        MaterialTextView singerName = view.findViewById(R.id.txt_singer_name);
        ImageView playButton = view.findViewById(R.id.btn_play);
        ImageView playNext = view.findViewById(R.id.btn_next);
        ImageView playPrev = view.findViewById(R.id.btn_pre);

        songName.setText(songNameStr);
        Glide.with(MusicPlayerFragment.this).load(imgLinkStr).into(songImg);
        singerName.setText(descriptionStr);
        //Playing song implementation
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaPlayer.isPlaying()){
                    playAudio();
                }else{
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                }
            }
        });
        return view;
    }
    private void playAudio(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(songLinkStr);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}