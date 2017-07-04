package com.felixtechlabs.mayacare.models;

/**
 * Enquiry model with getters and setters
 * Created by rohan on 4/7/17.
 */

public class Enquiry {

    private String id;

    private String fullName;

    private String email;

    private String mobileNumber;

    private String comments;

    private String city;

    private long enquiryDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getEnquiryDate() {
        return enquiryDate;
    }

    public void setEnquiryDate(long enquiryDate) {
        this.enquiryDate = enquiryDate;
    }
}
