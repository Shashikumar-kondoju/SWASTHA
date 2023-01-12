package com.androiddev.projectrelief

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.androiddev.projectrelief.R

class activity_musicplayer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_musicplayer)

        btn_play.setOnClickListener {
            val animation= AnimationUtils.loadAnimation(this,R.anim.rotation)

            img_music.startAnimation(animation)
            btn_play.setImageResource(R.drawable.ic_pause)
    }
}