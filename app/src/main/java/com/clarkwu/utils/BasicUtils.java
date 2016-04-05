package com.clarkwu.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2016/4/4.
 */
public class BasicUtils {
    private static BasicUtils basicUtils;

    private BasicUtils(){}

    public static BasicUtils getInstance(){
        if(basicUtils == null){
            basicUtils = new BasicUtils();
        }
        return basicUtils;
    }
    public static String getVersionName(Context context){
        if(context == null) return null;
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            String version = packageInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
