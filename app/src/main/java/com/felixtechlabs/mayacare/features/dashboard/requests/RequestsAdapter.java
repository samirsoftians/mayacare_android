package com.felixtechlabs.mayacare.features.dashboard.requests;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.felixtechlabs.mayacare.R;
import com.felixtechlabs.mayacare.models.Request;
import com.felixtechlabs.mayacare.util.MCConstants;
import com.felixtechlabs.mayacare.util.MCUtility;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rohan on 8/7/17.
 */

public class RequestsAdapter extends FirebaseRecyclerAdapter<Request, RequestsAdapter.RequestViewHolder> {


    private Context mContext;

    private HashMap<Integer, Drawable> statusBackgroundMap;

    private RequestsFragmentCommunicator mRequestsFragmentCommunicator;

    /**
     * @param modelClass      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
     *                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public RequestsAdapter(Class<Request> modelClass, int modelLayout,
                           Class<RequestViewHolder> viewHolderClass, Query ref, Context context,
                           RequestsFragmentCommunicator requestsFragmentCommunicator) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mContext = context;
        statusBackgroundMap = new HashMap<>();
        statusBackgroundMap.put(MCConstants.RequestStatusInt.PENDING,
                ContextCompat.getDrawable(mContext, R.drawable.status_pending_background));
        statusBackgroundMap.put(MCConstants.RequestStatusInt.ASSIGNED,
                ContextCompat.getDrawable(mContext, R.drawable.status_accepted_background));
        statusBackgroundMap.put(MCConstants.RequestStatusInt.COMPLETED,
                ContextCompat.getDrawable(mContext, R.drawable.status_completed_background));
        mRequestsFragmentCommunicator = requestsFragmentCommunicator;
    }

    @Override
    protected void populateViewHolder(RequestViewHolder viewHolder, Request request, int position) {
        viewHolder.txtFullName.setText(request.getRequesterName());
        viewHolder.txtRequestId.setText(mContext.getString(R.string.lbl_request_id, request.getRequestId()));
        viewHolder.relStatus.setBackground(statusBackgroundMap.get(request.getStatus()));
        viewHolder.txtRequestDate.setText(MCUtility.getDisplayDate(request.getRequestDateTime()));
        viewHolder.txtRequestTime.setText(MCUtility.getDisplayTime(request.getRequestDateTime()));
        viewHolder.txtRequestStatus.setText(MCUtility.getStatusString(request.getStatus()));
        viewHolder.txtServices.setText(MCUtility.getStringFromList(request.getServices()));
    }

    @Override
    protected void onDataChanged() {
        super.onDataChanged();
        mRequestsFragmentCommunicator.dismissProgress();
        if (getItemCount() == 0) {
            mRequestsFragmentCommunicator.showEmptyView();
        } else {
            mRequestsFragmentCommunicator.hideEmptyView();
        }
    }

    public static class RequestViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_request_id)
        TextView txtRequestId;

        @BindView(R.id.txt_request_date)
        TextView txtRequestDate;

        @BindView(R.id.txt_request_time)
        TextView txtRequestTime;

        @BindView(R.id.txt_request_status)
        TextView txtRequestStatus;

        @BindView(R.id.txt_full_name)
        TextView txtFullName;

        @BindView(R.id.txt_services)
        TextView txtServices;

        @BindView(R.id.rel_status)
        RelativeLayout relStatus;

        public RequestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
