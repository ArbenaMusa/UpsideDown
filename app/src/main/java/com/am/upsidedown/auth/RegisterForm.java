package com.am.upsidedown.auth;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.am.upsidedown.R;

public class RegisterForm extends AppCompatActivity {


    /** @Override
    protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.register_form);

    Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

    ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegisterForm.this,
    android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.role));
    myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    mySpinner.setAdapter(myAdapter);
    }
     **/
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog.setContentView(R.layout.sigup_fragment);
    }

    ImageView imageView;

    public void onclick(View view) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.register_form);

        imageView=(ImageView)dialog.findViewById(R.id.btn_register_close);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
