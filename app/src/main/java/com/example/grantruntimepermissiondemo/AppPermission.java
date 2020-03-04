package com.example.grantruntimepermissiondemo;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;

public class AppPermission
{
    private ArrayList<String> deniedPerms = new ArrayList<String>();
    private ArrayList<String> grantedPerms = new ArrayList<String>();
    private String[] allPerms;

    public Activity mActivity;


    public AppPermission(Activity activity){
        this.mActivity = activity;
    }
    public void reloadPermissions() {
        deniedPerms.clear();
        grantedPerms.clear();
        sortPermissions(getDeclaredPermissions());
    }

    private String[] getDeclaredPermissions() {

        try {
            String packageName= mActivity.getApplicationInfo().packageName;
            PackageInfo pacInfo = mActivity.getPackageManager().getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            allPerms = pacInfo.requestedPermissions;
            return allPerms;
        }
        catch (PackageManager.NameNotFoundException e) {}
        return null;
    }
    private void sortPermissions(String[] permz){

        if (permz != null) {
            for (String info: permz) {
                if (hasPermissionBeenGranted(info)) {
                    grantedPerms.add(info);
                }
                else {
                    deniedPerms.add(info);
                }
            }
        }
    }
    public String[] getAllPermissions(){
        return allPerms;
    }
    public void requestDeniedPermissions(int requestCode) {
        requestPermissions(getDeniedPermissions(), requestCode);
    }
    public void requestPermission(String perm, int req_code){
        requestPermissions(new String[]{perm}, req_code);
    }
    public void requestPermissions(String[] permz, int requestCode){
        if(permz.length>0)
            requestPermissionsInternal(permz, requestCode);
    }
    public String[] getDeniedPermissions() {
        return toStringArray(deniedPerms);
    }
    public String[] getGrantedPermissions() {
        return toStringArray(grantedPerms);
    }
    private class PermissionRequestError extends Error{
        private PermissionRequestError(String msg){
            super(msg);
        }
    }
    private void requestPermissionsInternal(String[] permzName, int requestCode) throws PermissionRequestError{
        if(permzName == null || permzName.length <=0 ){
            throw new PermissionRequestError("Permission list is empty or null... Are you sure to have declared permission in manifest?");
        }
        mActivity.requestPermissions(permzName, requestCode);
    }

    private String[] toStringArray(ArrayList<String> list) {
        String[] permz=new String[list.size()];
        for (int i=0; i < permz.length; i++) {
            permz[i] = list.get(i);
        }
        return permz;
    }
    protected boolean hasPermissionBeenGranted(String perm) {
        if (mActivity.checkSelfPermission(perm) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }
}
