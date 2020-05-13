package com.am.upsidedown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;

public class AuthActivity extends AppCompatActivity {

    private TabLayout logTabs;
    private ViewPager authViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        logTabs = (TabLayout) findViewById(R.id.logTabs);
        authViewPager = (ViewPager) findViewById(R.id.logViewpager);

        logTabs.setupWithViewPager(authViewPager);
    }
}
