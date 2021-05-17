package com.app.craniowake.view.stimulation;

import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.databinding.EventCriticalVerificationTestStimulationBinding;
import com.app.craniowake.view.viewModel.VerificationTestViewModel;

/**
 * THIS CLASS IS NOT PART OF THE FINISHED APP. CLASS IS FOR UPDATE TO CRANIOWAKE 2.0. PLEASE IGNORE
 */
public class VerificationTestActivity extends StimulationActivity {

    private VerificationTestViewModel verificationTestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventCriticalVerificationTestStimulationBinding binding = DataBindingUtil.setContentView(this, R.layout.event_critical_verification_test_stimulation);
        verificationTestViewModel = new ViewModelProvider(this).get(VerificationTestViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setViewmodel(verificationTestViewModel);
    }

    @Override
    public void saveTest() {
        verificationTestViewModel.insert();
    }


}
