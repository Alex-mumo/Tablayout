package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class SecondActivity extends AppCompatActivity {
    TabLayout layout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        layout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);



        //adapters
        final Adapters adapters = new Adapters(this, getSupportFragmentManager(), layout.getTabCount());
        viewPager.setAdapter(adapters);

        //page listeners
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(layout));

        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //gets the current position of the page
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //layout.addTab(layout.newTab().setText("REGISTER"));
    }
}