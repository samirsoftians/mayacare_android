package com.felixtechlabs.mayacare.util;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
}
