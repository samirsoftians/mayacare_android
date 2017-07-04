package com.felixtechlabs.mayacare.features.signup;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.felixtechlabs.mayacare.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * Base SignUp fragment containing all basic fields
 * and methods for signup screens
 * Created by rohan on 29/5/17.
 */

public abstract class SignUpBaseFragment extends Fragment {

    SignUpFragmentClickListener mSignUpFragmentClickListener;

    SignUpPresenter mSignUpPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(getFragment(), rootView);
        mSignUpPresenter = SignUpPresenterImpl.getInstance();
        start();
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mSignUpFragmentClickListener = (SignUpFragmentClickListener) getFragment().getActivity();
    }

    protected abstract Fragment getFragment();

    protected abstract void start();

    protected abstract
    @LayoutRes
    int getLayout();

    protected abstract boolean isFormValid();

    @Optional
    @OnClick(R.id.btn_next)
    void onClickNext() {
        if (isFormValid()) {
            mSignUpFragmentClickListener.onNextClick();
        }
    }

    @Optional
    @OnClick(R.id.btn_previous)
    void onClickPrevious() {
        mSignUpFragmentClickListener.onPreviousClick();
    }


    /**
     * Request focus to edit text
     *
     * @param view - view to request
     */
    protected void requestFocus(View view) {
        if (view.requestFocus()) {
            getFragment().getActivity()
                    .getWindow()
                    .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}
