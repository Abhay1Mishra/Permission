package com.example.permission;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.fragment.app.Fragment;

public class PermissionUtil {
    public static boolean useRunTimePermission(){
        return Build.VERSION.SDK_INT>=Build.VERSION_CODES.R;
    }
    public static boolean hasPermission(Activity activity ,String permission){
        if(useRunTimePermission()){
            return activity.checkSelfPermission(permission)== PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }
    public static void requestPermission(Activity activity ,String[] permission , int requestCode){
        if(useRunTimePermission()){
             activity.requestPermissions(permission, requestCode);
        }
    }

    public static boolean shouldShowRational(Activity activity, String permission){
        if (useRunTimePermission()){
            return activity.shouldShowRequestPermissionRationale(permission);
        }
        return false;
    }
    public static boolean shouldAskForPermission(Activity activity,String permission){
        if (useRunTimePermission()){
            return !hasPermission(activity,permission) &&
                    (!hasPermission(activity,permission)
                            || shouldShowRational(activity,permission));
        }
        return false;
    }
        public  static void goToAppSetting(Activity activity){
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package",activity.getPackageName(),null));
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }

}
