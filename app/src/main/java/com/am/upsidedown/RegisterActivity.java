package com.am.upsidedown;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.am.upsidedown.auth.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private ImageView userimage;
    private EditText txtName, txtSurname;
    private Spinner userOrWorkman, titleofJob;
    private Button btnRegisterUser;
    private String email, password;
    private String name, surname, workmanOrUser, occupation;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;

    private static final String USER = "users";
    private User user;
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

        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USER);
        mAuth = FirebaseAuth.getInstance();

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getIntent().getExtras() != null) {
                    email = getIntent().getExtras().getString("email");
                }
//                name = txtName.getText().toString();
//                surname = txtSurname.getText().toString();
//                workmanOrUser = userOrWorkman.getAdapter().toString();
//                occupation = titleofJob.getAdapter().toString();
//                user = new User(name, surname, email);
//
//                FirebaseUser fireuser = mAuth.getCurrentUser();
                Intent intent = new Intent (RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
