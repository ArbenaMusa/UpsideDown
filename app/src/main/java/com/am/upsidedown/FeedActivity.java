package com.am.upsidedown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FeedActivity extends AppCompatActivity {
    private String searchedJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        searchedJob = getIntent().getExtras().getString("searched_job");
    }

    public String getSearchedJob() {
        return searchedJob;
    }

    /**
     * This function finishes only FeedActivity on back button pressed.
     */
    @Override
    public void onBackPressed() {
        finish();
    }
}
