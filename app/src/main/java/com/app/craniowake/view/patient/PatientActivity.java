package com.app.craniowake.view.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.app.craniowake.R;
import com.app.craniowake.view.MainActivity;

import static com.app.craniowake.view.activityHelper.IntentHolder.PATIENT_ID;
/**
 * Baseclass for UserManagement
 */
public class PatientActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        initializeNawigationDrawer();
    }

    /**
     * saves id of patient to intent extra
     */
    void addIntent(int patientId) {
        Intent data = new Intent();
        data.putExtra(PATIENT_ID, patientId);
        setResult(RESULT_OK, data);
    }

    /**
     * allows navigating in navigationBar for both subclasses
     * if clicked, current activity is destroyed
     * @param item menuItem clicked in navigationDrawer
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        finish();
        super.onNavigationItemSelected(item);
        return true;
    }
}
