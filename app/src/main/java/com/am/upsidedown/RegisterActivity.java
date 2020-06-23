package com.am.upsidedown;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;
    private ImageView userimage;
    private EditText txtName, txtSurname;
    private Spinner userOrWorkman, titleofJob;
    private Button btnRegisterUser;
    private Button btnPick;
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
        btnPick = findViewById(R.id.btnPickImage);

        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pick an image"), GALLERY_REQUEST_CODE);
            }
        });

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

        addItemsOnSpinner2();
        addListenerOnSpinnerItemSelection();
    }

    /**
     * onActivityResult will be called every time we choose an image from gallery
     * add Uri to store image data
     * 
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri imageData = data.getData();

            userimage.setImageURI(imageData);
        }
    }

    // add items into spinner dynimacally
    public void addItemsOnSpinner2() {

        titleofJob = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("Electrician");
        list.add("Plumber");
        list.add("Painter");
        list.add("Housekeeper");
        list.add("Gardener");
        list.add("Chimneysweep");
        list.add("Mechanic");
        list.add("Mjeshter");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        titleofJob.setAdapter(dataAdapter);
    }

    public void addListenerOnSpinnerItemSelection() {
        userOrWorkman = (Spinner) findViewById(R.id.spinner1);
        userOrWorkman.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


}
