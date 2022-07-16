package com.app.projectfinal;

import android.os.Bundle;
import android.view.MenuItem;
;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout ;
    private ViewPager2 viewPager ;
    private ViewPagerAdapter viewPagerAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout,viewPager,(tab, position) -> {
                switch (position){
                    case 0 :
                        tab.setText("");
                        break;
                    case 1 :
                        tab.setText("");
                        break;
                    case 2 :
                        tab.setText("");
                        break;
                }
        }).attach();
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.navigation_Notification:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.navigation_User:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

    }

}