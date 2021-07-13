package com.app.craniowake.view.stimulation;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.craniowake.databinding.SensoryFunctionParaesthesiaFragmentBinding;
import com.app.craniowake.view.viewModel.ClinicalTestViewModel;

public class SensoryFunctionParaesthesiaFragment extends Fragment {

    private ClinicalTestViewModel viewModel;

    public static SensoryFunctionParaesthesiaFragment newInstance() {
        return new SensoryFunctionParaesthesiaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SensoryFunctionParaesthesiaFragmentBinding binding = SensoryFunctionParaesthesiaFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ClinicalTestViewModel.class);
        binding.setViewmodel(viewModel);

        return binding.getRoot();
    }
}