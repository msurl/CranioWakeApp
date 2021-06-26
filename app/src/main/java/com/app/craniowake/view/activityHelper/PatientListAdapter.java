package com.app.craniowake.view.activityHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.craniowake.R;
import com.app.craniowake.data.model.Patient;
import com.app.craniowake.data.model.University;

import java.util.ArrayList;
import java.util.List;

/**
 * class to display a list all existing patients at the specified position in a recyclerview
 */
public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientListHolder> {
    private ArrayList<Patient> patients = new ArrayList<>();

    /**
     * inflates created RecyclerView from layout Folder
     */
    @NonNull
    @Override
    public PatientListAdapter.PatientListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_patient_items, parent, false);
        return new PatientListHolder(itemView);
    }

    /**
     * new view for each new row, an old view is recycled and reused by binding new data to it.
     *
     * @param position the position of the current displayed "container" in which a patient will be displayed
     * @param holder   recycled container to display data
     */
    @Override
    public void onBindViewHolder(@NonNull PatientListAdapter.PatientListHolder holder, int position) {
        Patient currentPatient = patients.get(position);
        try {
            holder.textViewPatientName.setText(concatenatePatientName(currentPatient));
            holder.textViewCaseNumber.setText(String.valueOf(currentPatient.getCaseNumber()));
            holder.textViewPatientBirthDate.setText(currentPatient.getBirthDate());
            holder.textViewPatientUniversity.setText(currentPatient.getUniversity().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String concatenatePatientName(Patient currentPatient) {
        return currentPatient.getLastname() + " " + currentPatient.getFirstname();
    }

    public Patient getPatientByPosition(int position) {
        return patients.get(position);
    }

    /**
     * returns amount of patient objects in List
     */
    @Override
    public int getItemCount() {
        return patients.size();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = (ArrayList<Patient>) patients;
        notifyDataSetChanged();
    }

    /**
     * displays Information(name, caseNumber, creationDate) about a Patient
     */
    static class PatientListHolder extends RecyclerView.ViewHolder {
        private final TextView textViewPatientName;
        private final TextView textViewCaseNumber;
        private final TextView textViewPatientBirthDate;
        private final TextView textViewPatientUniversity;

        public PatientListHolder(View itemView) {
            super(itemView);
            textViewPatientName = itemView.findViewById(R.id.patient_name_list);
            textViewCaseNumber = itemView.findViewById(R.id.patient_casenumber_list);
            textViewPatientBirthDate = itemView.findViewById(R.id.patient_birthdate_list);
            textViewPatientUniversity = itemView.findViewById(R.id.patient_university_list);
        }
    }
}
