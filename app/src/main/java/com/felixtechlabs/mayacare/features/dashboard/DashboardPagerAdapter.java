package com.felixtechlabs.mayacare.features.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by ftl on 30/6/17.
 */

public class DashboardPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragmentsList;

    private ArrayList<String> mFragmentTitleList;

    public DashboardPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentsList = new ArrayList<>();
        mFragmentsList.add(new HomeFragment());
        mFragmentsList.add(new MyRequestFragment());
        mFragmentsList.add(new ProfileFragmant());

        mFragmentTitleList = new ArrayList<>();
        mFragmentTitleList.add("Home");
        mFragmentTitleList.add("My Requests");
        mFragmentTitleList.add("Profile");
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentsList.size();
    }
}
