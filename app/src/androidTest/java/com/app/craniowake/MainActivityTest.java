package com.app.craniowake;

import android.content.Context;
import android.view.Gravity;
import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.platform.app.InstrumentationRegistry;

import com.app.craniowake.view.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule rule = new ActivityScenarioRule<>(MainActivity.class);
    private View decorView;

    @Before
    public void setUp() {
        rule.getScenario().onActivity((ActivityScenario.ActivityAction<MainActivity>) activity -> decorView = activity.getWindow().getDecorView());
    }

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.app.craniowake", appContext.getPackageName());
    }

    @Test
    public void onCreate() {
        onView(withId(R.id.menu_start_screen)).check(matches(isDisplayed()));
    }

    @Test
    public void checkIfUIKomponentsDisplayed() {
        onView(withId(R.id.current_patient_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.select_opMode_Text_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.spinnerGameMode))
                .check(matches(isDisplayed()));
        onView(withId(R.id.select_BrainArea_Text_id))
                .check(matches(isDisplayed()));
        onView(withId(R.id.start_operation_button))
                .check(matches(isDisplayed()));
        onView(withId(R.id.nav_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkCorrectText() {
        onView(withId(R.id.current_patient_id))
                .check(matches(withText(R.string.no_patient_selected)));
        onView(withId(R.id.select_opMode_Text_id))
                .check(matches(withText(R.string.select_operation_mode)));
        onView(withId(R.id.select_BrainArea_Text_id))
                .check(matches(withText(R.string.select_brain_area)));
    }

    @Test
    public void notStartOperationWithoutPatientSelected() {
        onView(withId(R.id.current_patient_id))
                .check(matches(withText(R.string.no_patient_selected)));
        onView(withId(R.id.start_operation_button)).perform(click());

        onView(withText("Please select a patient")).
                inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickOnYourNavigationItem_ShowsYourScreen() throws InterruptedException{

        onView(withId(R.id.nav_view))
                .check(matches(isClosed(Gravity.START)))
                .perform(DrawerActions.open());

        onView(withId(R.id.nav_view))
                .perform(NavigationViewActions.navigateTo(R.id.item_newUserId));

        onView(withId(R.id.user_add_patient_id)).check(matches(isDisplayed()));
    }

}
