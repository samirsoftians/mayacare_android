package com.felixtechlabs.mayacare.features.authentication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;

/**
 * Launch control activity deciding which activity to start
 * Created by rohan on 20/6/17.
 */

public class LaunchControlActivity extends MCBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * Start the needed activity from here
         */
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_launch_control;
    }

    @Override
    public boolean isToolbarPresent() {
        return false;
    }
}
