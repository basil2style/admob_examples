package com.makeinfo.admobtest;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends Activity {

    private AdView mAdView;
    InterstitialAd mInterstitialAd;
    CountDownTimer mCountDownTimer;
    Button load,show,bannerAd;

    AdRequest adRequest = new AdRequest.Builder().build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load = (Button) findViewById(R.id.button);
        show = (Button) findViewById(R.id.button2);
        bannerAd = (Button) findViewById(R.id.button3);
        mAdView = (AdView) findViewById(R.id.ad_view);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("YOUR INTERSTITIAL ADUNIT ID");




        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showInterstitial();
                requestNewInterstitial();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInterstitial();
            }
        });

        bannerAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdView.loadAd(adRequest);
            }
        });


    }

    private void requestNewInterstitial() {

               mInterstitialAd.loadAd(adRequest);
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            //startGame();
        }
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
