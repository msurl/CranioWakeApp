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

/**
 * Testing display and elements of given Activity.
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class FourSquareActivityTest {
    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(FourSquareActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.fs_act_id)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfUIKomponentsDisplayed() {
        onView(withId(R.id.change_mode_four_square))
                .check(matches(isDisplayed()));
        onView(withId(R.id.textView3))
                .check(matches(isDisplayed()));
        onView(withId(R.id.finish_game_four_square))
                .check(matches(isDisplayed()));
        onView(withId(R.id.firstPicFourSquareButton))
                .check(matches(isDisplayed()));
        onView(withId(R.id.secondPicFourSquareButton))
                .check(matches(isDisplayed()));
        onView(withId(R.id.fourSquare_test_view))
                .check(matches(isDisplayed()));
        onView(withId(R.id.thirdPicFourSquareButton))
                .check(matches(isDisplayed()));
        onView(withId(R.id.fourthPicFourSquareButton))
                .check(matches(isDisplayed()));
    }

}