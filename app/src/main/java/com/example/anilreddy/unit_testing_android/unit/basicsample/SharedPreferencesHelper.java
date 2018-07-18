package com.example.anilreddy.unit_testing_android.unit.basicsample;

import android.content.SharedPreferences;

import java.util.Calendar;

class SharedPreferencesHelper {

    static final String KEY_NAME = "key_name";
    static final String KEY_DOB = "key_dob_millis";
    static final String KEY_EMAIL = "key_email";

    private static SharedPreferences mSharedPreferences;

    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        sharedPreferences = mSharedPreferences;
    }

    public boolean savePersonalInfo(SharedPreferenceEntry mSharedPreferenceEntry) {

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        editor.putString(KEY_NAME, mSharedPreferenceEntry.getName());
        editor.putLong(KEY_DOB, mSharedPreferenceEntry.getDateOfBirth().getTimeInMillis());
        editor.putString(KEY_EMAIL, mSharedPreferenceEntry.getEmail());

        return editor.commit();
    }

    public SharedPreferenceEntry getPersonalInfo() {
        String name = mSharedPreferences.getString(KEY_NAME, " ");
        Long dobMills = mSharedPreferences.getLong(KEY_DOB, Calendar.getInstance().getTimeInMillis());
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTimeInMillis(dobMills);
        String email = mSharedPreferences.getString(KEY_EMAIL, " ");

        return new SharedPreferenceEntry(name, dateOfBirth, email);
    }

}
