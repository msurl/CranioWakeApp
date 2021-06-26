package com.app.craniowake.view.patient;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.state.State;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.craniowake.R;
import com.app.craniowake.data.model.Patient;
import com.app.craniowake.view.MainActivity;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.activityHelper.IntentHolder;
import com.app.craniowake.view.activityHelper.PatientListAdapter;
import com.app.craniowake.view.viewModel.PatientViewModel;

/**
 * Activity to display all existing patients in database
 */
public class PatientListActivity extends PatientActivity {

//    private PatientViewModel patientViewModel;

    /**
     * generates and displays the recyclerview which was implemented in the PatientListAdapter.java class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_patient_overview);
        initializeNawigationDrawer();

        RecyclerView recyclerView = findViewById(R.id.patient_items_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        final PatientListAdapter listAdapter = new PatientListAdapter();
        recyclerView.setAdapter(listAdapter);

        PatientViewModel patientViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(PatientViewModel.class);
        patientViewModel.getAllPatients().observe(this, listAdapter::setPatients);

        /**
         * generates the swipe animation to select a Patient.
         * The name and Id are send to an Intent before leaving the activity.
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Patient patient = listAdapter.getPatientByPosition(viewHolder.getAdapterPosition());

                if(direction == ItemTouchHelper.RIGHT) {
                    Intent intent = new Intent(PatientListActivity.this, MainActivity.class);
                    intent.putExtra(IntentHolder.PATIENT_ID, patient.getPatientId());
                    intent.putExtra(IntentHolder.PATIENT_NAME, patient.getLastname() + " " + patient.getFirstname());
                    finish();
                    startActivity(intent);
                }
                else if(direction == ItemTouchHelper.LEFT) {
                    patientViewModel.deletePatient(patient);
                }
            }
        }).attachToRecyclerView(recyclerView);
    }
}
