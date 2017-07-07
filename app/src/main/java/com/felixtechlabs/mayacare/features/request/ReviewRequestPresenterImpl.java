package com.felixtechlabs.mayacare.features.request;

/**
 * Created by rohan on 7/7/17.
 */

public class ReviewRequestPresenterImpl implements ReviewRequestPresenter {

    private ReviewRequestView mReviewRequestView;

    public ReviewRequestPresenterImpl(ReviewRequestView reviewRequestView) {
        mReviewRequestView = reviewRequestView;
    }
}
