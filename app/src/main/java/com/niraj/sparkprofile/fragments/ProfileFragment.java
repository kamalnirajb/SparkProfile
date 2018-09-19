package com.niraj.sparkprofile.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.niraj.sparkprofile.R;
import com.niraj.sparkprofile.databinding.FragmentProfileBinding;
import com.niraj.sparkprofile.models.User;
import com.niraj.sparkprofile.utils.AppConstants;
import com.niraj.sparkprofile.viewmodels.UserItemViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnProfileUpdateListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {


    private OnProfileUpdateListener onProfileUpdateListener;
    private FragmentProfileBinding binding;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProfileFragment.
     */
    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = bind(inflater, container);
        return view;
    }

    private View bind(LayoutInflater inflater, ViewGroup container) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        UserItemViewModel uivm = new UserItemViewModel(new User(
                "16-01-1989",
                "Average",
                "Software Engineer",
                "Male",
                "Indian",
                "Niraj Kumar",
                "",
                "Niraj",
                "Hindu",
                "A friend of yours in need.",
                "Single",
                "Chennai",
                "Average"
        ));
        uivm.setOnClickListener(this);

        binding.setUivm(uivm);
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnProfileUpdateListener) {
            onProfileUpdateListener = (OnProfileUpdateListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnProfileUpdateListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onProfileUpdateListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save: {
                saveUser();
            }
            break;
        }
    }

    private void saveUser() {
        String displayName = AppConstants.trim(binding.etDisplayName.getText().toString(), "");
        String realName = AppConstants.trim(binding.etRealName.getText().toString(), "");
        String birthday = AppConstants.trim(binding.etBirthday.getText().toString(), "");
        String figure = AppConstants.trim(binding.etFigure.getText().toString(), "");
        String height = AppConstants.trim(binding.etHeight.getText().toString(), "");
        String ethnicity = AppConstants.trim(binding.etEthnicity.getText().toString(), "");
        String religion = AppConstants.trim(binding.etReligion.getText().toString(), "");
        String location = AppConstants.trim(binding.etLocation.getText().toString(), "");
        String aboutme = AppConstants.trim(binding.etAboutMe.getText().toString(), "");
        String gender = AppConstants.trim(binding.etGender.getText().toString(), "");
        String occupation = AppConstants.trim(binding.etOccupation.getText().toString(), "");
        String maritalStatus = AppConstants.trim(binding.etMaritalStatus.getText().toString(), "");

        if (displayName.isEmpty()) {
            AppConstants.showToast(getContext(), getString(R.string.display_name_empty));
            return;
        }
        if (realName.isEmpty()) {
            AppConstants.showToast(getContext(), getString(R.string.real_name_empty));
            return;
        }
        if (birthday.isEmpty()) {
            AppConstants.showToast(getContext(), getString(R.string.birthday_empty));
            return;
        }
        if (gender.isEmpty()) {
            AppConstants.showToast(getContext(), getString(R.string.gender_empty));
            return;
        }
        if (ethnicity.isEmpty()) {
            AppConstants.showToast(getContext(), getString(R.string.ethnicity_emptry));
            return;
        }
        if (religion.isEmpty()) {
            AppConstants.showToast(getContext(), getString(R.string.religion_empty));
            return;
        }
        if (occupation.isEmpty()) {
            AppConstants.showToast(getContext(), getString(R.string.occupation_empty));
            return;
        }
        if (height.isEmpty()) {
            AppConstants.showToast(getContext(), getString(R.string.height_empty));
            return;
        }
        if (figure.isEmpty()) {
            AppConstants.showToast(getContext(), getString(R.string.figure_emptry));
            return;
        }

        if (maritalStatus.isEmpty()) {
            AppConstants.showToast(getContext(), getString(R.string.marital_status_emptry));
            return;
        }
        if (aboutme.isEmpty()) {
            AppConstants.showToast(getContext(), getString(R.string.aboutme_empty));
            return;
        }
        if (location.isEmpty()) {
            AppConstants.showToast(getContext(), getString(R.string.location_empty));
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference("users");

        dbRef.setValue(new User(
                birthday,
                figure,
                occupation,
                gender,
                ethnicity,
                realName,
                "",
                displayName,
                religion,
                aboutme,
                maritalStatus,
                location,
                height
        ), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                AppConstants.showToast(getActivity().getApplicationContext(), "User information saved..." + databaseError.getDetails());
            }
        });

    }

    public interface OnProfileUpdateListener {
        void onProfileUpdated();
    }
}
