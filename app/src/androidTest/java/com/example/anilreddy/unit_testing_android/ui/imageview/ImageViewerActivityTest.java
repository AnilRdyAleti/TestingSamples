package com.example.anilreddy.unit_testing_android.ui.imageview;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.example.anilreddy.unit_testing_android.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.app.Instrumentation.ActivityResult;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.anilreddy.unit_testing_android.ui.imageview.ImageViewHasDrawableMatcher.hasDrawable;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ImageViewerActivityTest {

    @Rule
    public IntentsTestRule<ImageViewerActivity> mImageViewIntentRule =
            new IntentsTestRule<>(ImageViewerActivity.class);

    @Before
    public void subCameraIntent() {

        ActivityResult activityResult = createImageCaptureActivityResultStub();

        intending(hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(activityResult);
    }

    @Test
    public void takePhoto_drawableIsApplied() {
        onView(withId(R.id.imageView)).check(matches(not(hasDrawable())));

        onView(withId(R.id.button_take_photo)).perform(click());

        onView(withId(R.id.imageView)).check(matches(hasDrawable()));
    }

    public ActivityResult createImageCaptureActivityResultStub() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ImageViewerActivity.KEY_IMAGE_DATA,
                BitmapFactory.decodeResource(mImageViewIntentRule.getActivity().getResources(),
                        R.mipmap.ic_launcher));

        Intent resultData = new Intent();
        resultData.putExtras(bundle);

        return new ActivityResult(Activity.RESULT_OK, resultData);
    }
}
