package com.example.grantruntimepermissiondemo;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppPermissionCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            setContentView(R.layout.activity_main);
            if (Build.VERSION.SDK_INT >= 23) {
                reloadPermissions();
                requestDeniedPermissions(0);
            }
        }
    }

    @Override
    protected void onPermissionsResult(HashMap<String, Integer> permissions, int requestResult) {
        // TODO: Implement this method
        super.onPermissionsResult(permissions, requestResult);
        //Handle permission results
        Set<String> set=permissions.keySet();
        for(String s:set){
            //a Permission has been denied
            if(permissions.get(s)== PackageManager.PERMISSION_DENIED){
                requestPermission(s, 0);
            }
        }
    }
}
