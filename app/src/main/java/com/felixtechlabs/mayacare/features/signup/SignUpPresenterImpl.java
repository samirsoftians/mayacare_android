package com.felixtechlabs.mayacare.features.signup;

import android.content.Context;
import android.net.Uri;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.db.DatabaseReferenceManager;
import com.felixtechlabs.mayacare.db.StorageReferenceManager;
import com.felixtechlabs.mayacare.models.Consumer;
import com.felixtechlabs.mayacare.util.MCUtility;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Signleton Presenter Implementation for SignUpActivity,
 * does signup and maintains user data across all fragments
 * Created by rohan on 30/5/17.
 */

public class SignUpPresenterImpl implements SignUpPresenter {

    private static SignUpPresenterImpl instance;

    private SignUpActivityView mSignUpActivityView;

    private AppSignInDetailsFragmentView mAppSignInDetailsFragmentView;

    private Consumer mConsumer;

    private Uri profilePictureUri = null;

    private Context mContext;

    private SignUpPresenterImpl() {
        mConsumer = new Consumer();
    }

    /**
     * Getting singleton instance of signup presenter
     *
     * @return - instance
     */
    public static SignUpPresenterImpl getInstance() {
        if (instance == null) {
            instance = new SignUpPresenterImpl();
        }
        return instance;
    }

    /**
     * Method to set up activity view
     *
     * @param signUpActivityView - activity view object
     */
    @Override
    public void setSignUpActivityView(SignUpActivityView signUpActivityView) {
        this.mSignUpActivityView = signUpActivityView;
        this.mContext = (Context) signUpActivityView;
    }

    /**
     * Method to set uid to user model
     *
     * @param uid - Signed up user's uid
     */
    @Override
    public void setUid(String uid) {
        mConsumer.setId(uid);
    }


    /**
     * Method to set personal details to user model
     *
     * @param fullName     - name of user
     * @param gender       - gender of user
     * @param dateOfBirth  - date of birth of user
     * @param mobileNumber - mobile number of user
     */
    @Override
    public void setPersonalDetails(String fullName, String gender, String dateOfBirth,
                                   String mobileNumber) {
        mConsumer.setFullName(fullName);
        mConsumer.setGender(gender);
        mConsumer.setDateOfBirth(dateOfBirth);
        mConsumer.setMobileNumber(mobileNumber);
    }

    /**
     * Method to set address details to user model
     *
     * @param residentialAddress    - address
     * @param pinCode               - pincode
     * @param city                  - city
     * @param alternateMobileNumber - telephone
     */
    @Override
    public void setAddressDetails(String residentialAddress, String pinCode,
                                  String city,
                                  String alternateMobileNumber) {
        mConsumer.setAddress(residentialAddress);
        mConsumer.setPinCode(pinCode);
        mConsumer.setCity(city);
        mConsumer.setAlternateNumber(alternateMobileNumber);
    }


    /**
     * Method to Set Email to user model
     *
     * @param email - email of user
     */
    @Override
    public void setEmail(String email) {
        mConsumer.setEmail(email);
    }

    /**
     * Method to set profile picture url to user model
     *
     * @param profilePictureUrl - ProfilePictureUrl
     */
    @Override
    public void setProfilePictureUrl(String profilePictureUrl) {
        mConsumer.setProfilePictureUrl(profilePictureUrl);
    }

    /**
     * Method to create account on firebase and updating user data
     *
     * @param email    - Email to signup
     * @param password - password
     */
    @Override
    public void startSignUp(String email, String password) {
        //Start signUp with firebase
        mAppSignInDetailsFragmentView.showProgress();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(createAccountTask -> {
                    if (createAccountTask.isSuccessful()) {
                        //Account created getting uid and updating profile information
                        String uid = createAccountTask.getResult().getUser().getUid();
                        mConsumer.setId(uid);
                        if (profilePictureUri != null) {
                            uploadProfilePhoto(uid);
                        } else {
                            updateUserProfile(uid);
                        }
                    } else {
                        //Account creation failed showing error
                        mAppSignInDetailsFragmentView.dismissProgress();
                        String errorMsg = createAccountTask.getException().getMessage();
                        mAppSignInDetailsFragmentView.handleSignUpFailure(errorMsg);
                    }
                });
    }

    /**
     * Method to update user's profile information
     *
     * @param uid - User's uid
     */
    private void updateUserProfile(String uid) {
        mAppSignInDetailsFragmentView.updateProgressMessage(mContext
                .getString(R.string.progress_message_sign_up));
        DatabaseReferenceManager.getInstance().getConsumersReference()
                .child(uid).setValue(mConsumer)
                .addOnCompleteListener(dataUpdateTask -> {
                    mAppSignInDetailsFragmentView.dismissProgress();
                    if (dataUpdateTask.isSuccessful()) {
                        MCUtility.saveConsumer(mContext, mConsumer);
                        mSignUpActivityView.goToDashboard();
                    } else {
                        mAppSignInDetailsFragmentView.dismissProgress();
                        String errorMsg = dataUpdateTask.getException().getMessage();
                        mAppSignInDetailsFragmentView.handleSignUpFailure(errorMsg);
                    }
                });
    }

    /**
     * Method to upload profile photo to firebase and update url into User model
     *
     * @param uid - Uid of user
     */
    @SuppressWarnings("VisibleForTests")
    private void uploadProfilePhoto(String uid) {
        mAppSignInDetailsFragmentView.updateProgressMessage(mContext
                .getString(R.string.progress_message_updating_profile));
        String fileName = uid.concat(profilePictureUri.getLastPathSegment());
        StorageReferenceManager.getInstance().getProfilePhotosReference()
                .child(fileName)
                .putFile(profilePictureUri)
                .addOnCompleteListener(uploadTask -> {
                    if (uploadTask.isSuccessful()) {
                        Uri profilePhotoUri = uploadTask.getResult().getDownloadUrl();
                        if (profilePhotoUri != null) {
                            setProfilePictureUrl(profilePhotoUri.toString());
                        }
                    }
                    updateUserProfile(uid);
                });
    }

    /**
     * Update last fragment view
     *
     * @param appSignInDetailsFragmentView - signIn details fragment view
     */
    @Override
    public void setAppSignInDetailsFragmentView(AppSignInDetailsFragmentView
                                                        appSignInDetailsFragmentView) {
        this.mAppSignInDetailsFragmentView = appSignInDetailsFragmentView;
    }

    @Override
    public void setProfilePictureUri(Uri profilePictureUri) {
        this.profilePictureUri = profilePictureUri;
    }
}
