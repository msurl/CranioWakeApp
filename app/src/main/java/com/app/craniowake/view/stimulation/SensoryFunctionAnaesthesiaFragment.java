package com.app.craniowake.view.stimulation;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.app.craniowake.R;
import com.app.craniowake.databinding.SensoryFunctionAnaesthesiaFragmentBinding;
import com.app.craniowake.databinding.SensoryFunctionPainFragmentBinding;
import com.app.craniowake.view.viewModel.ClinicalTestViewModel;

public class SensoryFunctionAnaesthesiaFragment extends Fragment {

    private ClinicalTestViewModel viewModel;

    public static SensoryFunctionAnaesthesiaFragment newInstance() {
        return new SensoryFunctionAnaesthesiaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SensoryFunctionAnaesthesiaFragmentBinding binding = SensoryFunctionAnaesthesiaFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ClinicalTestViewModel.class);
        binding.setViewmodel(viewModel);

        return binding.getRoot();
    }
}