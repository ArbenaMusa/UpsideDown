package com.am.upsidedown.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.am.upsidedown.R;

public class LogInFragment extends Fragment {

    private EditText etEmail, etPassword;
    private Button logInButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etEmail = view.findViewById(R.id.setEmail);
        etPassword = view.findViewById(R.id.setPassword);
        logInButton = view.findViewById(R.id.log_in_btn);

        // Inflate the layout for this fragment
        return view;
    }

}
