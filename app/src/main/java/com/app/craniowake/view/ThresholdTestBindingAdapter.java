package com.app.craniowake.view;

import android.widget.RadioGroup;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

import com.app.craniowake.R;

public class ThresholdTestBindingAdapter {

//    @BindingAdapter("threshold:stimulationArea")
//    public static void setStimulationAreaRadioButtonId(RadioGroup radioGroup, String area) {
//        int id = -1;
//
//        if(area.equals("cortical"))
//            id = R.id.cortical_threshold;
//        else if(area.equals("subcortical"))
//            id = R.id.subcortical_threshold;
//        else if(area.equals("epidural"))
//            id = R.id.epidural_threshold;
//
//        if(radioGroup.getCheckedRadioButtonId() != id) {
//            radioGroup.check(id);
//        }
//    }
//
//    @InverseBindingAdapter(attribute = "threshold:stimulationArea", event = "android:checkedButtonAttrChanged")
//    public static String getStimulationAreaFromRadioButtonId(RadioGroup radioGroup) {
//        if (radioGroup.getCheckedRadioButtonId() == R.id.cortical_threshold)
//            return "cortical";
//        if(radioGroup.getCheckedRadioButtonId() == R.id.subcortical_threshold)
//            return "subcortical";
//        if(radioGroup.getCheckedRadioButtonId() == R.id.epidural_threshold)
//            return "epidural";
//
//        return "";
//    }
//
//    @BindingAdapter("threshold:responseType")
//    public static void setResponseRadioButtonId(RadioGroup radioGroup, String area) {
//        int id = -1;
//
//        if(area.equals("clinical"))
//            id = R.id.clinical_treshhold;
//        else if(area.equals("EMG"))
//            id = R.id.emg_treshhold;
//        else if(area.equals("both"))
//            id = R.id.both_treshhold;
//
//        if(radioGroup.getCheckedRadioButtonId() != id) {
//            radioGroup.check(id);
//        }
//    }
//
//    @InverseBindingAdapter(attribute = "threshold:responseType", event = "android:checkedButtonAttrChanged")
//    public static String getResponseFromRadioButtonId(RadioGroup radioGroup) {
//        if (radioGroup.getCheckedRadioButtonId() == R.id.clinical_treshhold)
//            return "clinical";
//        if(radioGroup.getCheckedRadioButtonId() == R.id.emg_treshhold)
//            return "EMG";
//        if(radioGroup.getCheckedRadioButtonId() == R.id.both_treshhold)
//            return "both";
//
//        return "";
//    }
}
