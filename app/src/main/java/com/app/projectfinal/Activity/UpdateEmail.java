package com.app.projectfinal.Activity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import fpoly.andoid.test2.R;

public class UpdateEmail extends AppCompatActivity {
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);

        mToolbar = findViewById(R.id.toolbarSetFileUser);
        setSupportActionBar(mToolbar);

        // Setting up action bar
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(getResources().getString(R.string.fileUserDisplay));
        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back));
    }
}