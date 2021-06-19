package com.app.craniowake.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.app.craniowake.R;
import com.app.craniowake.view.activityHelper.CraniowakeDialogBuilder;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash_screen);
        showDisclaimer();
    }

    private void showDisclaimer() {
        Dialog dialog = CraniowakeDialogBuilder.of(this, R.layout.dialog_disclaimer, R.id.btn_dialog_disclaimer);
        dialog.setOnDismissListener(d -> {
            this.startActivity(new Intent(this, MainActivity.class));
        });
        dialog.show();
    }

}