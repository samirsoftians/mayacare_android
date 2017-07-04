package com.felixtechlabs.mayacare.features.authentication;

import android.content.Context;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.db.DatabaseReferenceManager;
import com.felixtechlabs.mayacare.models.Consumer;
import com.felixtechlabs.mayacare.util.MCUtility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Presenter for login, containing login related business logic
 * Created by rohan on 31/5/17.
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView mLoginView;

    private FirebaseAuth mFirebaseAuth;

    private Context mContext;

    public LoginPresenterImpl(LoginView loginView) {
        mLoginView = loginView;
        mContext = (Context) loginView;
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    /**
     * Method to initiate firebase login
     *
     * @param email    - email of user
     * @param password - password of user
     */
    @Override
    public void login(String email, String password) {
        mLoginView.showProgress(mContext.getString(R.string.progress_message_login));
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(authResultTask -> {
                    if (authResultTask.isSuccessful()) {
                        String uid = authResultTask.getResult().getUser().getUid();
                        MCUtility.saveUID(mContext, uid);
                        loadUserDetails(uid);
                    } else {
                        mLoginView.dismissProgress();
                        String errorMessage = authResultTask.getException().getMessage();
                        mLoginView.handleLoginFailure(errorMessage);
                    }
                });
    }

    @Override
    public void initiateResetPassword(String email) {
        mLoginView.showProgress(mContext.getString(R.string.progress_message_reset));
        mFirebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            mLoginView.dismissProgress();
            if (task.isSuccessful()) {
                mLoginView.handleForgotPasswordCall(mContext.getString(R.string.message_email_sent));
            } else {
                String errorMsg = task.getException().getMessage();
                mLoginView.handleForgotPasswordCall(errorMsg);
            }
        });
    }

    /**
     * Method to load user details after login like name and photo url
     *
     * @param uid - User's uid
     */
    private void loadUserDetails(String uid) {
        mLoginView.updateProgressMsg(mContext.getString(R.string.progress_message_loading_profile));
        DatabaseReferenceManager.getInstance().getConsumersReference()
                .child(uid)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Consumer consumer = dataSnapshot.getValue(Consumer.class);
                        MCUtility.saveUserDetails(mContext, consumer);
                        if (mLoginView != null && mContext != null) {
                            mLoginView.dismissProgress();
                            mLoginView.handleLoginSuccess();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        mLoginView.dismissProgress();
                        String errorMessage = databaseError.getMessage();
                        mLoginView.handleLoginFailure(errorMessage);
                    }
                });
    }
}
