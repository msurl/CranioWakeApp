package com.app.craniowake.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.app.craniowake.R;
import com.app.craniowake.view.activityHelper.DialogBuilder;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash_screen);
        showDisclaimer();
    }

    private void showDisclaimer() {
        Dialog dialog = DialogBuilder.of(this, R.layout.disclaimer_dialog, R.id.btn_dialog_disclaimer);
        dialog.setOnDismissListener(d -> {
            this.startActivity(new Intent(this, MainActivity.class));
        });
        dialog.show();
    }

}