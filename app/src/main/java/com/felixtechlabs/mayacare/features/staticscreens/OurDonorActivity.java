package com.felixtechlabs.mayacare.features.staticscreens;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.felixtechlabs.mayacare.BuildConfig;
import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ftl on 4/7/17.
 */

public class OurDonorActivity extends MCBaseActivity {

    @BindView(R.id.txt_version)
    TextView txtVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Our Donors");
        enableHome();
        txtVersion.setText(getString(R.string.lbl_version,
                BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
    }

    @OnClick(R.id.txt_click_here)
    void onClickHere() {
        start(DonateActivity.class);
    }


    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_our_donors;
    }

    @Override
    public boolean isToolbarPresent() {
        return true;
    }
}
