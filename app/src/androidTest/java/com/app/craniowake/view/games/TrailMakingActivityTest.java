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
public class TrailMakingActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(TrailMakingActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.tw_act_id)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfUIKomponentsDisplayed() {
        onView(withId(R.id.frame_connect))
                .check(matches(isDisplayed()));
        onView(withId(R.id.textView3))
                .check(matches(isDisplayed()));
        onView(withId(R.id.change_mode_trailway_game))
                .check(matches(isDisplayed()));
        onView(withId(R.id.finish_trailway_game))
                .check(matches(isDisplayed()));
        onView(withId(R.id.restart_trailMaking_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.next_set_dots))
                .check(matches(isDisplayed()));
    }
}