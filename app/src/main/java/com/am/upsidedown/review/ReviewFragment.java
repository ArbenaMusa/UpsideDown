package com.am.upsidedown.review;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.am.upsidedown.MainActivity;
import com.am.upsidedown.R;

import com.google.android.material.snackbar.Snackbar;

public class ReviewFragment extends Fragment {

    private ReviewViewModel mViewModel;

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;

    Snackbar snackbar;


    public static ReviewFragment newInstance() {
        return new ReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_fragment, container, false);

        snackbar = Snackbar.make(view, "Welcome and thank you for rating our app!", Snackbar.LENGTH_INDEFINITE);
        snackbar.setDuration(5000);
        snackbar.show();

        mRatingBar = view.findViewById(R.id.ratingBar);
        mRatingScale = view.findViewById(R.id.tvRatingScale);
        mFeedback = view.findViewById(R.id.etFeedback);
        mSendFeedback = view.findViewById(R.id.btnSubmit);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("Very bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });
        return view;

       

       /** mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(ReviewFragment.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                } else {
                    mFeedback.setText("");
                    mRatingBar.setRating(0);
                    Toast.makeText(ReviewFragment.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
        **/

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReviewViewModel.class);

        // TODO: Use the ViewModel
    }

}

