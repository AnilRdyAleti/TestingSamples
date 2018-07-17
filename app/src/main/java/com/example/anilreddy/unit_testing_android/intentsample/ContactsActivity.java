package com.example.anilreddy.unit_testing_android.intentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;

import com.example.anilreddy.unit_testing_android.R;

public class ContactsActivity extends AppCompatActivity {

    static final String KEY_PHONE_NUMBER = "key_phone_number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setResult(Activity.RESULT_OK, createResultData("896-745-231"));
        finish();
    }

    @VisibleForTesting
    static Intent createResultData(String phoneNumber) {
        final Intent resultData = new Intent();
        resultData.putExtra(KEY_PHONE_NUMBER, phoneNumber);
        return resultData;
    }
}
