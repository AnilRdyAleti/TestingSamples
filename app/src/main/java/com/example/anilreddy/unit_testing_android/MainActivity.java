package com.example.anilreddy.unit_testing_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mViewMessage;
    EditText mUserEnterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }

    private void initUi() {
        mViewMessage = findViewById(R.id.textToBeChanged);
        mUserEnterText = findViewById(R.id.editTextUserInput);

        findViewById(R.id.changeTextBt).setOnClickListener(this);
        findViewById(R.id.activityChangeTextBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String text = mUserEnterText.getText().toString();

        int mChangeTextBtn = R.id.changeTextBt;
        int mChangeActivityBtn = R.id.activityChangeTextBtn;

        if (view.getId() == mChangeTextBtn) {
            mViewMessage.setText(text);
        } else if (view.getId() == mChangeActivityBtn) {
            Intent intent = com.example.anilreddy.unit_testing_android.ShowTextActivity.newStartIntent(this, text);
            startActivity(intent);
        }
//        switch (view.getId()) {
//            case R.id.changeTextBt:
//                // First button's interaction: set a text in a text view.
//                mViewMessage.setText(text);
//                break;
//            case R.id.activityChangeTextBtn:
//                // Second button's interaction: start an activity and send a message to it.
//                Intent intent = ShowTextActivity.newStartIntent(this, text);
//                startActivity(intent);
//                break;
//        }
    }
}
