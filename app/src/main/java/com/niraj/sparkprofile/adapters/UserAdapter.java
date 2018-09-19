package com.niraj.sparkprofile.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.niraj.sparkprofile.R;
import com.niraj.sparkprofile.databinding.LayoutItemUserBinding;
import com.niraj.sparkprofile.models.User;
import com.niraj.sparkprofile.utils.AppConstants;
import com.niraj.sparkprofile.viewmodels.UserItemViewModel;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private ObservableArrayList<User> usersList;

    public UserAdapter() {
        usersList = new ObservableArrayList<>();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_user, parent, false);
        return new UserViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = usersList.get(position);
        holder.setViewModel(new UserItemViewModel(user));
    }

    @Override
    public int getItemCount() {
        return this.usersList != null ? this.usersList.size() : 0;
    }

    @Override
    public void onViewAttachedToWindow(UserViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(UserViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable ObservableArrayList<User> data) {
        this.usersList = data;
        notifyDataSetChanged();
    }
    
    
    static class UserViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "UserViewHolder";
        LayoutItemUserBinding binding;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            bind();
        }

        @Override
        public String toString() {
            return super.toString();
        }

        public void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
            }
        }

        public void unbind() {
            if (binding != null) {
                binding.unbind();
            }
        }

        public void setViewModel(UserItemViewModel userItemViewModel) {
            if (binding != null) {
                binding.setUivm(userItemViewModel);
                AppConstants.logI(TAG, userItemViewModel.toString());
            }
        }
    }
}
