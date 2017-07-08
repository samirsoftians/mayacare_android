package com.felixtechlabs.mayacare.features.dashboard;

import android.support.v4.app.Fragment;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.request.NewRequestActivity;

import butterknife.OnClick;

/**
 * Created by ftl on 30/6/17.
 */

public class HomeFragment extends DashboardBaseFragment {


    @Override
    protected Fragment getFragment() {
        return this;
    }

    @Override
    protected void start() {

    }

    @OnClick({R.id.txt_request, R.id.imv_request})
    void onClickRequest() {
        startNewScreen(NewRequestActivity.class);
    }

    @OnClick({R.id.txt_services, R.id.imv_services})
    void onClickServices() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }
}
