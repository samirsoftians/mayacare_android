package com.felixtechlabs.mayacare.features.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;
import com.felixtechlabs.mayacare.features.staticscreens.AboutUsActivity;
import com.felixtechlabs.mayacare.features.staticscreens.EnquiryFormActivity;
import com.felixtechlabs.mayacare.features.staticscreens.OurFoundersActivity;
import com.felixtechlabs.mayacare.features.staticscreens.OurVolunteersActivity;
import com.felixtechlabs.mayacare.util.MCConstants;
import com.felixtechlabs.mayacare.util.MCDialogUtility;

import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by ftl on 30/6/17.
 */

public class DashboardActivity extends MCBaseActivity {

    @BindView(R.id.nav_drawer)
    NavigationView mNavigationView;

    @BindView(R.id.nav_drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.vip_dashboard_tabs)
    ViewPager vipTabs;

    @BindView(R.id.tbl_dashboard)
    TabLayout tblDashboard;

    private HashMap<Integer, Intent> mNavItemMap;

    private DashboardPagerAdapter mDashboardTabsPagerAdapter;


    private static final int TAB_COUNT = 3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpTabs();
        init();
        setUpNavDrawer();
    }

    /**
     * Initialization method
     */
    private void init() {
        /*Navigation item map, add nav item id and corresponding
        intent for activity to launch*/

        mNavItemMap = new HashMap<>();
        mNavItemMap.put(R.id.nav_item_about_us, getNewIntent(AboutUsActivity.class));
        mNavItemMap.put(R.id.nav_item_enquiry, getNewIntent(EnquiryFormActivity.class));
        mNavItemMap.put(R.id.nav_item_founders, getNewIntent(OurFoundersActivity.class));
        mNavItemMap.put(R.id.nav_item_our_volunteers, getNewIntent(OurVolunteersActivity.class));


    }

    /**
     * Method to setup navigation drawer
     */
    private void setUpNavDrawer() {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.ic_menu);
            mToolbar.setNavigationOnClickListener(v -> mDrawerLayout.openDrawer(Gravity.START));
        }
        mNavigationView.setItemIconTintList(null);
        mNavigationView.setNavigationItemSelectedListener(item -> {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
            if (item.getItemId() == R.id.nav_item_logout) {
                MCDialogUtility.getLogoutDialog(this).show();
            } else {
                startActivity(getIntentForNav(item.getItemId()));
            }
            return true;
        });

    }

    private Intent getIntentForNav(Integer index) {
        return mNavItemMap.get(index);
    }


    private void setUpTabs() {
        mDashboardTabsPagerAdapter = new DashboardPagerAdapter(getSupportFragmentManager());
        vipTabs.setAdapter(mDashboardTabsPagerAdapter);
        tblDashboard.setupWithViewPager(vipTabs, true);


        /*
        Logic to add custom views to tabs for showing icons and titles
        Weak for loop logic
         */

        for (int index = 0; index < TAB_COUNT; index++) {
            TextView tabTextView = (TextView) LayoutInflater.from(this)
                    .inflate(R.layout.layout_dashboard_custom_tab_set, null);
            tabTextView.setText(MCConstants.DASHBOARD_TAB_TITLES[index]);
            tabTextView.setCompoundDrawablesWithIntrinsicBounds(0,
                    MCConstants.DASHBOARD_TAB_ICONS[index], 0, 0);
            tblDashboard.getTabAt(index).setCustomView(tabTextView);
        }

        vipTabs.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitle(MCConstants.DASHBOARD_TAB_TITLES[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_dashboard;
    }

    @Override
    public boolean isToolbarPresent() {
        return true;
    }
}
