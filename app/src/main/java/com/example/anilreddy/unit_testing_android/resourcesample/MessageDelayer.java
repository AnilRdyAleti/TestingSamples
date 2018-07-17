package com.example.anilreddy.unit_testing_android.resourcesample;

import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.anilreddy.unit_testing_android.resourcesample.IdlingResource.SimpleIdlingResource;

public class MessageDelayer {
    private static final int DELAY_MILLIS = 3000;

    public interface DelayerCallback {
        void onDone(String text);
    }

    public static void processMessage(final String message, final DelayerCallback callback,
                                      @Nullable final SimpleIdlingResource idlingResource) {
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (callback != null) {
                callback.onDone(message);
            }
            if (idlingResource != null) {
                idlingResource.setIdleState(true);
            }
        }, DELAY_MILLIS);
    }
}
