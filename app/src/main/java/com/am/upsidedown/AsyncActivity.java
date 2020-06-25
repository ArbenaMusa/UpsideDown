package com.am.upsidedown;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncActivity extends AppCompatActivity {
    URL ImageUrl = null;
    InputStream is = null;
    Bitmap bmImg = null;
    ImageView imageView = null;
    TextView story;
    ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        Button button = findViewById(R.id.asyncTask);
        imageView = findViewById(R.id.image);
        story = findViewById(R.id.story);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskExample asyncTask = new AsyncTaskExample();
                asyncTask.execute("https://fsb.zobj.net/crop.php?r=h3o6PRwH92gkHSgvk6qaq55KC0I2ZbA3yby_inKlIIH5nugPKHg1uxLiddDG2E70XQW_nY2kU8O-AdJPq4YsImzZXZzIg73i-TJLzwLUpsKCv-n-dRpVg8uoNfoqycfK9gKsIcUN3mI7gpXJ");
            }
        });
    }

    private class AsyncTaskExample extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(AsyncActivity.this);
            p.setMessage("Please wait...Until we get the best written version of our story!");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                ImageUrl = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) ImageUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                is = conn.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                bmImg = BitmapFactory.decodeStream(is, null, options);
                Thread.sleep(7000);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            return bmImg;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (imageView != null) {
                p.hide();
                imageView.setImageBitmap(bitmap);
                story.setText("Because of the pandemic .... bllah bllah bllah");
            } else {
                p.show();
            }
        }
    }
}
