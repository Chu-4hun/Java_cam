package com.example.java_cam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private int CAMERA_CAPTURE;
    Button btn;
    Button btn2;
    VideoView img;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri extras = data.getData();
        img.setVideoURI(extras);
        img.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnTakeVideo);
        btn2 = findViewById(R.id.btnTakePhoto);
        img = findViewById(R.id.img);

        btn.setOnClickListener(view -> {
            try {
                Intent captureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(captureIntent, CAMERA_CAPTURE);
            } catch (ActivityNotFoundException cant) {
                String errorMessage = "Cam not supporting";
                Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        btn2.setOnClickListener(view -> {
            try {
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(captureIntent, CAMERA_CAPTURE);
            } catch (ActivityNotFoundException cant) {
                String errorMessage = "Cam not supporting";
                Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}