package com.niraj.sparkprofile.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.niraj.sparkprofile.GlideApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


/**
 * Created by NirajKumar on 10/31/17.
 */

public class AppConstants {

    private static final String TAG = "AppConstants";

    /**
     * Log method for error
     *
     * @param TAG
     * @param message
     */
    public static void logE(String TAG, String message) {

        Log.e(TAG, message);
    }

    /**
     * Log method for info
     *
     * @param TAG
     * @param message
     */
    public static void logI(String TAG, String message) {

        Log.i(TAG, message);
    }

    /**
     * Log method for debug
     *
     * @param TAG
     * @param message
     */
    public static void logD(String TAG, String message) {

        Log.d(TAG, message);
    }

    /**
     * Show toast messages
     *
     * @param context
     * @param msg
     */
    public static void showToast(Context context, String msg) {

        if (context != null) {
            logE(context.toString(), "Exception in showtoast:" + msg);
            new Handler(Looper.getMainLooper()).post(() -> Toast.makeText(context, msg, Toast.LENGTH_SHORT).show());
        }
    }

    /**
     * Trim the string for extra whitespaces
     *
     * @param value
     * @param defaultValue
     * @return String without white spaces
     */
    public static String trim(String value, String defaultValue) {

        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        return value.trim();
    }


    /**
     * Convert the date from "2017-11-16 11:55:52" to Date String
     *
     * @param datestr
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String convertWSDateTo(String datestr, String neededDateFormat) {

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(datestr);
            SimpleDateFormat sdf = new SimpleDateFormat(neededDateFormat); // Set your date format
            return sdf.format(date);
        } catch (Exception e) {
            AppConstants.logE(TAG, e.getMessage());
        }
        return "";
    }

    /**
     * Make image request and display it in the imageview
     *
     * @param imgView
     * @param imageUrl
     * @param defaultImageResId
     * @param radius
     */
    public static void makeImageRequest(AppCompatImageView imgView, final String imageUrl, final int defaultImageResId, int radius) {

        try {
            if (AppConstants.trim(imageUrl, "").isEmpty()) return;
            RequestOptions requestOptions = (new RequestOptions())
                    .transforms(new FitCenter(), new RoundedCorners(radius))
                    .useAnimationPool(true);

            GlideApp.with(imgView)
                    .load(imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(defaultImageResId)
                    .fallback(defaultImageResId)
                    .error(defaultImageResId)
                    .apply(requestOptions)
                    .fitCenter()
                    .into(imgView);
        } catch (Exception e) {
            AppConstants.logE(TAG, e.getMessage());
        }
    }

    /**
     * Get the list of permissions
     *
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public static String[] getPermissions()
            throws PackageManager.NameNotFoundException {

        ArrayList<String> permissions = new ArrayList<>(Arrays.asList(
                CAMERA,
                ACCESS_FINE_LOCATION,
                ACCESS_COARSE_LOCATION,
                CALL_PHONE,
                RECEIVE_SMS,
                READ_SMS,
                READ_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE));
        return permissions.toArray(new String[0]);
    }

}
