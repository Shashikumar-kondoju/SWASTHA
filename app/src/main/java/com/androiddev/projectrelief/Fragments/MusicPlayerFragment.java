package com.androiddev.projectrelief.Fragments;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.androiddev.projectrelief.Models.songs;
import com.androiddev.projectrelief.MyMediaPlayer;
import com.androiddev.projectrelief.R;
import com.bumptech.glide.Glide;
import com.google.android.material.textview.MaterialTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicPlayerFragment extends Fragment {

    String songNameStr,songLinkStr,imgLinkStr,songDurationStr,descriptionStr;
    MediaPlayer mediaPlayer = MyMediaPlayer.getInstance();
    SeekBar seekBar;
    ImageView playPauseButton,playPrev,playNext;
    songs currentSong;
    ArrayList<songs> songsLi;
    ImageView songImg;
    MaterialTextView songName;
    MaterialTextView singerName;
    TextView totalDuration,startDuration;

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


    int x = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_player, container, false);

        Bundle bundle = this.getArguments();
        if(bundle!=null){
//            songNameStr = (String)bundle.getString("songName");
//            songDurationStr = (String)bundle.getString("songDuration");
//            songLinkStr = (String)bundle.getString("songLink");
//            imgLinkStr = (String)bundle.getString("imgLink");
//            descriptionStr = (String)bundle.getString("description");
            songsLi = (ArrayList<songs>) bundle.getSerializable("LIST");
        }

        //finding their views
        songImg = view.findViewById(R.id.img_music);
        songName = view.findViewById(R.id.txt_song_name);
        singerName = view.findViewById(R.id.txt_singer_name);
        playPauseButton = view.findViewById(R.id.btn_play);
        playNext = view.findViewById(R.id.btn_next);
        playPrev = view.findViewById(R.id.btn_pre);
        totalDuration = view.findViewById(R.id.total_duration);
        startDuration = view.findViewById(R.id.start_duration);
        seekBar = view.findViewById(R.id.seek_progress);

        //implementation
        setResourceMusic();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    startDuration.setText(convertToMMSS(mediaPlayer.getCurrentPosition()+""));
                    if(mediaPlayer.isPlaying()){
                        songImg.setRotation(x++);
                    }else{
                        songImg.setRotation(0);
                    }
                }
                new Handler().postDelayed(this,100);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(mediaPlayer!=null&&b){
                    mediaPlayer.seekTo(i);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }

    private void playMusic(){
        mediaPlayer.reset();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(currentSong.getSongLink());
            mediaPlayer.prepare();
            mediaPlayer.start();
            playPauseButton.setImageResource(R.drawable.ic_pause);
            seekBar.setProgress(0);
            seekBar.setMax(mediaPlayer.getDuration());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void pausePlay(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            playPauseButton.setImageResource(R.drawable.ic_play);
            songImg.setRotation(0);
        } else{
            mediaPlayer.start();
            playPauseButton.setImageResource(R.drawable.ic_pause);
        }
    }

    private void playNextSong(){
        if(MyMediaPlayer.currentIndex==songsLi.size()-1)
            return;
        MyMediaPlayer.currentIndex += 1;
        songImg.setRotation(0);
        setResourceMusic();
        playMusic();
    }

    private void setResourceMusic() {
        currentSong = songsLi.get(MyMediaPlayer.currentIndex);
        songName.setText(currentSong.getSongName());
        singerName.setText(currentSong.getDescription());
        totalDuration.setText(currentSong.getSongDuration());
        Glide.with(this).load(currentSong.getImgLink()).into(songImg);
        playPauseButton.setOnClickListener(view1 -> pausePlay());
        playNext.setOnClickListener(view1 -> playNextSong());
        playPrev.setOnClickListener(view1 -> playPreviousSong());
        playMusic();
    }

    private void playPreviousSong(){
        if(MyMediaPlayer.currentIndex==0)
            return;
        MyMediaPlayer.currentIndex -= 1;
        songImg.setRotation(0);
        setResourceMusic();
        playMusic();
    }

    public static String convertToMMSS(String songDuration) {
        Long millis = Long.parseLong(songDuration);
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis)%TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(millis)%TimeUnit.MINUTES.toSeconds(1));
    }

}