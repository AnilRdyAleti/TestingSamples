package com.example.anilreddy.unit_testing_android.custommatcher;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.is;

public class HintMatcher {

    static Matcher<View> withHint(final String subString) {
        return withHint(is(subString));
    }

    static Matcher<View> withHint(final Matcher<String> stringMatcher) {
        checkNotNull(stringMatcher);
        return new BoundedMatcher<View, EditText>(EditText.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with hint: ");
                stringMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(EditText view) {
                final CharSequence hint = view.getHint();
                return hint != null && stringMatcher.matches(hint.toString());
            }
        };
    }
}
