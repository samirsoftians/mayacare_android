package com.felixtechlabs.mayacare.features.staticscreens;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toolbar;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;
import com.felixtechlabs.mayacare.util.MCConstants;

import butterknife.BindView;

/**
 * Created by ftl on 3/7/17.
 */

public class AboutUsActivity extends MCBaseActivity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("About Us");
        enableHome();

    }



    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    public boolean isToolbarPresent() {
        return true;
    }
}
