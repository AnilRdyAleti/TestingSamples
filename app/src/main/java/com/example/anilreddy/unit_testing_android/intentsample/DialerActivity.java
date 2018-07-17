package com.example.anilreddy.unit_testing_android.intentsample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anilreddy.unit_testing_android.R;

public class DialerActivity extends AppCompatActivity {


    private static final int REQUEST_CODE_PICK = 16;
    private EditText mCallerNumber;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);
        mCallerNumber = findViewById(R.id.edit_text_caller_number);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK) {
            if (resultCode == RESULT_OK) {
                mCallerNumber.setText(data.getExtras()
                        .getString(ContactsActivity.KEY_PHONE_NUMBER));
            }
        }
    }

    private Intent createCallIntentFromNumber() {
        final Intent intentToCall = new Intent(Intent.ACTION_CALL);
        String number = mCallerNumber.getText().toString();
        intentToCall.setData(Uri.parse("tel: " + number));
        return intentToCall;
    }

    public void onCall(View view) {
        boolean hasCallPhonePermission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED;

        if (hasCallPhonePermission) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_CALL_PHONE);
        startActivity(createCallIntentFromNumber());
        } else
        Toast.makeText(this, R.string.warning_call_phone_permission, Toast.LENGTH_LONG).show();

    }

    public void onPickContact(View view) {
        final Intent pickContactIntent = new Intent(this, ContactsActivity.class);
        startActivityForResult(pickContactIntent, REQUEST_CODE_PICK);
    }
}
