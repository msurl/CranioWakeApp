package com.app.craniowake.view.patient;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.app.craniowake.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Testing display and elements of given Activity.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class AddPatientActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(AddPatientActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.user_add_patient_id)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfUIKomponentsDisplayed() {
        onView(withId(R.id.user_input_caseNumber_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.user_input_firstname_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.user_input_lastname_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.user_birthday_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.RGroup))
                .check(matches(isDisplayed()));
        onView(withId(R.id.input_user_male))
                .check(matches(isDisplayed()));
        onView(withId(R.id.input_user_female))
                .check(matches(isDisplayed()));
        onView(withId(R.id.add_user_button))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkInput() {
        onView(withId(R.id.user_input_caseNumber_id)).perform(typeText("123634"), closeSoftKeyboard());
        onView(withId(R.id.add_user_button)).perform(click());
    }
}