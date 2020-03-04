package com.example.grantruntimepermissiondemo;

import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

public class AppPermissionCompatActivity extends AppCompatActivity {
    AppPermission mAppPermission = new AppPermission(this);

    public void reloadPermissions() {
        mAppPermission.reloadPermissions();
    }
    public String[] getAllPermissions() {
        return mAppPermission.getAllPermissions();
    }
    public void requestDeniedPermissions(int requestCode) {
        mAppPermission.requestDeniedPermissions(requestCode);
    }
    public void requestPermission(String perm, int req_code) {
        mAppPermission.requestPermission(perm, req_code);
    }
    public String[] getDeniedPermissions() {
        return mAppPermission.getDeniedPermissions();
    }
    public String[] getGrantedPermissions() {
        return mAppPermission.getGrantedPermissions();
    }

    @Override
    public final void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        // TODO: Implement this method
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        HashMap<String, Integer> perms= new HashMap<String, Integer>(permissions.length);
        for (int i=0; i < permissions.length; i++) {
            perms.put(permissions[i], grantResults[i]);
        }
        onPermissionsResult(perms, requestCode);
    }

    /**
     implement this method to get permission result call-backs
     */
    protected void onPermissionsResult(HashMap<String, Integer>  permissions, int requestResult) {

    }
}
