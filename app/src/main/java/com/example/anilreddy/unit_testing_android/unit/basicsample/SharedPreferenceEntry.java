package com.example.anilreddy.unit_testing_android.unit.basicsample;

import java.util.Calendar;

public class SharedPreferenceEntry {

    private final String mName;
    private final Calendar mDateOfBirth;
    private final String mEmail;

    public SharedPreferenceEntry(String name, Calendar dob, String email) {
        mName = name;
        mDateOfBirth = dob;
        mEmail = email;
    }

    public String getName() {
        return mName;
    }

    public Calendar getDateOfBirth() {
        return mDateOfBirth;
    }

    public String getEmail() {
        return mEmail;
    }
}
