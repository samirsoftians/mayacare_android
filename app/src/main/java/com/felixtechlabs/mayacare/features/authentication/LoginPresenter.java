package com.felixtechlabs.mayacare.features.authentication;

/**
 * Presenter interface to call login logic from activity
 * Created by rohan on 31/5/17.
 */

public interface LoginPresenter {

    void login(String email, String password);

    void initiateResetPassword(String email);
}
