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
import com.app.craniowake.view.viewModel.MotorFunctionViewModel;

public class MotorFunctionFragment extends Fragment {

    private MotorFunctionViewModel mViewModel;

    public static MotorFunctionFragment newInstance() {
        return new MotorFunctionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.motor_function_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MotorFunctionViewModel.class);
        // TODO: Use the ViewModel
    }

}