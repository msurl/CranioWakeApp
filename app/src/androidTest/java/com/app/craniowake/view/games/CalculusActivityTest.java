package com.app.craniowake.view.games;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.app.craniowake.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class CalculusActivityTest {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(CalculusActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.calc_act_id)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfUIKomponentsDisplayed() {
        onView(withId(R.id.finish_game_calc))
                .check(matches(isDisplayed()));
        onView(withId(R.id.textView3))
                .check(matches(isDisplayed()));
        onView(withId(R.id.firstNumberView))
                .check(matches(isDisplayed()));
        onView(withId(R.id.operatorView))
                .check(matches(isDisplayed()));
        onView(withId(R.id.secondNumberView))
                .check(matches(isDisplayed()));
        onView(withId(R.id.calcAnswer))
                .check(matches(isDisplayed()));
        onView(withId(R.id.next_calc_button))
                .check(matches(isDisplayed()));
    }
}