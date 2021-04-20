package com.app.craniowake.view.patient;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.app.craniowake.R;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.databaseExport.SqliteExporter;
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
     *
     * @param item menuItem clicked in navigationDrawer
     */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        finish();
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.item_newUserId:
                intent = new Intent(this, AddPatientActivity.class);
                break;
            case R.id.item_userListId:
                intent = new Intent(this, PatientListActivity.class);
                break;
            case R.id.item_exportId:
                item.setEnabled(false);
                return true;
            default:
                System.out.println("no item found");
                break;
        }
        startActivity(intent);
        return true;
    }
}
