package com.hugo.mytest.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.TypedValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 作者：hugo
 * @date 时间：2019/1/4.
 * 版本：v1.0
 * 描述：
 */
public class Utils {
    private static String fileName= "myjson.json";
    //读取方法
    public static String getJson(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * dp to px转换
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context,int dpValue){
        int pxValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
        return pxValue;
    }



}
