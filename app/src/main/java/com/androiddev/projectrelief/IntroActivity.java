package com.androiddev.projectrelief;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;

import com.androiddev.projectrelief.R;

import agency.tango.materialintroscreen.MaterialIntroActivity;
import agency.tango.materialintroscreen.SlideFragmentBuilder;
import agency.tango.materialintroscreen.animations.IViewTranslation;

public class IntroActivity extends MaterialIntroActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableLastSlideAlphaExitTransition(true);

        getBackButtonTranslationWrapper()
                .setEnterTranslation(new IViewTranslation() {
                    @Override

                    public void translate(View view, @FloatRange(from = 0, to = 1.0) float percentage) {
                        view.setAlpha(percentage);
                    }
                });

        addSlide(new SlideFragmentBuilder()
                .title("MENTAL HEALTH")


                .image(R.drawable.basic)
                .description("Description 1")
                .backgroundColor(R.color.backgroundPlayer)
                .buttonsColor(R.color.colorPrimaryDark)


                .build());
        addSlide(new SlideFragmentBuilder()
                .title("PHYSICAL HEALTH")

                .image(R.drawable.exercises)
                .description("Description 2")
                .backgroundColor(R.color.backgroundPlayer)
                .buttonsColor(R.color.colorPrimaryDark)


                .build());
        addSlide(new SlideFragmentBuilder()
                .title("HEALING MUSIC")

                .image(R.drawable.music)
                .description("Description 3")
                .backgroundColor(R.color.backgroundPlayer)
                .buttonsColor(R.color.colorPrimaryDark)
                .build());

    }
    @Override
    public void onFinish() {
        super.onFinish();
        Toast.makeText(this, "Try this library in your project! :)", Toast.LENGTH_SHORT).show();
    }

}
