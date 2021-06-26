package com.app.craniowake.view.activityHelper;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.app.craniowake.R;
import com.app.craniowake.data.model.Complication;
import com.app.craniowake.data.model.Patient;
import com.app.craniowake.databinding.DialogComplicationBinding;
import com.app.craniowake.functional.VoidFunction;
import com.app.craniowake.generated.callback.OnClickListener;
import com.app.craniowake.view.OperationActivity;
import com.app.craniowake.view.viewModel.ComplicationViewModel;
import com.app.craniowake.view.viewModel.PatientViewModel;

import java.util.function.Function;

// TODO: Vielleicht lieber DialogFragment nutzen statt dieser Variante
public class CraniowakeDialogBuilder {

    public static Dialog of(Context context, int layoutId, int buttonId) {
        Dialog dialog = new Dialog(context) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                this.requestWindowFeature(Window.FEATURE_NO_TITLE);
                this.setCancelable(false);
                this.setContentView(layoutId);

                Button closeButton = this.findViewById(buttonId);
                closeButton.setOnClickListener(v -> this.dismiss());
            }
        };
        return dialog;
    }

//    public static Dialog withOnClickListener(Context context, int layoutId, int buttonId, OnClickListener onClickListener) {
//        Dialog dialog = new Dialog(context) {
//            @Override
//            protected void onCreate(Bundle savedInstanceState) {
//                super.onCreate(savedInstanceState);
//                this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                this.setCancelable(false);
//                this.setContentView(layoutId);
//
//                Button closeButton = this.findViewById(buttonId);
//                closeButton.setOnClickListener(onClickListener);
//            }
//        };
//        return dialog;
//    }

    public static Dialog complicationDialogWithDataBinding(AppCompatActivity activity, Long operationId) {
        final ComplicationViewModel viewModel = new ViewModelProvider(activity).get(ComplicationViewModel.class);
        viewModel.setOperationId(operationId);
        DialogComplicationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.dialog_complication, null, false);
        binding.setViewmodel(viewModel);

        ComplicationDialog dialog = new ComplicationDialog(activity, viewModel::addComplication);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(binding.getRoot());

        return dialog;
    }

//    private static class ComplicationDialog extends Dialog{
//
//        VoidFunction executeOnCloseButtonClick;
//
//        public ComplicationDialog(@NonNull Context context, VoidFunction function) {
//            super(context);
//
//            executeOnCloseButtonClick = function;
//        }
//
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            Button closeButton = this.findViewById(R.id.btn_dialog_complication);
//            closeButton.setOnClickListener(v -> {
//                executeOnCloseButtonClick.execute();
//                super.dismiss();
//            });
//        }
//    }

    private static class DialogWithVoidFunction extends Dialog {

        final VoidFunction executeOnCloseButtonClick;
        final int closeButtonId;

        public DialogWithVoidFunction(@NonNull Context context, int closeButtonId, VoidFunction function) {
            super(context);

            this.closeButtonId = closeButtonId;
            this.executeOnCloseButtonClick = function;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Button closeButton = this.findViewById(closeButtonId);
            closeButton.setOnClickListener(v -> {
                executeOnCloseButtonClick.execute();
                super.dismiss();
            });
        }
    }

    private static class ComplicationDialog extends DialogWithVoidFunction{

        VoidFunction executeOnCloseButtonClick;

        public ComplicationDialog(@NonNull Context context, VoidFunction function) {
            super(context, R.id.btn_dialog_complication, function);

            executeOnCloseButtonClick = function;
        }
    }

    // TODO: Nachdem ein Patient zum löschen ausgewählt wurde, dann aber auf "Nein" geklickt wurde, muss der ListViewAdapter aktualisiert werden.
    public static Dialog deletePatientDialog(Context context, PatientViewModel viewModel, Patient patient) {
        Dialog dialog = new AlertDialog.Builder(context).setCancelable(false).
                setIcon(android.R.drawable.ic_menu_delete).setMessage(context.getString(R.string.delete_patient) + " " + patient.getName()).
                setPositiveButton(R.string.yes, (dialog1, which) -> {
                    viewModel.deletePatient(patient);
                }).setNegativeButton(R.string.no, (dialog1, which) -> {}).create();

        return dialog;
    }
}
