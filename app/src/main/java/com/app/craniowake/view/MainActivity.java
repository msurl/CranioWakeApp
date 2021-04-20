package com.app.craniowake.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.db.CraniowakeDatabase;
import com.app.craniowake.data.model.Operation;
import com.app.craniowake.databaseExport.SqliteExporter;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.activityHelper.customUtils.DialogAddedCSV;
import com.app.craniowake.view.activityHelper.customUtils.DialogAddedPatient;
import com.app.craniowake.view.patient.AddPatientActivity;
import com.app.craniowake.view.patient.PatientListActivity;
import com.app.craniowake.view.viewModel.OperationViewModel;
import com.app.craniowake.view.viewModel.PatientViewModel;
import com.facebook.stetho.Stetho;
import com.google.android.material.navigation.NavigationView;

import lombok.SneakyThrows;

/**
 * MainActivity and entry point of the App. It starts a new Operation (OperationActivity)
 * From here the navigationDrawer is operated to open PatientListActivity, AddPatientActivity and initiate a databaseExport
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, NavigationView.OnNavigationItemSelectedListener {

    PatientViewModel patientViewModel;
    OperationViewModel operationViewModel;
    String selectedGameMode;
    String selectedBrainArea;

    /**
     * creates MainActivity
     */
    @SneakyThrows
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.menu_start_screen);
        generateUIElements();
        displayNameOfCurrentPatient();
    }

    /**
     * if patient has been selected the name will be displayed
     * otherwise it will inform the user that no patient has been selected yet
     */
    private void displayNameOfCurrentPatient() {
        final TextView currentPatientTextView = findViewById(R.id.current_patient_id);
        if (getPatientName() != null) {
            currentPatientTextView.setText(getPatientName());
        } else {
            currentPatientTextView.setText(R.string.no_patient_selected);
        }
    }

    /**
     * get selected patient  Id
     *
     * @return id of patient
     */
    private int getById() {
        Intent intent = getIntent();
        return intent.getIntExtra(IntentHolder.PATIENT_ID, 0);
    }

    /**
     * get selected patient name
     *
     * @return name of patient
     */
    private String getPatientName() {
        Intent intent = getIntent();
        return intent.getStringExtra(IntentHolder.PATIENT_NAME);
    }

    /**
     * navigate from Menu to a new Activity (atientListActivity, AddPatientActivity) or start a databaseExport
     *
     * @param item selected choice how to proceed further
     */
    @SneakyThrows
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.item_newUserId:
                intent = new Intent(this, AddPatientActivity.class);
                break;
            case R.id.item_userListId:
                intent = new Intent(this, PatientListActivity.class);
                break;
            case R.id.item_exportId:
                CraniowakeDatabase craniowakeDatabase = CraniowakeDatabase.getInstance(this);
                if (!SqliteExporter.DB_GENERATING_CSV) {
                    SqliteExporter.export(craniowakeDatabase.getOpenHelper().getReadableDatabase(), this);
                    displayNoteOfDbExport();
                }
                item.setEnabled(false);
                return true;
            default:
                System.out.println("no item found");
                break;
        }
        startActivity(intent);
        return true;
    }


    public void startOperation(View v) {
        saveOperation(selectedGameMode, selectedBrainArea);
    }

    /**
     * creates object of Operation and saves the new Operation, connected to the Patient to the database. Object is processed by the OperationViewModel.
     * following the OperationActivity starts
     * Intents to be send to OperationActivity
     *
     * @param selectedBrainArea area were tumor most likely is located
     * @param selectedGameMode  moment of testing(prä, intra, post, followup)
     */
    private void saveOperation(String selectedGameMode, String selectedBrainArea) {
        final Intent intent = new Intent(this, OperationActivity.class);

        operationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(OperationViewModel.class);
        patientViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PatientViewModel.class);
        patientViewModel.getPatientById(getById()).observe(this, patient -> {
            try {
                Operation newOperation = new Operation(selectedBrainArea, selectedGameMode, patient.getPatientId());
                operationViewModel.addOperation(newOperation);

                intent.putExtra(IntentHolder.OPERATION_DATE, newOperation.getDateTime());
                intent.putExtra(IntentHolder.PATIENT_ID, getById());
                intent.putExtra(IntentHolder.PATIENT_NAME, patient.getFirstname() + patient.getLastname());
                intent.putExtra(IntentHolder.OPERATION_MODE, selectedGameMode);

                setResult(RESULT_OK, intent);
                startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Please select a patient", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * initialize input for dropdown spinner to select brainarea and operationMode
     */
    private void initializeSpinners() {
        //spinner1
        Spinner spinnerBrainArea = findViewById(R.id.spinnerBrainArea);
        createDropdownSpinner(spinnerBrainArea, R.array.brainAreas);

        //spinner2
        Spinner spinnerGameMode = findViewById(R.id.spinnerGameMode);
        createDropdownSpinner(spinnerGameMode, R.array.game_modes);
    }

    private void createDropdownSpinner(Spinner spinner, int stringResourceId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, stringResourceId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    protected void initializeToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    protected void initializeNawigationDrawer() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.menu_operation);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.todo, R.string.todo);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
    }

    private void generateUIElements() {
        initializeSpinners();
        initializeToolbar();
        initializeNawigationDrawer();
    }

    /**
     * informs user of successful databaseExport
     */
    public void displayNoteOfDbExport() {
        DialogAddedCSV alert = new DialogAddedCSV();
        alert.showDialog(this);
    }

    /**
     * closes Activity with current Patient
     */
    public void closeActivity(View view) {
        finish();
    }

    /**
     * get correct item from dropdown spinners
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerBrainArea:
                selectedBrainArea = parent.getItemAtPosition(position).toString();
                break;
            case R.id.spinnerGameMode:
                selectedGameMode = parent.getItemAtPosition(position).toString();
                break;
            default:
                //
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}

