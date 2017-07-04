package com.felixtechlabs.mayacare.features.signup;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.HashMap;

/**
 * ViewPager adapter class to make swiping form
 * Created by rohan on 28/5/17.
 */

public class SignUpStepsPagerAdapter extends FragmentStatePagerAdapter {

    HashMap<Integer, Fragment> positionFragmentMap;

    public SignUpStepsPagerAdapter(FragmentManager fm) {
        super(fm);
        positionFragmentMap = new HashMap<>();
        positionFragmentMap.put(0, new PersonalDetailsFragment());
        positionFragmentMap.put(1, new AddressDetailsFragment());
        positionFragmentMap.put(2, new AppSignInDetailsFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return positionFragmentMap.get(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
