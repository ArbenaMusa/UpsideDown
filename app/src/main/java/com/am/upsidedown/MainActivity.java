package com.am.upsidedown;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.am.upsidedown.chat.ChatFragment;
import com.am.upsidedown.utils.CustomDialog;
import com.am.upsidedown.utils.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private TabLayout tabs;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabs = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);
    }

    /**
     * This method displays tabs' fragments on ViewPager using ViewPagerAdapter
     * and provides the methods used to perform Fragment Transactions (add, remove, replace fragments) through Fragment Manager.
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ChatFragment().newInstance(), "Chats");
        viewPager.setAdapter(adapter);
    }

    /**
     * This method detects user interaction with the side menu,
     * pops up the preferences dialog
     * and provides logout action handler.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.side_menu_settings:
                CustomDialog preferencesDialog = (CustomDialog) new CustomDialog(this)
                        .setLayout(R.layout.dialog_preference);
                preferencesDialog.show();
                return true;
            case R.id.side_menu_logout:
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, AuthActivity.class));
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * This method inflates side_menu resource.
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.side_menu, menu);
        return true;
    }
}
