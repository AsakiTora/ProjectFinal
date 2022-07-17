package com.app.projectfinal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.app.projectfinal.R;
import com.app.projectfinal.utils.CheckStateNetwork;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadData();
    }

    private void loadData() {
        if (CheckStateNetwork.isNetworkAvailable(this)){
            //Network connected
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }, 3000);
        } else {
            //Network disconnected
            Toast.makeText(this, "Network disconnected", Toast.LENGTH_SHORT).show();
        }
    }
}