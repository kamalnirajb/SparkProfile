package com.niraj.sparkprofile.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.niraj.sparkprofile.models.User;

public class UserItemViewModel extends BaseObservable {

    private User user;
    private View.OnClickListener onClickListener;

    public UserItemViewModel(User user) {
        this.user = user;
    }

    public void setUp() {
    }

    public void tearDown() {
    }

    @Bindable
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Bindable
    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
