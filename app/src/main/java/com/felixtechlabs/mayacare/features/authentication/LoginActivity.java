package com.felixtechlabs.mayacare.features.authentication;

import android.app.Activity;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;

/**
 * Created by rohan on 27/6/17.
 */

public class LoginActivity extends MCBaseActivity {

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public boolean isToolbarPresent() {
        return false;
    }
}
