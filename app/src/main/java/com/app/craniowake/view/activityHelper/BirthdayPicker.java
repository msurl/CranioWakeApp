package com.app.craniowake.view.activityHelper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.craniowake.data.model.Patient;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Creates visual pop up calendar to select a date by year, month and day
 */
public class BirthdayPicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private final Context context;
    MutableLiveData<Patient> patient;

    public BirthdayPicker(Context context, int editTextViewID, MutableLiveData<Patient> patient) {
        this.context = context;
        this.patient = patient;
        Activity act = (Activity) context;
        View editText = act.findViewById(editTextViewID);
        editText.setOnClickListener(this);
    }

    @Override
    public final void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//        Vielleicht von setValue() auf postValue() ändern
        Patient p = patient.getValue();

        p.setBirthDate(formatDate(year, monthOfYear, dayOfMonth));
        patient.setValue(p);
    }

    /**
     * calendar is set to default Timezone and starts 50 years in the past.
     *
     * @param v when clicked the calendar opens
     */
    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        DatePickerDialog dialog = new DatePickerDialog(context, this, calendar.get(Calendar.YEAR) - 50, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }

    /**
     * formats selected date and sets editText
     */
    private String formatDate(int year, int month, int day) {
        return day + "/"+ (month + 1) + "/" + year+ " ";
    }
}