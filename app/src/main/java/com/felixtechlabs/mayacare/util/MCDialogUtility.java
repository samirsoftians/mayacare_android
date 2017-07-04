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

    public static MaterialDialog getResetPasswordDialog(Context context, MaterialDialog.InputCallback inputCallback) {
        return new MaterialDialog.Builder(context)
                .title(R.string.dialog_title_forgot)
                .content(R.string.dialog_content_forgot)
                .inputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                .positiveText(R.string.dialog_action_send)
                .input(R.string.dialog_hint_forgot, 0, inputCallback).show();

    }
}
