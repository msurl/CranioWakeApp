package com.app.craniowake.view.activityHelper;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import com.app.craniowake.generated.callback.OnClickListener;

import java.util.function.Function;

public class CraniowakeDialogBuilder {

    public static Dialog of(Context context, int layoutId, int buttonId) {
        Dialog dialog = new Dialog(context) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                this.requestWindowFeature(Window.FEATURE_NO_TITLE);
                this.setCancelable(false);
                this.setContentView(layoutId);

                Button closeButton = this.findViewById(buttonId);
                closeButton.setOnClickListener(v -> this.dismiss());
            }
        };
        return dialog;
    }

    public static Dialog withoutDismissOnButtonClick(Context context, int layoutId, int buttonId) {
        Dialog dialog = new Dialog(context) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                this.requestWindowFeature(Window.FEATURE_NO_TITLE);
                this.setCancelable(false);
                this.setContentView(layoutId);

                Button closeButton = this.findViewById(buttonId);
                closeButton.setOnClickListener(v -> this.dismiss());
            }
        };
        return dialog;
    }


//    public static Dialog of(Activity activity, int layoutId, int buttonId, Function<Void, Void> afterDismiss) {
//        Dialog dialog = new Dialog(activity) {
//            @Override
//            protected void onCreate(Bundle savedInstanceState) {
//                super.onCreate(savedInstanceState);
//                this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                this.setCancelable(false);
//                this.setContentView(layoutId);
//
//                Button closeButton = this.findViewById(buttonId);
//                closeButton.setOnClickListener(v -> {
//                    this.dismiss();
//                    afterDismiss.apply(null);
//                });
//            }
//        };
//        return dialog;
//    }
}
