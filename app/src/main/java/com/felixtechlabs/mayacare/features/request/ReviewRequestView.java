package com.felixtechlabs.mayacare.features.request;

/**
 * Created by rohan on 7/7/17.
 */

public interface ReviewRequestView {

    void showProgress(String message);

    void dismissProgress();

    void handleRequestSuccess(long requestId);

    void handleRequestFailure(String errorMsg);
}
