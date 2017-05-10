package net.ingramintegrations.interstitialadexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    Button displayAdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayAdButton = (Button) findViewById(R.id.act_main_bt_display_ad);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                goNextActivity();
            }
        });

        requestNewInterstitial();

        displayAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAd();
            }
        });
    }

    private void displayAd() {


        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            goNextActivity();
        }


    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("DEVICE_ID_EMULATOR")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void goNextActivity() {
        // After Ad, go to next Activity
        Toast.makeText(this, "Going to next Actvity.", Toast.LENGTH_SHORT).show();
    }
}
