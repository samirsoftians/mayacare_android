package com.felixtechlabs.mayacare.features.authentication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;
import com.felixtechlabs.mayacare.features.dashboard.DashboardActivity;
import com.felixtechlabs.mayacare.features.signup.SignUpActivity;
import com.felixtechlabs.mayacare.util.MCDialogUtility;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by rohan on 27/6/17.
 */

public class LoginActivity extends MCBaseActivity implements LoginView {


    @BindView(R.id.edt_email)
    EditText edtEmail;

    @BindView(R.id.edt_password)
    EditText edtPassword;

    private LoginPresenter mLoginPresenter;

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginPresenter = new LoginPresenterImpl(this);
    }

    @OnClick(R.id.btn_login)
    void onClickLogin() {
        if (isFormValid()) {
            mLoginPresenter.login(edtEmail.getText().toString().trim(),
                    edtPassword.getText().toString().trim());
        }
    }

    @OnClick(R.id.txt_forgot_password)
    void onClickForgot() {
        MCDialogUtility.getResetPasswordDialog(this, (dialog, input) -> {
            String email = input.toString().trim();
            if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showToast("Enter valid email");
            } else {
                dialog.dismiss();
                mLoginPresenter.initiateResetPassword(email);
            }
        });
    }

    private boolean isFormValid() {
        String email = edtEmail.getText().toString().trim();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError(getString(R.string.val_msg_valid_email));
            return false;
        }

        if (edtPassword.getText().toString().trim().isEmpty()) {
            edtPassword.setError(getString(R.string.val_msg_empty_password));
            return false;
        }

        return true;
    }

    @OnClick(R.id.btn_registration)
    void onClickSignUp() {
        start(SignUpActivity.class);
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public boolean isToolbarPresent() {
        return false;
    }

    @Override
    public void showProgress(String msg) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(msg);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void updateProgressMsg(String msg) {
        if (mProgressDialog != null) {
            mProgressDialog.setMessage(msg);
        }
    }

    @Override
    public void dismissProgress() {
        if (mProgressDialog != null && !isFinishing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void handleLoginSuccess() {
        start(DashboardActivity.class);
        finish();
    }

    @Override
    public void handleLoginFailure(String errorMsg) {
        showToast(errorMsg);
    }

    @Override
    public void handleForgotPasswordCall(String msg) {
        showToast(msg);
    }
}
