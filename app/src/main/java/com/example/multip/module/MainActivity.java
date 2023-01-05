package com.example.multip.module;

import static com.example.multip.permissionUtil.PermissionUtil.goToAppSetting;
import static com.example.multip.permissionUtil.PermissionUtil.hasPermission;
import static com.example.multip.permissionUtil.PermissionUtil.shouldShowRational;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.multip.R;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
Button locBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locBtn =findViewById(R.id.locBtn);


    locBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (hasPermission(MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)&&hasPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(MainActivity.this, "Both permission Granted", Toast.LENGTH_SHORT).show();
            }else {
                if (shouldShowRational(MainActivity.this,Manifest.permission.ACCESS_COARSE_LOCATION)||shouldShowRational(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)){
                        CustomDialog("Location Permission", "This app required Location permission", "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                goToAppSetting(MainActivity.this);
                            }

                        },"Cancel",null);
                }else {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},100);
                }
            }

        }
    });

    }

void CustomDialog(String title, String message,String positiveBtnTitle, DialogInterface.OnClickListener positiveListener,String negativeBtnTitle,DialogInterface.OnClickListener negativeListener ){
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    builder.setTitle(title);
    builder.setMessage(message);
    builder.setPositiveButton(positiveBtnTitle,positiveListener);
    builder.setNegativeButton(negativeBtnTitle,negativeListener);
    builder.create().show();
}
}