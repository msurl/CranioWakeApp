package com.app.craniowake.view.stimulation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.craniowake.R;

/**
 * THIS CLASS IS NOT PART OF THE FINISHED APP. CLASS IS FOR UPDATE TO CRANIOWAKE 2.0. PLEASE IGNORE
 */
public abstract class StimulationActivity extends AppCompatActivity //implements AdapterView.OnItemSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public final void closeActivity(View view) {
        saveTest();
        finish();
    }

    abstract void saveTest();

}
