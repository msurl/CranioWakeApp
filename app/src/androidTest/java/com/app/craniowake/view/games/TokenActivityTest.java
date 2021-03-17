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
public class TokenActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(TokenActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.token_act_id)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfUIKomponentsDisplayed() {
        onView(withId(R.id.findTheToken))
                .check(matches(isDisplayed()));
        onView(withId(R.id.token_description_txt))
                .check(matches(isDisplayed()));
        onView(withId(R.id.finish_token_game))
                .check(matches(isDisplayed()));
        onView(withId(R.id.bigWhiteCircle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.smallYellowCircle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.bigBlackSquare))
                .check(matches(isDisplayed()));
        onView(withId(R.id.smallBlueSquare))
                .check(matches(isDisplayed()));
        onView(withId(R.id.bigRedCircle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.smallBlackCircle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.bigGreenSquare))
                .check(matches(isDisplayed()));
        onView(withId(R.id.smallPinkSquare))
                .check(matches(isDisplayed()));
        onView(withId(R.id.bigYellowCircle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.smallGreenCircle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.bigWhiteSquare))
                .check(matches(isDisplayed()));
        onView(withId(R.id.smallYellowSquare))
                .check(matches(isDisplayed()));
        onView(withId(R.id.bigBlackCircle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.smallRedCircle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.bigYellowSquare))
                .check(matches(isDisplayed()));
        onView(withId(R.id.smallGreenSquare))
                .check(matches(isDisplayed()));
        onView(withId(R.id.bigGreenCircle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.smallWhiteCircle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.bigRedSquare))
                .check(matches(isDisplayed()));
        onView(withId(R.id.smallBlackSquare))
                .check(matches(isDisplayed()));
        onView(withId(R.id.bigPinkCircle))
                .check(matches(isDisplayed()));
        onView(withId(R.id.smallWhiteSquare))
                .check(matches(isDisplayed()));
        onView(withId(R.id.bigBlueSquare))
                .check(matches(isDisplayed()));
        onView(withId(R.id.smallRedSquare))
                .check(matches(isDisplayed()));
    }
}