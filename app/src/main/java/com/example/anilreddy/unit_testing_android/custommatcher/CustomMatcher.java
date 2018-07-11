package com.example.anilreddy.unit_testing_android.custommatcher;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.anilreddy.unit_testing_android.R;

import java.util.Arrays;
import java.util.List;

public class CustomMatcher extends AppCompatActivity implements View.OnClickListener {

    @VisibleForTesting
    public static final List<String> COFFEE_PREPARATIONS = Arrays.asList("Espresso", "Latte", "Mocha", "Café con leche", "Cold brew");

    @VisibleForTesting
    public static final String VALID_ENDING = "coffee";

    private EditText mUserInputText;
    private View mSuccessView;
    private View mErrorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_matcher);
        initUi();
    }

    private void initUi() {
        findViewById(R.id.button).setOnClickListener(this);
        mUserInputText = findViewById(R.id.editText);
        mSuccessView = findViewById(R.id.inputValidationSuccess);
        mErrorView = findViewById(R.id.inputValidationError);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            final String input = mUserInputText.getText().toString();
            showResult(validateText(input));
        }
    }

    public void showResult(boolean isValidResult) {
        mSuccessView.setVisibility(isValidResult ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(isValidResult ? View.GONE : View.VISIBLE);

    }

    public static boolean validateText(String input) {
        if (input.toLowerCase().endsWith(VALID_ENDING)) {
            return true;
        }

        for (String preparation : COFFEE_PREPARATIONS) {
            if (preparation.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }
}
