package com.app.projectfinal.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import fpoly.andoid.test2.R;

public class ShopActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private TextView add, create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initView();

        setSupportActionBar(mToolbar);

        // Setting up action bar
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(getResources().getString(R.string.setType));
        mActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_back));

        addProduct();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbarShop);
        add = findViewById(R.id.tvAddPr);
        create = findViewById(R.id.tvAddDonHang);
    }

    private void addProduct() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopActivity.this, AddPrActivity.class));
            }
        });
    }
}