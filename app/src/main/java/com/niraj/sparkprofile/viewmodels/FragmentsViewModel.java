package com.niraj.sparkprofile.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;

import com.niraj.sparkprofile.BR;
import com.niraj.sparkprofile.adapters.HomePagerAdapter;
import com.niraj.sparkprofile.adapters.HomePagerAdapter;
import com.niraj.sparkprofile.models.User;

import java.util.List;

public class FragmentsViewModel extends BaseObservable {

    private static final String TAG = "ScheduleViewModel";

    public ObservableBoolean isLoading = new ObservableBoolean();
    private HomePagerAdapter adapter;
    private ObservableArrayList<Fragment> fragments;

    public void setUp() {
        populateFragments();
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    public FragmentsViewModel(FragmentManager fragmentManager, ObservableArrayList<Fragment> fragments) {
        this.adapter = new HomePagerAdapter(fragmentManager);
        this.fragments = fragments;
    }

    @Bindable
    public HomePagerAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(HomePagerAdapter adapter) {
        this.adapter = adapter;
    }

    @Bindable
    public ObservableArrayList<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(ObservableArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }


    public void populateFragments() {
        isLoading.set(false);
        notifyPropertyChanged(BR.usersList);
    }
}

