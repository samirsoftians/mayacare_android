package com.felixtechlabs.mayacare.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Request model with getters and setters
 * Created by rohan on 4/7/17.
 */

public class Request implements Serializable {

    private long requestId;

    private String requestKey;

    private String requesterName;

    private String requesterEmail;

    private String requesterMobile;

    private String requesterAddress;

    private String requesterId;

    private long requestDateTime;

    private ArrayList<String> services;

    private long requestedAt;

    private String comments;

    private int status;

    private String assignedToUid;

    private long requestSortOrder;


    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public String getRequestKey() {
        return requestKey;
    }

    public void setRequestKey(String requestKey) {
        this.requestKey = requestKey;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getRequesterEmail() {
        return requesterEmail;
    }

    public void setRequesterEmail(String requesterEmail) {
        this.requesterEmail = requesterEmail;
    }

    public String getRequesterMobile() {
        return requesterMobile;
    }

    public void setRequesterMobile(String requesterMobile) {
        this.requesterMobile = requesterMobile;
    }

    public String getRequesterAddress() {
        return requesterAddress;
    }

    public void setRequesterAddress(String requesterAddress) {
        this.requesterAddress = requesterAddress;
    }

    public String getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(String requesterId) {
        this.requesterId = requesterId;
    }

    public long getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(long requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public ArrayList<String> getServices() {
        return services;
    }

    public void setServices(ArrayList<String> services) {
        this.services = services;
    }

    public long getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(long requestedAt) {
        this.requestedAt = requestedAt;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAssignedToUid() {
        return assignedToUid;
    }

    public void setAssignedToUid(String assignedToUid) {
        this.assignedToUid = assignedToUid;
    }

    public long getRequestSortOrder() {
        return requestSortOrder;
    }

    public void setRequestSortOrder(long requestSortOrder) {
        this.requestSortOrder = requestSortOrder;
    }
}
