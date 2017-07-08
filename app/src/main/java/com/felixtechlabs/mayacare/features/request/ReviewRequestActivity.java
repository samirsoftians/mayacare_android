package com.felixtechlabs.mayacare.features.request;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.features.base.MCBaseActivity;
import com.felixtechlabs.mayacare.models.Request;
import com.felixtechlabs.mayacare.util.MCConstants;
import com.felixtechlabs.mayacare.util.MCDialogUtility;
import com.felixtechlabs.mayacare.util.MCUtility;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by rohan on 7/7/17.
 */

public class ReviewRequestActivity extends MCBaseActivity implements ReviewRequestView {

    @BindView(R.id.txt_full_name)
    TextView txtFullName;

    @BindView(R.id.txt_mobile_number)
    TextView txtMobileNumber;

    @BindView(R.id.txt_email)
    TextView txtEmail;

    @BindView(R.id.txt_address)
    TextView txtAddress;

    @BindView(R.id.txt_services)
    TextView txtServices;

    @BindView(R.id.txt_date_time)
    TextView txtDateTime;

    @BindView(R.id.txt_comments)
    TextView txtComments;

    private Request mRequest;

    private ProgressDialog mProgressDialog;

    private ReviewRequestPresenter mReviewRequestPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequest = (Request) getIntent().getSerializableExtra(MCConstants.KEY_REQUEST_OBJECT);
        if (mRequest == null) {
            finish();
            return;
        }
        mReviewRequestPresenter = new ReviewRequestPresenterImpl(this);
        enableHome();
        setTitle("Review Request");
        showData();
    }

    private void showData() {
        txtFullName.setText(mRequest.getRequesterName());
        txtMobileNumber.setText(mRequest.getRequesterMobile());
        txtEmail.setText(mRequest.getRequesterEmail());
        txtAddress.setText(mRequest.getRequesterAddress());
        txtServices.setText(MCUtility.getStringFromList(mRequest.getServices()));
        txtComments.setText(mRequest.getComments());
        txtDateTime.setText(MCUtility.getDisplayDateTime(mRequest.getRequestDateTime()));
    }

    @OnClick(R.id.btn_edit)
    void onClickEdit() {
        Intent requestIntent = getNewIntent(NewRequestActivity.class);
        requestIntent.putExtra(MCConstants.KEY_REQUEST_OBJECT, mRequest);
        startActivity(requestIntent);
        finish();
    }

    @OnClick(R.id.btn_confirm)
    void onClickConfirm() {
        mReviewRequestPresenter.submitRequest(mRequest);
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_review_request;
    }

    @Override
    public boolean isToolbarPresent() {
        return true;
    }

    @Override
    public void showProgress(String message) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void dismissProgress() {
        if (mProgressDialog != null && !isFinishing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void handleRequestSuccess(long requestId) {
        MCDialogUtility
                .getSingleButtonInfoDialog(this,
                        getString(R.string.dialog_request_confirm, requestId),
                        (dialog, which) -> finish()).show();
    }

    @Override
    public void handleRequestFailure(String errorMsg) {
        showToast(errorMsg);
    }

    @Override
    public void onBackPressed() {
        onClickEdit();
    }
}
