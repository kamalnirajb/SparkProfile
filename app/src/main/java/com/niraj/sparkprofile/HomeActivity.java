package com.niraj.sparkprofile;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.niraj.sparkprofile.adapters.HomePagerAdapter;
import com.niraj.sparkprofile.customui.AppTextViewCFS;
import com.niraj.sparkprofile.databinding.ActivityHomeBinding;
import com.niraj.sparkprofile.fragments.ProfileFragment;
import com.niraj.sparkprofile.fragments.UsersFragment;
import com.niraj.sparkprofile.viewmodels.FragmentsViewModel;

import java.util.ArrayList;
import java.util.Arrays;


public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemReselectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener, UsersFragment.OnUserClickListener, ProfileFragment.OnProfileUpdateListener {


    private AppTextViewCFS tvHomeToolbarTitle;

    private ViewPager viewPagerHome;

    private BottomNavigationView bottomNavigationView;

    private HomePagerAdapter adapter;

    private ArrayList<Integer> tabs = new ArrayList<>(
            Arrays.asList(
                    R.id.action_users,
                    R.id.action_profile
            )
    );

    private ArrayList<Integer> tabsTitle = new ArrayList<>(
            Arrays.asList(
                    R.string.app_name,
                    R.string.profile
            )
    );


    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = bind();
        initPager(view);
    }

    /**
     * Bind the view model with views
     *
     * @return
     */
    private View bind() {
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        ObservableArrayList<Fragment> fragments = new ObservableArrayList<>();
        try {
            fragments.add(0, UsersFragment.newInstance());
            fragments.add(1, ProfileFragment.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        binding.setFragmentsvm(new FragmentsViewModel(getSupportFragmentManager(), fragments));
        return binding.getRoot();
    }

    /**
     * Initialize the pager with other UI components
     *
     * @param view
     */
    private void initPager(View view) {
        tvHomeToolbarTitle = view.findViewById(R.id.home_toolbar_title_text);
        viewPagerHome = view.findViewById(R.id.view_pager_home);
        bottomNavigationView = view.findViewById(R.id.bottom_tab_navigation);

        viewPagerHome.setPageTransformer(false, (page, position) -> {
            final float normalizedposition = Math.abs(Math.abs(position) - 1);
            page.setAlpha(normalizedposition);
        });

        bottomNavigationView.setOnNavigationItemReselectedListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.action_users);

        Menu bottomNavigationViewMenu = bottomNavigationView.getMenu();
        bottomNavigationViewMenu.findItem(R.id.action_users).setChecked(true);
        //  bottomNavigationView.setBadge(1, 0);
        viewPagerHome.addOnPageChangeListener(this);
    }


    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {

        int position = tabs.indexOf(menuItem.getItemId());
        if (viewPagerHome != null) {
            viewPagerHome.setCurrentItem(position);
            tvHomeToolbarTitle.setText(tabsTitle.get(position));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int position = tabs.indexOf(menuItem.getItemId());
        if (viewPagerHome != null) {
            viewPagerHome.setCurrentItem(position);
            tvHomeToolbarTitle.setText(tabsTitle.get(position));
        }

        return false;
    }

    @Override
    public void onPageScrolled(int position, float v, int i1) {
        tvHomeToolbarTitle.setText(tabsTitle.get(position));
    }

    @Override
    public void onPageSelected(int position) {

        if (prevMenuItem != null) {
            prevMenuItem.setChecked(false);
        } else {
            bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }

        bottomNavigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = bottomNavigationView.getMenu().getItem(position);
        tvHomeToolbarTitle.setText(tabsTitle.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    @Override
    public void onProfileUpdated() {

    }

    @Override
    public void onUserClicked() {

    }
}
