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
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4ClassRunner.class)
public class LineBisectionActivityTest {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(LineBisectionActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.mol_act_id)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfUIKomponentsDisplayed() {
        onView(withId(R.id.middle_of_line_area))
                .check(matches(isDisplayed()));
        onView(withId(R.id.middle_of_line_yellow_line))
                .check(matches(isDisplayed()));
        onView(withId(R.id.timer_button_mol))
                .check(matches(isDisplayed()));
        onView(withId(R.id.timerView_mol))
                .check(matches(isDisplayed()));
        onView(withId(R.id.finish_game_middle_of_line))
                .check(matches(isDisplayed()));
        onView(withId(R.id.textView3))
                .check(matches(isDisplayed()));
    }
}