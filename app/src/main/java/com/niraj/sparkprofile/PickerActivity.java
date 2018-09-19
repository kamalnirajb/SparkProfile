package com.niraj.sparkprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.niraj.sparkprofile.customui.AppSearchView;

public class PickerActivity extends AppCompatActivity {

    AppSearchView searchView;
    RecyclerView recyclerViewCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);
    }
}
