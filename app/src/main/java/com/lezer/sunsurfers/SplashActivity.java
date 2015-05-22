package com.lezer.sunsurfers;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by 123 on 21.05.2015.
 */
public class SplashActivity extends Activity {

    private static int SPLASH_SCREEN_TIMEOUT = 2500;
    ImageView im;
    Animation animSplash;
    ProgressBar progressBar;
    boolean b = false;
    int mProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //im = (ImageView) findViewById(R.id.animCircle);
        progressBar = (ProgressBar) findViewById(R.id.progBar);
        animSplash = AnimationUtils.loadAnimation(this, R.anim.animation_splash);

        while (!b) {
            progressBar.setProgress(mProgress);
            mProgress++;
            b = new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }, SPLASH_SCREEN_TIMEOUT);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onAnim(View v) {
        im.startAnimation(animSplash);
    }
}
