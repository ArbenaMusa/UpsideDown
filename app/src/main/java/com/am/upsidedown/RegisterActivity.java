package com.am.upsidedown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.am.upsidedown.auth.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private ImageView userimage;
    private EditText txtName, txtSurname;
    private Spinner userOrWorkman, titleofJob;
    private Button btnRegisterUser;
    private String email, password;
    private String name, surname, workmanOrUser, occupation;

    private FirebaseAuth mAuth;
    private FirebaseFirestore firestore;
    private String userId;

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userimage = findViewById(R.id.image);
        txtName = findViewById(R.id.txtname);
        txtSurname = findViewById(R.id.txtsurname);
        userOrWorkman = findViewById(R.id.spinner1);
        titleofJob = findViewById(R.id.spinner2);
        btnRegisterUser = findViewById(R.id.btnregisterUser);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = firestore.collection("users").document(userId);
                Map<String, Object> user = new HashMap<>();
                if (getIntent().getExtras() != null) {
                    email = getIntent().getExtras().getString("email");
                }
                user.put("name", txtName.getText().toString());
                user.put("surname", txtSurname.getText().toString());
                user.put("email", email);
                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "User profile created for " + userId);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "on failure: " + e.toString());
                    }
                });
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
