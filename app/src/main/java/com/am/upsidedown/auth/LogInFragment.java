package com.am.upsidedown.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import com.am.upsidedown.MainActivity;
import com.am.upsidedown.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInFragment extends Fragment {

    private FirebaseAuth mAuth;
    private EditText etEmail, etPassword;
    private Button logInButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();

        etEmail = view.findViewById(R.id.setEmail);
        etPassword = view.findViewById(R.id.setPassword);
        logInButton = view.findViewById(R.id.log_in_btn);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    /**
     * This method authenticates the user using FirebaseAuth
     * and redirects to MainActivity if login succeeds.
     *
     * @param email
     * @param password
     */
    private void loginUserAccount(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getActivity(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getActivity(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Login successful!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            intent.putExtra("isRegistered",  true);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            Toast.makeText(getActivity(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
