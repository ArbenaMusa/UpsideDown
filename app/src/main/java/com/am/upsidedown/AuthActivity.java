package com.am.upsidedown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.am.upsidedown.auth.LogInFragment;
import com.am.upsidedown.auth.SignUpFragment;
import com.am.upsidedown.utils.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {

    private TabLayout logTabs;
    private ViewPager authViewPager;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null) setContentView(R.layout.activity_main);
        else setContentView(R.layout.activity_auth);

        logTabs = (TabLayout) findViewById(R.id.logTabs);
        authViewPager = (ViewPager) findViewById(R.id.logViewpager);

        setupViewPager(authViewPager);
        logTabs.setupWithViewPager(authViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        //The Fragment Manager provides the methods used to access the Fragments currently added to the
        //Activity, and to perform Fragment Transactions to add, remove, and replace Fragments.
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LogInFragment(), "Log In");
        adapter.addFragment(new SignUpFragment(), "Sign Up");
        viewPager.setAdapter(adapter);
    }
}
