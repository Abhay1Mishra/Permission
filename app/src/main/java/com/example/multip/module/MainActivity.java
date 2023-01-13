package com.example.multip.module;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.multip.R;
import com.example.permission.permission;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button locBtn;
    String[] Permission;
    private ActivityResultLauncher<String[]> requestPermissionsLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locBtn = findViewById(R.id.locBtn);


        locBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
                if(!permission.checkAndRequestPermissions(MainActivity.this, permissions, 100)){
                }
            }
        });
        requestPermissionsLauncher =
                registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {

                    boolean isGranted = Boolean.TRUE.equals(result.getOrDefault(result, true));
                    if (isGranted) {
                        Toast.makeText(MainActivity.this, "Permissions granted!", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(MainActivity.this, "Permissions denied!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void onButtonClick(View view) {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        if(!permission.checkAndRequestPermissions(this, permissions, 100)){
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (permission.isPermissionsGranted(grantResults)) {

                Toast.makeText(MainActivity.this, "Permissions granted!", Toast.LENGTH_SHORT).show();
            } else {
                permission.CustomDialog(MainActivity.this, "All Permission",
                        "You have denied some permissions", "Go to Setting",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                permission.goToAppSetting(MainActivity.this);

                            }

                        },
                        "Exit App", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        });

                Toast.makeText(MainActivity.this, "Permissions denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}




