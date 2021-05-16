package com.app.craniowake.view;

import android.widget.AdapterView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

import com.app.craniowake.R;

import java.util.Arrays;

// TODO: Views, die in beiden Tests vorkommen, zu jeweils einem Element zusammenfassen
public class VerificationTestBindingAdapter {

    @BindingAdapter("verification:motorFunctionStimulationArea")
    public static void setMotorFunctionStimulationAreaRadioButtonId(RadioGroup radioGroup, String area) {
        int id = -1;

        if(area.equals("cortical"))
            id = R.id.cortical_verification;
        else if(area.equals("subcortical"))
            id = R.id.subcortical_verification;

        if(radioGroup.getCheckedRadioButtonId() != id) {
            radioGroup.check(id);
        }
    }

    @InverseBindingAdapter(attribute = "verification:motorFunctionStimulationArea", event = "android:checkedButtonAttrChanged")
    public static String getMotorFunctionStimulationAreaFromRadioButtonId(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.cortical_verification)
            return "cortical";
        if(radioGroup.getCheckedRadioButtonId() == R.id.subcortical_verification)
            return "subcortical";

        return "";
    }

    @BindingAdapter("verification:speechStimulationArea")
    public static void setSpeechStimulationAreaRadioButtonId(RadioGroup radioGroup, String area) {
        int id = -1;

        if(area.equals("cortical"))
            id = R.id.cortical_speech;
        else if(area.equals("subcortical"))
            id = R.id.subcortical_speech;

        if(radioGroup.getCheckedRadioButtonId() != id) {
            radioGroup.check(id);
        }
    }

    @InverseBindingAdapter(attribute = "verification:speechStimulationArea", event = "android:checkedButtonAttrChanged")
    public static String getSpeechStimulationAreaFromRadioButtonId(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.cortical_speech)
            return "cortical";
        if(radioGroup.getCheckedRadioButtonId() == R.id.subcortical_speech)
            return "subcortical";

        return "";
    }


    @BindingAdapter("verification:motorFunctionStimulationType")
    public static void setStimulationTypeRadioButtonId(RadioGroup radioGroup, String area) {
        int id = -1;

        if(area.equals("monopolar"))
            id = R.id.monopolar;
        else if(area.equals("bipolar"))
            id = R.id.bipolar;

        if(radioGroup.getCheckedRadioButtonId() != id) {
            radioGroup.check(id);
        }
    }

    @InverseBindingAdapter(attribute = "verification:motorFunctionStimulationType", event = "android:checkedButtonAttrChanged")
    public static String getStimulationTypeFromRadioButtonId(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.monopolar)
            return "monopolar";
        if(radioGroup.getCheckedRadioButtonId() == R.id.bipolar)
            return "bipolar";

        return "";
    }

    @BindingAdapter("verification:verResponseType")
    public static void setVerificationResponseRadioButtonId(RadioGroup radioGroup, String area) {
        int id = -1;

        if(area.equals("clinical"))
            id = R.id.clinical;
        else if(area.equals("EMG"))
            id = R.id.emg;
        else if(area.equals("both"))
            id = R.id.both;

        if(radioGroup.getCheckedRadioButtonId() != id) {
            radioGroup.check(id);
        }
    }

    @InverseBindingAdapter(attribute = "verification:verResponseType", event = "android:checkedButtonAttrChanged")
    public static String getVerificationResponseFromRadioButtonId(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.clinical)
            return "clinical";
        if(radioGroup.getCheckedRadioButtonId() == R.id.emg)
            return "EMG";
        if(radioGroup.getCheckedRadioButtonId() == R.id.both)
            return "both";

        return "";
    }


    @BindingAdapter({"stimulation:responseInMuscle", "stimulation:muscles"})
    public static void setResponseInMuscle(Spinner spinner, String muscle, String[] muscles) {
        int position = Arrays.stream(muscles).filter(muscle::equals).findFirst().
                map(m -> Arrays.binarySearch(muscles, m, String::compareTo)).orElse(AdapterView.INVALID_POSITION);

        if (spinner.getSelectedItemPosition() != position) {
            spinner.setSelection(position);
        }
    }

    @InverseBindingAdapter(attribute = "stimulation:responseInMuscle", event = "android:selectedItemPositionAttrChanged")
    public static String getResponseInMuscle(Spinner spinner) {
        int position = spinner.getSelectedItemPosition();

        String muscle = (position != AdapterView.INVALID_POSITION) ?  spinner.getSelectedItem().toString() : "";
        return muscle;
    }

    @BindingAdapter("threshold:stimulationArea")
    public static void setThresholdStimulationAreaRadioButtonId(RadioGroup radioGroup, String area) {
        int id = -1;

        if(area.equals("cortical"))
            id = R.id.cortical_threshold;
        else if(area.equals("subcortical"))
            id = R.id.subcortical_threshold;
        else if(area.equals("epidural"))
            id = R.id.epidural_threshold;

        if(radioGroup.getCheckedRadioButtonId() != id) {
            radioGroup.check(id);
        }
    }

    @InverseBindingAdapter(attribute = "threshold:stimulationArea", event = "android:checkedButtonAttrChanged")
    public static String getThresholdStimulationAreaFromRadioButtonId(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.cortical_threshold)
            return "cortical";
        if(radioGroup.getCheckedRadioButtonId() == R.id.subcortical_threshold)
            return "subcortical";
        if(radioGroup.getCheckedRadioButtonId() == R.id.epidural_threshold)
            return "epidural";

        return "";
    }

    @BindingAdapter("threshold:responseType")
    public static void setResponseRadioButtonId(RadioGroup radioGroup, String area) {
        int id = -1;

        if(area.equals("clinical"))
            id = R.id.clinical_treshhold;
        else if(area.equals("EMG"))
            id = R.id.emg_treshhold;
        else if(area.equals("both"))
            id = R.id.both_treshhold;

        if(radioGroup.getCheckedRadioButtonId() != id) {
            radioGroup.check(id);
        }
    }

    @InverseBindingAdapter(attribute = "threshold:responseType", event = "android:checkedButtonAttrChanged")
    public static String getResponseFromRadioButtonId(RadioGroup radioGroup) {
        if (radioGroup.getCheckedRadioButtonId() == R.id.clinical_treshhold)
            return "clinical";
        if(radioGroup.getCheckedRadioButtonId() == R.id.emg_treshhold)
            return "EMG";
        if(radioGroup.getCheckedRadioButtonId() == R.id.both_treshhold)
            return "both";

        return "";
    }
}
