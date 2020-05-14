package com.am.upsidedown.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.am.upsidedown.R;

public class CustomDialog extends Dialog {

    TextView txtclose;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    /**
     * This method sets the dialog's view content,
     * handles the close button action
     * and returns the dialog object.
     *
     * @param layout
     * @return
     */
    public Dialog setLayout(int layout) {
        this.setContentView(layout);
        txtclose = (TextView) this.findViewById(R.id.dialog_close);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        this.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                getOwnerActivity().finish();
            }
        });

        this.setCanceledOnTouchOutside(false);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return this;
    }
}
