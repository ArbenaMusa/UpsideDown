package com.am.upsidedown.feed;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.am.upsidedown.FeedActivity;
import com.am.upsidedown.R;
import com.am.upsidedown.models.Occupation;

import java.util.List;


public class Feed1Fragment extends Fragment {
    private String searchedJob;
    private int jobIndex;
    private List<Occupation> occupationList;
    private TextView jobName, jobDescription;
    private ImageView jobImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.feed1_fragment, container, false);

        jobImage = view.findViewById(R.id.feed_description_image);
        jobName = view.findViewById(R.id.job_name);
        jobDescription = view.findViewById(R.id.job_description);

        FeedActivity activity = (FeedActivity) getActivity();
        searchedJob = activity.getSearchedJob();
        jobIndex = activity.getJobIndex();
        occupationList = activity.getOccupationsList();

        setJobImage(searchedJob);
        jobName.setText(occupationList.get(jobIndex).getJobName());
        jobDescription.setText(occupationList.get(jobIndex).getJobDescription());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);
        Button pite = view.findViewById(R.id.search_help);
        pite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_feed1Fragment_to_feed2Fragment);
            }
        });
    }

    private void setJobImage(String searchedJob){
        switch(searchedJob){
            case "Electrician":
                jobImage.setImageResource(R.drawable.electrician_icon);
                break;
            case "Plumber":
                jobImage.setImageResource(R.drawable.plumber_icon);
                break;
            case "Painter":
                jobImage.setImageResource(R.drawable.painter_icon);
                break;
            case "Housekeeper":
                jobImage.setImageResource(R.drawable.housekeeper_icon);
                break;
            case "Gardener":
                jobImage.setImageResource(R.drawable.gardener_icon);
                break;
            case "Chimneysweep":
                jobImage.setImageResource(R.drawable.chimneysweep_icon);
                break;
            case "Mechanic":
                jobImage.setImageResource(R.drawable.mechanic_icon);
                break;
            default:
                jobImage.setImageResource(R.drawable.user);
        }
    }
}
