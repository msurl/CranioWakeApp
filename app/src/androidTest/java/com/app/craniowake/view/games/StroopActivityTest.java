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
public class StroopActivityTest {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(StroopActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.stroop_act_id)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfUIKomponentsDisplayed() {
        onView(withId(R.id.finish_stroop_game))
                .check(matches(isDisplayed()));
        onView(withId(R.id.textView3))
                .check(matches(isDisplayed()));
        onView(withId(R.id.stroopFalseButton))
                .check(matches(isDisplayed()));
        onView(withId(R.id.stroopink))
                .check(matches(isDisplayed()));
        onView(withId(R.id.stroopTrueButton))
                .check(matches(isDisplayed()));
    }
}