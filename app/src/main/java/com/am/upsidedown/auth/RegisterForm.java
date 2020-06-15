package com.am.upsidedown.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.am.upsidedown.MainActivity;
import com.am.upsidedown.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterForm extends AppCompatActivity {

    private ImageView closeRegisterForm, userimage;
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
        setContentView(R.layout.register_form);

        closeRegisterForm = findViewById(R.id.btn_register_close);
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
                    password = getIntent().getExtras().getString("password");
                }
                name = txtName.getText().toString();
                surname = txtSurname.getText().toString();
                workmanOrUser = userOrWorkman.getAdapter().toString();
                occupation = titleofJob.getAdapter().toString();
                user = new User(name, surname, email, password, workmanOrUser, occupation);

                FirebaseUser fireuser = mAuth.getCurrentUser();
                updateUI(fireuser);
            }
        });
    }

    /**
     * This method adds user information to database and redirect to main activity
     *
     * @param fireuser
     */
    public void updateUI(FirebaseUser fireuser) {
        String keyid = mDatabase.push().getKey();
        mDatabase.child(keyid).setValue(fireuser);
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }


    //=== SPINNER DROPDOWN LIST ===
    /**
     * Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
     * <p>
     * ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegisterForm.this,
     * android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.role));
     * myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     * mySpinner.setAdapter(myAdapter);
     * }
     **/
//    Dialog dialog;

    /** @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    dialog.setContentView(R.layout.sigup_fragment);
    }

    ImageView imageView;

    public void onclick(View view) {

    final Dialog dialog = new Dialog(this);
    dialog.setContentView(R.layout.register_form);

    imageView=(ImageView)dialog.findViewById(R.id.btn_register_close);
    imageView.setOnClickListener(new View.OnClickListener() {
    @Override public void onClick(View v) {
    dialog.dismiss();
    }
    });
    dialog.show();
     **/
}


