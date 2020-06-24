package com.am.upsidedown.feed;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.am.upsidedown.FeedActivity;
import com.am.upsidedown.R;

public class FeedFragment extends Fragment {

    private FeedViewModel mViewModel;

    public static FeedFragment newInstance() {
        return new FeedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        CardView electritianCard, plumberCard, painterCard, housekeeperCard, gardenerCard, chimneysweepCard, mechanicCard, mjeshterCard;

        View view = inflater.inflate(R.layout.feed_fragment, container, false);
        electritianCard = view.findViewById(R.id.electrician);
        plumberCard = view.findViewById(R.id.plumber);
        painterCard = view.findViewById(R.id.painter);
        housekeeperCard = view.findViewById(R.id.housekeeper);
        gardenerCard = view.findViewById(R.id.gardener);
        chimneysweepCard = view.findViewById(R.id.chimneysweep);
        mechanicCard = view.findViewById(R.id.mechanic);
        mjeshterCard = view.findViewById(R.id.mjeshter);

        cardViewOnClickListener(electritianCard, "Electritian");
        cardViewOnClickListener(plumberCard, "Plumber");
        cardViewOnClickListener(painterCard, "Painter");
        cardViewOnClickListener(housekeeperCard, "Housekeeper");
        cardViewOnClickListener(gardenerCard, "Gardener");
        cardViewOnClickListener(chimneysweepCard, "Chimneysweep");
        cardViewOnClickListener(mechanicCard, "Mechanic");
        cardViewOnClickListener(mjeshterCard, "Mjeshter");

        return view;
    }

    public void cardViewOnClickListener(CardView cardView, final String extra){
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent feedActivity = new Intent(getActivity(), FeedActivity.class);
                feedActivity.putExtra("searched_job", extra);
                startActivity(feedActivity);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FeedViewModel.class);
        // TODO: Use the ViewModel
    }

}
