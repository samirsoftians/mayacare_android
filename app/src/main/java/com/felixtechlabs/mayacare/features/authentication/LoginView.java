package com.felixtechlabs.mayacare.features.authentication;

/**
 * View interface to pass control from presenter to activity
 * Created by rohan on 31/5/17.
 */

public interface LoginView {

    void showProgress(String msg);

    void updateProgressMsg(String msg);

    void dismissProgress();

    void handleLoginSuccess();

    void handleLoginFailure(String errorMsg);

    void handleForgotPasswordCall(String msg);
}
