package com.example.anilreddy.unit_testing_android.resourcesample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anilreddy.unit_testing_android.R;
import com.example.anilreddy.unit_testing_android.resourcesample.IdlingResource.MessageDelayer;
import com.example.anilreddy.unit_testing_android.resourcesample.IdlingResource.SimpleIdlingResource;

public class ResourceSample extends AppCompatActivity implements View.OnClickListener, MessageDelayer.DelayerCallback {


    private TextView mTextView;
    private EditText mEditText;

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_sample);

        findViewById(R.id.changeTextBt).setOnClickListener(this);
        mTextView = findViewById(R.id.textToBeChanged);
        mEditText = findViewById(R.id.editTextUserInput);
    }

    @Override
    public void onClick(View view) {
        final String text = mEditText.getText().toString();

        if (view.getId() == R.id.changeTextBt) {
            mTextView.setText(R.string.waiting_msg);
            MessageDelayer.processMessage(text, this, mIdlingResource);
        }
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    public void onDone(String text) {
        mTextView.setText(text);
    }
}
