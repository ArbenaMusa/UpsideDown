package com.am.upsidedown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
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

        setContentView(R.layout.activity_auth);
        if(mAuth.getCurrentUser() != null){
            Intent innerActivity = new Intent(AuthActivity.this, MainActivity.class);
            startActivity(innerActivity);
            finish();
        }

        logTabs = (TabLayout) findViewById(R.id.logTabs);
        authViewPager = (ViewPager) findViewById(R.id.logViewpager);

        setupViewPager(authViewPager);
        logTabs.setupWithViewPager(authViewPager);
    }

    /**
     * This method displays tabs' fragments on ViewPager using ViewPagerAdapter
     * and provides the methods used to perform Fragment Transactions (add, remove, replace fragments) through Fragment Manager.
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LogInFragment(), "Log In");
        adapter.addFragment(new SignUpFragment(), "Sign Up");
        viewPager.setAdapter(adapter);
    }
}
