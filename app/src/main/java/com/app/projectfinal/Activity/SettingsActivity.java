package com.app.projectfinal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import fpoly.andoid.test2.R;

public class SettingsActivity extends AppCompatActivity {

    // Ui widgets
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private LinearLayout llFile, llBank, llAddress, llSetNotification, llLanguage, llRate, llIntroduce, llDelUser;
    private Button btnLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();
        // Finding UI widgets
        mToolbar = findViewById(R.id.toolbarSetUser);
        setSupportActionBar(mToolbar);

        // Setting up action bar
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(getResources().getString(R.string.setupDisplay));
        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back));

        clickSetUser();

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            // Respond to the action bar's Up/Home button
//            case android.R.id.home:
//                SettingsActivity.this.onBackPressed();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }


    private void initView() {
        llFile = findViewById(R.id.llFile);
        llBank = findViewById(R.id.llBank);
        llAddress = findViewById(R.id.llAddress);
        llSetNotification = findViewById(R.id.llSetNotification);
        llLanguage = findViewById(R.id.llLanguage);
        llRate = findViewById(R.id.llRate);
        llIntroduce = findViewById(R.id.llIntroduce);
        llDelUser = findViewById(R.id.llDelUser);
        btnLogOut = findViewById(R.id.btnLogOut);

    }

    private void clickSetUser() {
        llFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FileUserActivity.class));
            }
        });
    }

    private void clickSetAddress() {
        llAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FileUserActivity.class));
            }
        });
    }

    private void clickSetBank() {
        llBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FileUserActivity.class));
            }
        });
    }

    private void clickSetNotifications() {
        llSetNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FileUserActivity.class));
            }
        });
    }

    private void clickSetLanguage() {
        llLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FileUserActivity.class));
            }
        });
    }

    private void clickSetRate() {
        llRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FileUserActivity.class));
            }
        });
    }

    private void clickSetIntroduce() {
        llIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FileUserActivity.class));
            }
        });
    }

    private void clickSetDelUser() {
        llDelUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FileUserActivity.class));
            }
        });
    }

    public void LogOut(View view) {
        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
    }
}
