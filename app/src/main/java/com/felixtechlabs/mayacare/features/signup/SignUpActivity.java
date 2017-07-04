package com.felixtechlabs.mayacare.features.signup;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.customviews.NonSwipingViewPager;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;
import com.felixtechlabs.mayacare.features.dashboard.DashboardActivity;

import butterknife.BindView;

/**
 * Created by rohan on 4/7/17.
 */

public class SignUpActivity extends MCBaseActivity implements SignUpFragmentClickListener, SignUpActivityView {

    @BindView(R.id.vip_sign_up)
    NonSwipingViewPager vipSignUp;

    private SignUpStepsPagerAdapter mSignUpStepsPagerAdapter;

    private SignUpPresenter mSignUpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registration");
        enableHome();
        setUpSignUpForm();
        mSignUpPresenter = SignUpPresenterImpl.getInstance();
        mSignUpPresenter.setSignUpActivityView(this);
    }

    private void setUpSignUpForm() {
        mSignUpStepsPagerAdapter = new SignUpStepsPagerAdapter(getSupportFragmentManager());
        vipSignUp.setAdapter(mSignUpStepsPagerAdapter);
    }


    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    public boolean isToolbarPresent() {
        return true;
    }


    @Override
    public void onNextClick() {
        int current = vipSignUp.getCurrentItem();
        if (current < 3) {
            vipSignUp.setCurrentItem(current + 1);
        }
    }

    @Override
    public void onPreviousClick() {
        int current = vipSignUp.getCurrentItem();
        if (current > 0) {
            vipSignUp.setCurrentItem(current - 1);
        }
    }

    @Override
    public void goToDashboard() {
        start(DashboardActivity.class);
        finish();
    }
}
