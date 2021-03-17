package com.app.craniowake.view.activityHelper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Creates visual pop up calendar to select a date by year, month and day
 */
public class BirthdayPicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private final Context context;
    private final EditText editText;
    private int day;
    private int month;
    private int birthYear;

    public BirthdayPicker(Context context, int editTextViewID) {
        Activity act = (Activity) context;
        this.editText = act.findViewById(editTextViewID);
        this.editText.setOnClickListener(this);
        this.context = context;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        birthYear = year;
        month = monthOfYear;
        day = dayOfMonth;
        updateDisplay();
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
    private void updateDisplay() {
        editText.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(birthYear).append(" "));
    }
}