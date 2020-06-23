package com.am.upsidedown;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Adapter;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity{

    private static final int GALLERY_REQUEST_CODE = 123;
    private ImageView userimage;
    private EditText txtName, txtSurname;
    private Spinner role, occupation;
    private Button btnRegisterUser;
    private Button btnPick;
    private String email, password;
    private String userRole, userJob;

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
        role = findViewById(R.id.spinner1);
        occupation = findViewById(R.id.spinner2);
        btnRegisterUser = findViewById(R.id.btnregisterUser);
        btnPick = findViewById(R.id.btnPickImage);

        role = (Spinner) findViewById(R.id.spinner1);
        occupation = (Spinner) findViewById(R.id.spinner2);

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
                user.put("role", userRole);
                if (userRole == "Workman") {
                    user.put("occupation", userJob);
                }
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


    /**
     * This method adds dynamically spinner2 when an item in spinner1 is selected
     *
     */
    public void addItemsOnSpinner2(){
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
        dataAdapter.notifyDataSetChanged();
        occupation.setAdapter(dataAdapter);

    }

    public void addListenerOnSpinnerItemSelection() {
        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userRole = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                userJob = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
