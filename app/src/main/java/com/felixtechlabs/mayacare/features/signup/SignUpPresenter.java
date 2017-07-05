package com.felixtechlabs.mayacare.features.signup;

import android.net.Uri;

/**
 * Presenter interface connecting activity to presenter implementation
 * Created by rohan on 30/5/17.
 */

public interface SignUpPresenter {

    void setSignUpActivityView(SignUpActivityView signUpActivityView);

    void setUid(String uid);

    void setPersonalDetails(String fullName, String gender, String dateOfBirth,
                            String mobileNumber);

    void setAddressDetails(String residentialAddress, String pinCode,
                           String city, String alternateMobileNumber);

    void setEmail(String email);

    void setProfilePictureUrl(String profilePictureUrl);

    void startSignUp(String email, String password);

    void setAppSignInDetailsFragmentView(AppSignInDetailsFragmentView appSignInDetailsFragmentView);

    void setProfilePictureUri(Uri profilePictureUri);
}
