package com.app.craniowake.view.activityHelper.customUtils;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.Button;

import com.app.craniowake.R;

public class DialogAddedCSV {

    /**
     * Method to call dialog
     *
     * @param activity shows dialog on this activity
     */
    public void showDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.event_note_csv);

        Button dialogButton = dialog.findViewById(R.id.end_note_csv);
        dialogButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();

    }

}
