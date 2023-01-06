package com.example.multip.module;
import static com.example.permission.AlertDia.CustomDialog;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.multip.R;
import com.example.permission.AlertDia;
import com.example.permission.PermissionUtil;

public class MainActivity extends AppCompatActivity {
Button LocBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocBtn =findViewById(R.id.locBtn);


        LocBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (PermissionUtil.hasPermission(MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)&&PermissionUtil.hasPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(MainActivity.this, "Both permission Granted", Toast.LENGTH_SHORT).show();
            }else {
                if (PermissionUtil.shouldShowRational(MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)||PermissionUtil.shouldShowRational(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)){
                    AlertDia.CustomDialog(MainActivity.this,"Location Permission", "This app required Location permission", "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                PermissionUtil.goToAppSetting(MainActivity.this);
                            }

                        },"Cancel",null);
                }else {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},100);
                }
            }

        }
    });

    }


}