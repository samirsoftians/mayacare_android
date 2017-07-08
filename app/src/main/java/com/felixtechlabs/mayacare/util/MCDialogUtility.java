package com.felixtechlabs.mayacare.util;

import android.content.Context;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;
import com.felixtechlabs.mayacare.R;

/**
 * Created by rohan on 4/7/17.
 */

public class MCDialogUtility {

    /**
     * Method to get Logout dialog
     *
     * @param context - Context where logout dialog is required
     * @return - Material Dialog object
     */
    public static MaterialDialog getLogoutDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .title(context.getString(R.string.dialog_title_information))
                .content(context.getString(R.string.dialog_message_logout))
                .positiveText(context.getString(R.string.dialog_action_yes))
                .negativeText(context.getString(R.string.dialog_action_no))
                .onPositive((dialog, which) -> MCUtility.logout(context))
                .onNegative((dialog, which) -> dialog.dismiss()).build();
    }

    /**
     * Method to get reset password dialog
     *
     * @param context       - Context of login activity
     * @param inputCallback - callback
     * @return - Material Dialog object
     */
    public static MaterialDialog getResetPasswordDialog(Context context, MaterialDialog.InputCallback inputCallback) {
        return new MaterialDialog.Builder(context)
                .title(R.string.dialog_title_forgot)
                .content(R.string.dialog_content_forgot)
                .inputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                .positiveText(R.string.dialog_action_send)
                .input(R.string.dialog_hint_forgot, 0, inputCallback).show();

    }

    /**
     * Get services selector dialog
     *
     * @param context                 - Context of calling activity
     * @param selectedIndices         - already selected options
     * @param listCallbackMultiChoice - callback for action
     * @return - Material Dialog object
     */
    public static MaterialDialog getServicesDialog(Context context,
                                                   Integer[] selectedIndices,
                                                   MaterialDialog.ListCallbackMultiChoice
                                                           listCallbackMultiChoice) {
        return new MaterialDialog.Builder(context)
                .title(R.string.dialog_title_services)
                .items(R.array.array_services)
                .itemsCallbackMultiChoice(selectedIndices, listCallbackMultiChoice)
                .positiveText(R.string.dialog_action_select).build();
    }

    public static MaterialDialog getSingleButtonInfoDialog(Context context, String message,
                                                           MaterialDialog.SingleButtonCallback
                                                                   singleButtonCallback) {
        return new MaterialDialog.Builder(context)
                .title(R.string.dialog_title_information)
                .content(message)
                .positiveText("Okay")
                .onPositive(singleButtonCallback)
                .build();
    }
}
