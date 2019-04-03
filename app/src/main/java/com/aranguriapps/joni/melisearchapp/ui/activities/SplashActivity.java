package com.aranguriapps.joni.melisearchapp.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.aranguriapps.joni.melisearchapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    protected Animation fadeIn;
    @BindView(R.id.iconoaranguri)
    protected ImageView iconoAranguri;
    @BindView(R.id.iconomeli)
    protected ImageView iconoMeli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);

        Thread timerThread = new Thread(){
            public void run() {


                iconoAranguri.startAnimation(fadeIn);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                iconoAranguri.setImageBitmap(null);
                                iconoMeli.startAnimation(fadeIn);
                            }
                        }, 1000);


                    }
                });
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                             cerrarSplash();
                            }
                        }, 3000);


                    }
                });


            }
        };
        timerThread.start();
    }

    private void cerrarSplash() {
        Intent intent =  new Intent(SplashActivity.this,SearchActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left, R.anim.nada);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }


}