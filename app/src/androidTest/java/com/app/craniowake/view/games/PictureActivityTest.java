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
public class PictureActivityTest {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(PictureActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.picture_act_id)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfUIKomponentsDisplayed() {
        onView(withId(R.id.pictureFalseButton))
                .check(matches(isDisplayed()));
        onView(withId(R.id.picture_test_view))
                .check(matches(isDisplayed()));
        onView(withId(R.id.pictureTrueButton))
                .check(matches(isDisplayed()));
        onView(withId(R.id.textView3))
                .check(matches(isDisplayed()));
        onView(withId(R.id.change_mode_picture))
                .check(matches(isDisplayed()));
        onView(withId(R.id.finish_game_picture))
                .check(matches(isDisplayed()));
    }
}