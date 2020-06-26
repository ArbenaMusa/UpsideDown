package com.am.upsidedown;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.am.upsidedown.models.Occupation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FeedActivity extends AppCompatActivity {
    private String searchedJob;
    private int jobIndex;
    private List<Occupation> occupationsList;
    private String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        searchedJob = getIntent().getExtras().getString("searched_job");
        jobIndex = getIntent().getExtras().getInt("job_index");

        occupationsList = new ArrayList<>();
        jsonString = loadJSONFromAsset();

        try {
            JSONObject jObj = new JSONObject(jsonString);
            JSONArray occupationsArray = jObj.getJSONArray("occupations");
            for (int i = 0; i < occupationsArray.length(); i++) {
                JSONObject singleObj = occupationsArray.getJSONObject(i);
                occupationsList.add(new Occupation(singleObj.getString("jobName"), singleObj.getString("jobDescription")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getSearchedJob() {
        return searchedJob;
    }

    public int getJobIndex() {
        return jobIndex;
    }

    public List<Occupation> getOccupationsList(){ return occupationsList;}

    /**
     * This function finishes only FeedActivity on back button pressed.
     */
    @Override
    public void onBackPressed() {
        finish();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("occupation.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
