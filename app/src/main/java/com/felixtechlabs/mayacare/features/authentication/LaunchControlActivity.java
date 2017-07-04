package com.felixtechlabs.mayacare.features.authentication;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.ActivityCompat;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;
import com.felixtechlabs.mayacare.features.dashboard.DashboardActivity;
import com.felixtechlabs.mayacare.util.MCConstants;
import com.felixtechlabs.mayacare.util.MCUtility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Launch control activity deciding which activity to start
 * Created by rohan on 20/6/17.
 */

public class LaunchControlActivity extends MCBaseActivity {

    FirebaseAuth mFirebaseAuth;

    private int PERMISSION_ALL_REQ_CODE = 101;

    private String[] PERMISSIONS_NEEDED = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAuth = FirebaseAuth.getInstance();
        /*
        Check permission and redirect else request for the same
         */
        if (MCUtility.hasPermissions(this, PERMISSIONS_NEEDED)) {
            handleRedirection();
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS_NEEDED, PERMISSION_ALL_REQ_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ALL_REQ_CODE && (grantResults.length > 0) &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            handleRedirection();
        } else {
            showToast(getString(R.string.permission_not_granted_msg));
            finishAffinity();
        }
    }

    private void handleRedirection() {
        new Handler().postDelayed(() -> {
            /*
             * Logic to check whether user is already logged in or not
             * if logged in go to dashboard else show login activity
             */
            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
            if (firebaseUser == null) {
                start(LoginActivity.class);
                finish();
            } else {
                start(DashboardActivity.class);
                finish();
            }
        }, MCConstants.LAUNCH_CONTROL_DELAY_MILLIS);
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_launch_control;
    }

    @Override
    public boolean isToolbarPresent() {
        return false;
    }
}
