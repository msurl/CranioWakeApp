package com.app.craniowake.view.stimulation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

import com.app.craniowake.R;
import com.app.craniowake.view.viewModel.ClinicalTestViewModel;

public class ClinicalTestActivity extends AppCompatActivity {

    private ClinicalTestViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_clinical_test);

        Button vigilance = findViewById(R.id.vigilance_global);
        vigilance.setOnClickListener(this::showVigilanceMenu);

        Button cranial = findViewById(R.id.cranial_nerves);
        cranial.setOnClickListener(this::showCranialNervesMenu);

        MotorFunctionFragment motorFunctionFragment = new MotorFunctionFragment();
        SensoryFunctionFragment sensoryFunctionFragment = new SensoryFunctionFragment();

        Button motorFunction = findViewById(R.id.motor_function_button);
        motorFunction.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, motorFunctionFragment).commit();
        });

        Button sensoryFunction = findViewById(R.id.sensory_function);
        sensoryFunction.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, sensoryFunctionFragment).commit();
        });

        viewModel = new ViewModelProvider(this).get(ClinicalTestViewModel.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void showVigilanceMenu(View view) {
        PopupMenu popup = initPopupMenu(view, R.menu.menu_vigilance);
        popup.show();
    }

    private void showCranialNervesMenu(View view) {
        PopupMenu popup = initPopupMenu(view, R.menu.menu_cranial);
        popup.show();
    }

    private PopupMenu initPopupMenu(View view, int resId) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(resId, popup.getMenu());
        return popup;
    }

    public void saveTestAndFinishActivity() {
        viewModel.saveCurrentTest();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.clinical_test_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save_clinical_test_button)
            saveTestAndFinishActivity();
        return super.onOptionsItemSelected(item);
    }
}