package com.felixtechlabs.mayacare.features.signup;

/**
 * View interface used to pass control from presenter to fragment
 * Created by rohan on 30/5/17.
 */

public interface AppSignInDetailsFragmentView {

    void showProgress();

    void dismissProgress();

    void updateProgressMessage(String message);

    void handleSignUpFailure(String errorMsg);
}
