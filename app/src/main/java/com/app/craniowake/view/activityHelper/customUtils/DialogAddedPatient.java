package com.app.craniowake.view.activityHelper.customUtils;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.Button;

import com.app.craniowake.R;

/**
 * custom designed dialog Pop-Up for basic information like added patient or successful db export.
 */
public class DialogAddedPatient {

    /**
     * Method to call dialog
     *
     * @param activity shows dialog on this activity
     */
    public void showDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.event_note);

        Button dialogButton = dialog.findViewById(R.id.end_note_add_patient);
        dialogButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();

    }
}
