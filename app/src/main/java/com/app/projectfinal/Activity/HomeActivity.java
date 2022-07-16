package com.app.projectfinal.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.app.projectfinal.Adapter.ViewPagerAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import com.app.projectfinal.R;

public class HomeActivity extends AppCompatActivity {

    FragmentManager manager;
    private AHBottomNavigation bottomNavigation;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        bottomNavigation = findViewById(R.id.AHBottomNavigation);
        viewPager2 = findViewById(R.id.ViewPager);
        viewPager2.setAdapter(new ViewPagerAdapter(this));

        AHBottomNavigationItem home = new AHBottomNavigationItem(R.string.title_home, R.drawable.custom_drawable_bottom_nav_home, R.color.green);
        AHBottomNavigationItem notification = new AHBottomNavigationItem(R.string.title_notifications, R.drawable.ic_notifications_full, R.color.green);
        AHBottomNavigationItem user = new AHBottomNavigationItem(R.string.title_user, R.drawable.custom_drawable_bottom_nav_user, R.color.green);

        bottomNavigation.addItem(home);
        bottomNavigation.addItem(notification);
        bottomNavigation.addItem(user);

        bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#39C1FF"));
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#39C1FF"));


        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager2.setCurrentItem(position);
                bottomNavigation.setIconActiveColor(position, R.color.red);
                bottomNavigation.setTitleActiveColor(position, R.color.red);
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
    public void countCart(int count) {
        AHNotification notification = new AHNotification.Builder()
                .setText(String.valueOf(count))
                .setBackgroundColor(ContextCompat.getColor(HomeActivity.this, R.color.red))
                .setTextColor(ContextCompat.getColor(HomeActivity.this, R.color.white))
                .build();
        bottomNavigation.setNotification(notification, 1);
    }

}
