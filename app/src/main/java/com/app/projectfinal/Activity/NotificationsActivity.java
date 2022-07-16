package com.app.projectfinal.Activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import fpoly.andoid.test2.R;

public class NotificationsActivity extends AppCompatActivity {
    // Ui widgets
    private Toolbar mToolbar;
    private  ActionBar mActionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        // Finding UI widgets
        mToolbar = findViewById(R.id.notifications_toolbar);
        setSupportActionBar(mToolbar);

        //  Setting up the action bar
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(getResources().getString(R.string.notifications_title));
        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp));


    }
}
