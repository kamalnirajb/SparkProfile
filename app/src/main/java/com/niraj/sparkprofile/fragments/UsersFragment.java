package com.niraj.sparkprofile.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.util.DataUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.niraj.sparkprofile.R;
import com.niraj.sparkprofile.customui.AppSearchView;
import com.niraj.sparkprofile.databinding.FragmentUsersBinding;
import com.niraj.sparkprofile.models.User;
import com.niraj.sparkprofile.utils.AppConstants;
import com.niraj.sparkprofile.viewmodels.UserItemViewModel;
import com.niraj.sparkprofile.viewmodels.UserViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UsersFragment.OnUserClickListener} interface
 * to handle interaction events.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    private static final String TAG = "UsersFragment";
    AppSearchView searchView;
    RecyclerView recyclerViewUsers;

    private OnUserClickListener onUserClickListener;

    public UsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment UsersFragment.
     */
    public static UsersFragment newInstance() {
        return new UsersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = bind(inflater, container);
        initView(view);
        return view;
    }

    private void initView(View view) {
        searchView = (AppSearchView) view.findViewById(R.id.search_view);
        recyclerViewUsers = (RecyclerView) view.findViewById(R.id.recyclerview_users);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(recyclerViewUsers.getContext()));
        recyclerViewUsers.addItemDecoration(new DividerItemDecoration(recyclerViewUsers.getContext(), DividerItemDecoration.VERTICAL));
    }

    private View bind(LayoutInflater inflater, ViewGroup container) {
        FragmentUsersBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false);
        ObservableArrayList<User> users = new ObservableArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("users");
        // Read from the database
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                AppConstants.showToast(getContext(), "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                AppConstants.logD(TAG, "Failed to read value." + error.toException());
            }
        });
        try {
            for (int i = 0; i < 100; i++) {
                users.add(users.size(), new User(
                        "birthday",
                        "figure",
                        "occupation",
                        "gender",
                        "ethnicity",
                        "realname",
                        "profilepic",
                        "displayname",
                        "religion",
                        "aboutme",
                        "maritalstatus",
                        "location",
                        "height"
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        binding.setUvm(new UserViewModel(users));

        return binding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnUserClickListener) {
            onUserClickListener = (OnUserClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnUserClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onUserClickListener = null;
    }


    public interface OnUserClickListener {
        void onUserClicked();
    }
}
