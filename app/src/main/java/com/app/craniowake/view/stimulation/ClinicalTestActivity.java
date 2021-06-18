package com.app.craniowake.view.stimulation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.app.craniowake.R;

public class ClinicalTestActivity extends AppCompatActivity {

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
}