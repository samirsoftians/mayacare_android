package com.felixtechlabs.mayacare.features.signup;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.widget.EditText;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.util.MCUtility;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Fragment accepting user's app signIn details while signUp,
 * fragment calling actual signup
 * TODO: Remove hardcoded error messages
 * Created by rohan on 29/5/17.
 */

public class AppSignInDetailsFragment extends SignUpBaseFragment implements
        AppSignInDetailsFragmentView {

    @BindView(R.id.edt_email)
    EditText edtEmail;

    @BindView(R.id.il_email)
    TextInputLayout ilEmail;

    @BindView(R.id.il_password)
    TextInputLayout ilPassword;

    @BindView(R.id.edt_password)
    EditText edtPassword;

    @BindView(R.id.edt_confirm_password)
    EditText edtConfirmPassword;

    @BindView(R.id.il_confirm_password)
    TextInputLayout ilConfirmPassword;

    private ProgressDialog mProgressDialog;

    @Override
    protected Fragment getFragment() {
        return this;
    }

    @Override
    protected void start() {
        mSignUpPresenter.setAppSignInDetailsFragmentView(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_app_signin_details;
    }

    @OnClick(R.id.btn_register)
    void onClickRegister() {
        if (isFormValid()) {
            mSignUpPresenter.setEmail(edtEmail.getText().toString().trim());
            mSignUpPresenter.startSignUp(edtEmail.getText().toString().trim(),
                    edtPassword.getText().toString().trim());
        }
    }

    @Override
    protected boolean isFormValid() {
        String email = edtEmail.getText().toString().trim();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ilEmail.setError("Enter valid e-mail");
            requestFocus(ilEmail);
            return false;
        } else {
            ilEmail.setErrorEnabled(false);
        }

        String password = edtPassword.getText().toString().trim();
        if (password.isEmpty()) {
            ilPassword.setError("Enter Password");
            requestFocus(edtPassword);
            return false;
        } else {
            ilPassword.setErrorEnabled(false);
        }
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (confirmPassword.isEmpty()) {
            ilConfirmPassword.setError("Enter password again");
            requestFocus(edtConfirmPassword);
            return false;
        } else {
            ilConfirmPassword.setErrorEnabled(false);
        }

        if (!confirmPassword.equals(password)) {
            ilConfirmPassword.setError("Passwords do not match");
            requestFocus(edtConfirmPassword);
            return false;
        } else {
            ilConfirmPassword.setErrorEnabled(false);
        }

        return true;
    }

    @Override
    public void showProgress() {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(getString(R.string.progress_message_create_account));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if (mProgressDialog != null && !getActivity().isFinishing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void updateProgressMessage(String message) {
        if (mProgressDialog != null) {
            mProgressDialog.setMessage(message);
        }
    }

    @Override
    public void handleSignUpFailure(String errorMsg) {
        MCUtility.showToast(getActivity(), errorMsg);
    }
}
