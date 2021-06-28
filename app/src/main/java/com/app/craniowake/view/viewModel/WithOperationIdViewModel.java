package com.app.craniowake.view.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import lombok.Setter;

public class WithOperationIdViewModel extends AndroidViewModel {
    @Setter
    protected Long operationId;

    public WithOperationIdViewModel(@NonNull Application application) {
        super(application);
    }
}
