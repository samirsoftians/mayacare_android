package com.felixtechlabs.mayacare.features.authentication;

import android.app.Activity;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;
import com.felixtechlabs.mayacare.features.dashboard.DashboardActivity;

import butterknife.OnClick;

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

    @OnClick(R.id.btn_login)
    public void onClickLogin(){
        start(DashboardActivity.class);
    }

    @Override
    public boolean isToolbarPresent() {
        return false;
    }
}
