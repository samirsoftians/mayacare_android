package com.felixtechlabs.mayacare.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v13.app.ActivityCompat;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.felixtechlabs.mayacare.features.authentication.LaunchControlActivity;
import com.felixtechlabs.mayacare.models.Consumer;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility class having all app wide utility methods
 * Created by rohan on 20/6/17.
 */

public class MCUtility {

    /**
     * Utility method to show toast
     *
     * @param context - context of activity
     * @param msg     - message to show
     */
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Get checked radio button text from radio group
     *
     * @param radioButtonGroup - radio group
     * @return - String text of radio button
     */
    public static String getCheckedRadioText(RadioGroup radioButtonGroup) {
        int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
        View radioButtonView = radioButtonGroup.findViewById(radioButtonID);
        int idx = radioButtonGroup.indexOfChild(radioButtonView);
        RadioButton radioButton = (RadioButton) radioButtonGroup.getChildAt(idx);
        return radioButton.getText().toString();
    }

    /**
     * Saving Uid to preferences
     *
     * @param context - Context of calling activity
     * @param uid     - Uid to store
     */
    public static void saveUID(Context context, String uid) {
        MCPreferences.get(context)
                .saveString(MCPreferences.Keys.USER_UID, uid);
    }

    /**
     * Getting uid from preferences
     *
     * @param context - Context of calling activity
     * @return - String Uid
     */
    public static String getUid(Context context) {
        return MCPreferences.get(context)
                .getString(MCPreferences.Keys.USER_UID);
    }

    /**
     * Saving full name to preferences
     *
     * @param context  - Context of calling activity
     * @param fullName - Full name to store
     */
    public static void saveFullName(Context context, String fullName) {
        MCPreferences.get(context)
                .saveString(MCPreferences.Keys.USER_FULL_NAME, fullName);
    }

    /**
     * Getting full name from preferences
     *
     * @param context - Context of calling activity
     * @return - String full name
     */
    public static String getFullName(Context context) {
        return MCPreferences.get(context)
                .getString(MCPreferences.Keys.USER_FULL_NAME);
    }


    /**
     * Saving email to preferences
     *
     * @param context - Context of calling activity
     * @param email   - Full name to store
     */
    public static void saveEmail(Context context, String email) {
        MCPreferences.get(context)
                .saveString(MCPreferences.Keys.USER_EMAIL, email);
    }

    /**
     * Getting full name from preferences
     *
     * @param context - Context of calling activity
     * @return - String full name
     */
    public static String getEmail(Context context) {
        return MCPreferences.get(context)
                .getString(MCPreferences.Keys.USER_EMAIL);
    }

    /**
     * Saving mobile to preferences
     *
     * @param context - Context of calling activity
     * @param mobile  - Full name to store
     */
    public static void saveMobile(Context context, String mobile) {
        MCPreferences.get(context)
                .saveString(MCPreferences.Keys.USER_MOBILE, mobile);
    }

    /**
     * Getting mobile from preferences
     *
     * @param context - Context of calling activity
     * @return - String mobile
     */
    public static String getMobile(Context context) {
        return MCPreferences.get(context)
                .getString(MCPreferences.Keys.USER_MOBILE);
    }

    /**
     * Saving address to preferences
     *
     * @param context - Context of calling activity
     * @param address - Full address to store
     */
    public static void saveAddress(Context context, String address) {
        MCPreferences.get(context)
                .saveString(MCPreferences.Keys.USER_ADDRESS, address);
    }

    /**
     * Getting address from preferences
     *
     * @param context - Context of calling activity
     * @return - String address
     */
    public static String getAddress(Context context) {
        return MCPreferences.get(context)
                .getString(MCPreferences.Keys.USER_ADDRESS);
    }

    /**
     * Saving profile photo url from preferences
     *
     * @param context         - Context of calling activity
     * @param profilePhotoUrl - profile photo url to store
     */
    public static void saveProfilePhotoUrl(Context context, String profilePhotoUrl) {
        MCPreferences.get(context)
                .saveString(MCPreferences.Keys.USER_PROFILE_PHOTO_URL, profilePhotoUrl);
    }

    /**
     * Getting profile photo url from preferences
     *
     * @param context - Context of calling activity
     * @return - String profile photo url
     */
    public static String getProfilePhotoUrl(Context context) {
        return MCPreferences.get(context)
                .getString(MCPreferences.Keys.USER_PROFILE_PHOTO_URL);
    }

    /**
     * Save all the user details at once
     *
     * @param context  - caller activity
     * @param consumer - User object
     */
    public static void saveConsumer(Context context, Consumer consumer) {
        saveUID(context, consumer.getId());
        saveFullName(context, consumer.getFullName());
        saveProfilePhotoUrl(context, consumer.getProfilePictureUrl());
        saveMobile(context, consumer.getMobileNumber());
        saveEmail(context, consumer.getEmail());
        saveAddress(context, consumer.getAddress());
    }

    /**
     * Get Consumer details from preferences
     *
     * @param context - Context
     * @return - Consumer object
     */
    public static Consumer getConsumer(Context context) {
        Consumer consumer = new Consumer();
        consumer.setFullName(getFullName(context));
        consumer.setMobileNumber(getMobile(context));
        consumer.setEmail(getEmail(context));
        consumer.setProfilePictureUrl(getProfilePhotoUrl(context));
        consumer.setAddress(getAddress(context));
        return consumer;
    }

    /**
     * Method to logout of app
     *
     * @param context - Context of activity to logout
     */
    public static void logout(Context context) {
        MCPreferences.get(context).clear();
        FirebaseAuth.getInstance().signOut();
        try {
            Intent intent = new Intent(context, LaunchControlActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            ((Activity) context).finishAffinity();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Method to check whether has permissions
     *
     * @param context     -  context of the activity
     * @param permissions - permissions array
     * @return - Has permissions or not
     */
    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null &&
                permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method to get formatted date for post
     *
     * @param milliSeconds - Time in millis
     * @return - String formatted date
     */
    public static String getFormattedDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    /**
     * Method to get formatted date for post
     *
     * @param milliSeconds - time to format
     * @return - Formatted date
     */
    public static String getDisplayDateTime(long milliSeconds) {
        return new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(new Date(milliSeconds));
    }


    /**
     * Method to get formatted date
     *
     * @param milliSeconds - time to format
     * @return - Formatted date
     */
    public static String getDisplayDate(long milliSeconds) {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date(milliSeconds));
    }

    /**
     * Method to get formatted time for event
     *
     * @param milliSeconds - time to format
     * @return - Formatted date
     */
    public static String getDisplayTime(long milliSeconds) {
        return new SimpleDateFormat("hh:mm a").format(new Date(milliSeconds));
    }

    public static String getStringFromList(ArrayList<String> list) {
        String finalString = "";
        for (String value : list) {
            int index = list.indexOf(value);
            if (index == list.size() - 1) {
                finalString = finalString.concat(value);
            } else {
                finalString = finalString.concat(value).concat(", ");
            }
        }

        return finalString;
    }

}
