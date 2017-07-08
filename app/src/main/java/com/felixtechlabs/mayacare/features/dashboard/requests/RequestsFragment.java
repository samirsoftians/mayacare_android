package com.felixtechlabs.mayacare.features.dashboard.requests;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.db.DatabaseReferenceManager;
import com.felixtechlabs.mayacare.features.dashboard.DashboardBaseFragment;
import com.felixtechlabs.mayacare.models.Request;
import com.felixtechlabs.mayacare.util.MCUtility;
import com.google.firebase.database.Query;

import butterknife.BindView;


/**
 * Created by ftl on 30/6/17.
 */

public class RequestsFragment extends DashboardBaseFragment implements RequestsFragmentCommunicator {


    @BindView(R.id.pb_requests)
    ProgressBar pbRequests;

    @BindView(R.id.txt_error_msg)
    TextView txtErrorMsg;

    @BindView(R.id.rev_requests)
    RecyclerView rvRequests;

    private RequestsAdapter mRequestsAdapter;

    @Override
    protected Fragment getFragment() {
        return this;
    }

    @Override
    protected void start() {
        showProgress();
        loadRequests();
    }

    private void loadRequests() {
        rvRequests.setLayoutManager(new LinearLayoutManager(getActivity()));
        Query requestsQuery = DatabaseReferenceManager.getInstance()
                .getRequestsReference(MCUtility.getUid(getActivity())).orderByChild("requestSortOrder");
        mRequestsAdapter = new RequestsAdapter(Request.class,
                R.layout.item_layout_request, RequestsAdapter.RequestViewHolder.class, requestsQuery,
                getActivity(), this);
        rvRequests.setAdapter(mRequestsAdapter);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_requests;
    }

    @Override
    public void dismissProgress() {
        pbRequests.setVisibility(View.GONE);
    }

    void showProgress() {
        pbRequests.setIndeterminate(true);
        pbRequests.setVisibility(View.VISIBLE);
    }


    @Override
    public void showEmptyView() {
        txtErrorMsg.setText("No requests yet");
        txtErrorMsg.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        txtErrorMsg.setVisibility(View.INVISIBLE);
    }

}
