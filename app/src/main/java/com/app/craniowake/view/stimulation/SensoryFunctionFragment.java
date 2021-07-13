package com.app.craniowake.view.stimulation;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.craniowake.R;
import com.app.craniowake.databinding.MotorFunctionFragmentBinding;
import com.app.craniowake.databinding.SensoryFunctionFragmentBinding;
import com.app.craniowake.view.viewModel.ClinicalTestViewModel;
import com.app.craniowake.view.viewModel.SensoryFunctionViewModel;

import org.jetbrains.annotations.NotNull;

public class SensoryFunctionFragment extends Fragment {

    private SensoryFunctionPainFragment sensoryFunctionPainFragment;
    private SensoryFunctionParaesthesiaFragment sensoryFunctionParaesthesiaFragment;
    private SensoryFunctionAnaesthesiaFragment sensoryFunctionAnaesthesiaFragment;

    public static SensoryFunctionFragment newInstance() {
        return new SensoryFunctionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sensory_function_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        this.sensoryFunctionPainFragment = SensoryFunctionPainFragment.newInstance();
        this.sensoryFunctionAnaesthesiaFragment = SensoryFunctionAnaesthesiaFragment.newInstance();
        this.sensoryFunctionParaesthesiaFragment = SensoryFunctionParaesthesiaFragment.newInstance();

        RadioGroup sensoryFunctionRadioGroup = view.findViewById(R.id.sensory_function_radio_group);
        sensoryFunctionRadioGroup.setOnCheckedChangeListener((radioGroup, checkedId) ->
                SensoryFunctionFragment.this.changeInnerFragment(checkedId));
    }

    private void changeInnerFragment(int id) {
        if(id == R.id.pain)
            changeInnerFragment(sensoryFunctionPainFragment);
        if(id == R.id.anaesthesia)
            changeInnerFragment(sensoryFunctionAnaesthesiaFragment);
        if(id == R.id.paraesthesia)
            changeInnerFragment(sensoryFunctionParaesthesiaFragment);
    }

    private void changeInnerFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(R.id.sensory_function_inner_fragment, fragment).commit();
    }
}