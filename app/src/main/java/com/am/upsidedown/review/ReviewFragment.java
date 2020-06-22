package com.am.upsidedown.review;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
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

import com.am.upsidedown.R;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ReviewFragment extends Fragment {

    private ReviewViewModel mViewModel;
    private RatingBar mRatingBar;
    private TextView mRatingScale;
    private EditText mFeedback;
    private Button mSendFeedback;
    private Snackbar snackbar;
    private int stars;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private String userId;

    private static final String TAG = "ReviewFragment";

    public static ReviewFragment newInstance() {
        return new ReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_fragment, container, false);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        snackbar = Snackbar.make(view, R.string.review_quote, Snackbar.LENGTH_INDEFINITE);
        snackbar.setDuration(5000);
        snackbar.show();

        mRatingBar = view.findViewById(R.id.ratingBar);
        mRatingScale = view.findViewById(R.id.tvRatingScale);
        mFeedback = view.findViewById(R.id.etFeedback);
        mSendFeedback = view.findViewById(R.id.btnSubmit);

        selectRatingStars();
        sendFeedback();

        return view;
    }

    /**
     * This method displays rating text based on the number of stars that have been selected.
     */
    public void selectRatingStars(){
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText(R.string.one);
                        stars = 1;
                        break;
                    case 2:
                        mRatingScale.setText(R.string.two);
                        stars = 2;
                        break;
                    case 3:
                        mRatingScale.setText(R.string.three);
                        stars = 3;
                        break;
                    case 4:
                        mRatingScale.setText(R.string.four);
                        stars = 4;
                        break;
                    case 5:
                        mRatingScale.setText(R.string.five);
                        stars = 5;
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });
    }

    /**
     * This method checks if the feedback field is filled.
     */
    public void sendFeedback(){
        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), R.string.feedback_error, Toast.LENGTH_LONG).show();
                    Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.blink_anim);
                    mSendFeedback.startAnimation(animation);
                } else {
                    DocumentReference documentReference = firestore.collection("feedback").document(userId);
                    Map<String, Object> user = new HashMap<>();
                    user.put("stars", stars);
                    user.put("feedback", mFeedback.getText().toString());
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "Feedback from " + userId);
                            Toast.makeText(getActivity(), R.string.feedback_message, Toast.LENGTH_SHORT).show();
                            Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fadein);
                            mSendFeedback.startAnimation(animation);
                            mFeedback.setText("");
                            mRatingBar.setRating(0);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "on failure: " + e.toString());
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ReviewViewModel.class);

        // TODO: Use the ViewModel
    }
}

