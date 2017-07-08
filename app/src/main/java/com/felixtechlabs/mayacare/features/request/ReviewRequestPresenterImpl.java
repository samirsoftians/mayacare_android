package com.felixtechlabs.mayacare.features.request;

import android.content.Context;

import com.felixtechlabs.mayacare.db.DatabaseReferenceManager;
import com.felixtechlabs.mayacare.models.Request;
import com.felixtechlabs.mayacare.util.MCUtility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

/**
 * Created by rohan on 7/7/17.
 */

public class ReviewRequestPresenterImpl implements ReviewRequestPresenter {

    private ReviewRequestView mReviewRequestView;

    private long requestId;

    private Context mContext;

    public ReviewRequestPresenterImpl(ReviewRequestView reviewRequestView) {
        mReviewRequestView = reviewRequestView;
        mContext = (Context) reviewRequestView;
    }

    @Override
    public void submitRequest(Request request) {
        mReviewRequestView.showProgress("Submitting Request...");
        generateId(request);
    }

    private void generateId(Request request) {
        DatabaseReferenceManager.getInstance().getRequestIdReference().runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                long currentId = (long) mutableData.getValue();
                requestId = currentId + 1;
                mutableData.setValue(requestId);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean committed, DataSnapshot dataSnapshot) {
                if (committed) {
                    request.setRequestId(requestId);
                    request.setRequestSortOrder(-1 * requestId);
                    uploadRequest(request);
                } else {
                    mReviewRequestView.dismissProgress();
                    mReviewRequestView.handleRequestFailure(databaseError.getMessage());
                }
            }
        });
    }

    private void uploadRequest(Request request) {
        DatabaseReference requestReference = DatabaseReferenceManager.getInstance()
                .getRequestsReference(MCUtility.getUid(mContext));
        String key = requestReference.push().getKey();
        request.setRequestKey(key);
        requestReference.child(key).setValue(request).addOnCompleteListener(task -> {
            mReviewRequestView.dismissProgress();
            if (task.isSuccessful()) {
                mReviewRequestView.handleRequestSuccess(requestId);
            } else {
                mReviewRequestView.handleRequestFailure(task.getException().getMessage());
            }
        });
    }
}
