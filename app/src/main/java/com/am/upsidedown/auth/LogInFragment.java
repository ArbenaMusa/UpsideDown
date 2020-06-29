package com.am.upsidedown.auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class LogInFragment extends Fragment implements TextWatcher, CompoundButton.OnCheckedChangeListener {

    private FirebaseAuth mAuth;
    private EditText etEmail, etPassword;
    private Button logInButton;

    private CheckBox rememberMe;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_REMEMBER = "remember";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASS = "password";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        mAuth = FirebaseAuth.getInstance();

        etEmail = view.findViewById(R.id.setEmail);
        etPassword = view.findViewById(R.id.setPassword);
        logInButton = view.findViewById(R.id.log_in_btn);
        rememberMe = view.findViewById(R.id.saveLoginCheckBox);

        sharedPreferences = getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getBoolean(KEY_REMEMBER, false))
            rememberMe.setChecked(true);
        else
            rememberMe.setChecked(false);

        etEmail.setText(sharedPreferences.getString(KEY_EMAIL, ""));
        etPassword.setText(sharedPreferences.getString(KEY_PASS, ""));

        etEmail.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
        rememberMe.setOnCheckedChangeListener(this);

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
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            Toast.makeText(getActivity(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    /**
     * These methods are from implementation of TextWatcher
     * and OnCheckedChangedListener
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        managePrefs();

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        managePrefs();

    }

    /**
     * This method check whether the checkbox is checked
     * if is checked save values in keys
     * if it is not then remove values from keys
     *
     */
    private void managePrefs() {
        if (rememberMe.isChecked()) {
            editor.putString(KEY_EMAIL, etEmail.getText().toString().trim());
            editor.putString(KEY_PASS, etPassword.getText().toString().trim());
            editor.putBoolean(KEY_REMEMBER, true);
            editor.apply();
        } else {
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS); //editor.putString(KEY_PASS, "");
            editor.remove(KEY_EMAIL); //editor.putString(KEY_EMAIL, "");
            editor.apply();
        }
    }
}
