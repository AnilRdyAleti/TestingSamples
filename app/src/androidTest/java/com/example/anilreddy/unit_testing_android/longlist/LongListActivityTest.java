package com.example.anilreddy.unit_testing_android.longlist;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.anilreddy.unit_testing_android.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LongListActivityTest {

    private static final String TEXT_ITEM_30 = "item: 30";
    private static final String TEXT_ITEM_30_SELECTED = "30";
    private static final String TEXT_ITEM_60 = "item: 60";
    private static final String LAST_ITEM_ID = "item: 99";

    private LongListActivity mLongListActivity;

    @Rule
    public ActivityTestRule<LongListActivity> mLongListActivityActivityTestRule = new ActivityTestRule<>(LongListActivity.class, true, true);


    @Before
    public void setActivity() {
        mLongListActivity = mLongListActivityActivityTestRule.getActivity();
    }

    @Test
    public void list_lastItem_NotDisplayed() {
        onView(withText(LAST_ITEM_ID)).check(doesNotExist());
    }

    @Test
    public void list_Scrolls() {
        onRow(LAST_ITEM_ID).check(matches(isCompletelyDisplayed()));
    }

    @Test
    public void row_Click() {
        onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowContentTextView)).perform(click());

        onView(withId(R.id.selection_row_value)).check(matches(withText(TEXT_ITEM_30_SELECTED)));
    }

    @Test
    public void toggle_Click() {
        onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowToggleButton)).perform(click());

        onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowToggleButton)).check(matches(isChecked()));
    }

    @Test
    public void toggle_ClickDoesntPropagate() {
        onRow(TEXT_ITEM_30).onChildView(withId(R.id.rowContentTextView)).perform(click());

        onRow(TEXT_ITEM_60).onChildView(withId(R.id.rowToggleButton)).perform(click());

        onView(ViewMatchers.withId(R.id.selection_row_value)).check(matches(withText(TEXT_ITEM_30_SELECTED)));
    }

    private static DataInteraction onRow(String str) {
        return onData(hasEntry(equalTo(LongListActivity.ROW_TEXT), is(str)));
    }
}
