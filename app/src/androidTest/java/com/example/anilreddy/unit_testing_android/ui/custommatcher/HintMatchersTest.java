package com.example.anilreddy.unit_testing_android.ui.custommatcher;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.anilreddy.unit_testing_android.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HintMatchersTest {

    private static final String INVALID_STRING_TO_BE_TYPED = "Earl Grey";

    private static final String COFFEE_ENDING = "coffee?";

    private static final String COFFEE_INVALID_ENDING = "tea?";

//    private CustomMatcher mCustomMatcher;

    private String mStringWithValidEnding;
    private String mValidStringToBeTyped;


    @Rule
    public ActivityTestRule<CustomMatcher> mCustomMatcherActivity = new ActivityTestRule<>(CustomMatcher.class);

    @Before
    public void setActivity() {
//        this.mCustomMatcher = mCustomMatcherActivity.getActivity();

        mStringWithValidEnding = "Random " + CustomMatcher.VALID_ENDING;

        mValidStringToBeTyped = CustomMatcher.COFFEE_PREPARATIONS.get(0);
    }

    @Test
    public void hint_isDisplayedInEditText() {
        String hintText = mCustomMatcherActivity.getActivity().getResources().getString(R.string.hint);

        onView(withId(R.id.editText)).check(matches(HintMatcher.withHint(hintText)));
    }

    @Test
    public void hint_endWith() {
        onView(withId(R.id.editText)).check(matches(HintMatcher.withHint(anyOf(endsWith(COFFEE_ENDING), endsWith(COFFEE_INVALID_ENDING)))));
    }

    @Test
    public void editText_canBeTypedInto() {
        onView(withId(R.id.editText)).perform(typeText(mValidStringToBeTyped), closeSoftKeyboard())
                .check(matches(withText(mValidStringToBeTyped)));
    }

    @Test
    public void validation_resultIsOneOfTheValidStrings() {
        onView(withId(R.id.editText)).perform(typeText(mValidStringToBeTyped), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.inputValidationSuccess)).check(matches(isDisplayed()));
        onView(withId(R.id.inputValidationError)).check(matches(not(isDisplayed())));
    }

    @Test
    public void validation_resultHasCorrectEnding() {
        onView(withId(R.id.editText)).perform(typeText(mStringWithValidEnding), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.inputValidationSuccess)).check(matches(isDisplayed()));
        onView(withId(R.id.inputValidationError)).check(matches(not(isDisplayed())));
    }

    @Test
    public void validation_resultIsIncorrect() {
        onView(withId(R.id.editText)).perform(typeText(INVALID_STRING_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.inputValidationError)).check(matches(isDisplayed()));
        onView(withId(R.id.inputValidationSuccess)).check(matches(not(isDisplayed())));
    }

}
