package com.app.craniowake.view.stimulation;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.craniowake.R;
import com.app.craniowake.databinding.MotorFunctionFragmentBinding;
import com.app.craniowake.view.viewModel.ClinicalTestViewModel;
import com.app.craniowake.view.viewModel.MotorFunctionViewModel;

public class MotorFunctionFragment extends Fragment {

    private ClinicalTestViewModel viewModel;

    public static MotorFunctionFragment newInstance() {
        return new MotorFunctionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        MotorFunctionFragmentBinding binding = MotorFunctionFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ClinicalTestViewModel.class);
        binding.setViewmodel(viewModel);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}