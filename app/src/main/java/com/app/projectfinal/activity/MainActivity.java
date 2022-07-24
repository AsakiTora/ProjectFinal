package com.app.projectfinal.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.app.projectfinal.adapter.ViewPagerAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import com.app.projectfinal.R;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;
    private AHBottomNavigation bottomNavigation;
    private ViewPager2 viewPager2;
    private ActionBar actionBar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_carts:
                        startActivity(new Intent(MainActivity.this, CartActivity.class));
                        Log.e("TAG", "onOptionsItemSelected: abc" );
                        return true;
                    default:
                        return false;
                }
            }
        });
        bottomNavigation = findViewById(R.id.AHBottomNavigation);
        viewPager2 = findViewById(R.id.ViewPager);
        viewPager2.setAdapter(new ViewPagerAdapter(this));

        AHBottomNavigationItem home = new AHBottomNavigationItem(R.string.home, R.drawable.custom_drawable_bottom_nav_home, R.color.green);
        AHBottomNavigationItem notification = new AHBottomNavigationItem(R.string.notification, R.drawable.custom_drawable_bottom_nav_notifications, R.color.green);
        AHBottomNavigationItem user = new AHBottomNavigationItem(R.string.personal, R.drawable.custom_drawable_bottom_nav_user, R.color.green);

        bottomNavigation.addItem(home);
        bottomNavigation.addItem(notification);
        bottomNavigation.addItem(user);

        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#1CAE81"));
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#1CAE81"));


        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager2.setCurrentItem(position);
                bottomNavigation.setIconActiveColor(position, R.color.green);
                bottomNavigation.setTitleActiveColor(position, R.color.green);
                return true;
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigation.setCurrentItem(position);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_carts:
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                Log.e("TAG", "onOptionsItemSelected: abc" );
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void countNotification(int count) {
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(count))
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.red))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white))
                .build();
        bottomNavigation.setNotification(notification, 1);
    }

}
