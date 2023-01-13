package com.example.permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class permission {
    public static boolean useRunTimePermission(){
        return Build.VERSION.SDK_INT>=Build.VERSION_CODES.R;
    }
    public static boolean checkAndRequestPermissions(Activity activity, String[] permissions, int requestCode) {
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity, permissionList.toArray(new String[permissionList.size()]), requestCode);
            return false;
        }
        return true;
    }

    public static boolean isPermissionsGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
    public  static void goToAppSetting(Activity activity){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package",activity.getPackageName(),null));
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }
    public static void CustomDialog(Context context, String title, String message, String positiveBtnTitle, DialogInterface.OnClickListener positiveListener, String negativeBtnTitle, DialogInterface.OnClickListener negativeListener ){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveBtnTitle,positiveListener);
        builder.setCancelable(false);
        builder.setNegativeButton(negativeBtnTitle,negativeListener);
        builder.create().show();
    }

}
