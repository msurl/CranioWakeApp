package com.app.craniowake.view;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.app.craniowake.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class OperationActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(OperationActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.menu_operation)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfUIKomponentsDisplayed() {
        onView(withId(R.id.patient_id_operation_mode))
                .check(matches(isDisplayed()));
        onView(withId(R.id.current_operation_status))
                .check(matches(isDisplayed()));

        onView(withId(R.id.treshhold_test))
                .check(matches(isDisplayed()));
        onView(withId(R.id.verification_test))
                .check(matches(isDisplayed()));

        onView(withId(R.id.pptt_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.calc_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.stroop_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.read_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.picture_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.token_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.middle_of_line_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.digital_span_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.trailway_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.four_square_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.reaction_button))
                .check(matches(isDisplayed()));
    }

    @Test
    public void openPpttActivity() {
        onView(withId(R.id.pptt_button)).perform(click());
        onView(withId(R.id.ppt_act_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void openCalcActivity() {
        onView(withId(R.id.calc_button)).perform(click());
        onView(withId(R.id.calc_act_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void openStroopActivity() {
        onView(withId(R.id.stroop_button)).perform(click());
        onView(withId(R.id.stroop_act_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void openReadActivity() {
        onView(withId(R.id.read_button)).perform(click());
        onView(withId(R.id.read_act_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void openPictureActivity() {
        onView(withId(R.id.picture_button)).perform(click());
        onView(withId(R.id.picture_act_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void openTokenActivity() {
        onView(withId(R.id.token_button)).perform(click());
        onView(withId(R.id.token_act_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void openMoLActivity() {
        onView(withId(R.id.middle_of_line_button)).perform(click());
        onView(withId(R.id.mol_act_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void openDigitSpanActivity() {
        onView(withId(R.id.digital_span_button)).perform(click());
        onView(withId(R.id.dig_act_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void openTrailWayActivity() {
        onView(withId(R.id.trailway_button)).perform(click());
        onView(withId(R.id.tw_act_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void openFourSquareActivity() {
        onView(withId(R.id.four_square_button)).perform(click());
        onView(withId(R.id.fs_act_id))
                .check(matches(isDisplayed()));
    }

    @Test
    public void openReactionActivity() {
        onView(withId(R.id.reaction_button)).perform(click());
        onView(withId(R.id.react_act_id))
                .check(matches(isDisplayed()));
    }
}