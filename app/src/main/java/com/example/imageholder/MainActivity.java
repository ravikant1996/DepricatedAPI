package com.example.imageholder;

import static com.example.imageholder.Constraints.DIR_NAME;
import static com.example.imageholder.Constraints.FILE_NAME;
import static com.example.imageholder.Constraints.NOT_CONNECTED;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.imageholder.ImageSaver.ImageSaver;
import com.example.imageholder.NetworkUtil.BroadcastListener;
import com.example.imageholder.NetworkUtil.ConnectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private Button download;
    private ImageView imageView;
    private ProgressDialog progressDialog;
    private String src = "https://picsum.photos/1000/1500";
    Bitmap myBitmap;

    private BroadcastReceiver MyReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        download = findViewById(R.id.download);
        imageView = findViewById(R.id.imageView);
        MyReceiver = new BroadcastListener();
        registerReceiver(MyReceiver, new IntentFilter());

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConnectionUtils.isConnected(MainActivity.this)) {
                    downloadingImage();
                } else {
                    Toast.makeText(MainActivity.this, NOT_CONNECTED, Toast.LENGTH_SHORT).show();
                }
            }
        });

        try {
            myBitmap = new ImageSaver(MainActivity.this).
                    setFileName(FILE_NAME).
                    setDirectoryName(DIR_NAME).
                    load();
            imageView.setImageBitmap(myBitmap);
        } catch (NullPointerException r) {
            //
        }
    }

    private void downloadingImage() {

        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = new ProgressDialog(MainActivity.this);
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.show();
                    }
                });
                try {
                    java.net.URL url = new java.net.URL(src);
                    HttpURLConnection connection = (HttpURLConnection) url
                            .openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    myBitmap = BitmapFactory.decodeStream(input);
                    new ImageSaver(MainActivity.this).
                            setFileName(FILE_NAME).
                            setDirectoryName(DIR_NAME).
                            save(myBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                        imageView.setImageBitmap(myBitmap);
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}