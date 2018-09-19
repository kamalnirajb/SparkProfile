package com.niraj.sparkprofile;

import android.databinding.DataBindingUtil;
import android.support.multidex.MultiDexApplication;

import com.niraj.sparkprofile.binding.AppDataBindingComponent;

public class SparkProfileApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        DataBindingUtil.setDefaultComponent(new AppDataBindingComponent());
    }
}
