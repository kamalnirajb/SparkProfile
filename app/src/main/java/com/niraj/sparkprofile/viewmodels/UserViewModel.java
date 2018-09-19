package com.niraj.sparkprofile.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.support.v7.widget.RecyclerView;

import com.niraj.sparkprofile.BR;
import com.niraj.sparkprofile.adapters.UserAdapter;
import com.niraj.sparkprofile.models.User;

import java.util.List;

public class UserViewModel extends BaseObservable {

    private static final String TAG = "ScheduleViewModel";

    public ObservableBoolean isLoading = new ObservableBoolean();
    private UserAdapter adapter;
    private ObservableArrayList<User> usersList;
    private RecyclerView.OnScrollListener onScrollListener;

    public void setUp() {
        populateUsers();
    }

    public void tearDown() {
        // perform tear down tasks, such as removing listeners
    }

    public UserViewModel(ObservableArrayList<User> users) {
        this.adapter = new UserAdapter();
        this.usersList = users;
    }

    @Bindable
    public UserAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(UserAdapter adapter) {
        this.adapter = adapter;
    }

    @Bindable
    public ObservableArrayList<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(ObservableArrayList<User> usersList) {
        this.usersList = usersList;
    }

    public void populateUsers() {
        isLoading.set(false);
        notifyPropertyChanged(BR.usersList);
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        return onScrollListener;
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public void updateUsers() {
        isLoading.set(false);
        populateUsers();
    }

    /* Needs to be public for Databinding */
    public void onRefresh() {
        isLoading.set(true);
        populateUsers();
    }
}
