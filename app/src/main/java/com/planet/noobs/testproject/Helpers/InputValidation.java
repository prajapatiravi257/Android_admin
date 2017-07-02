package com.planet.noobs.testproject.Helpers;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by rio on 14/6/17.
 */

public class InputValidation {
    private Context context;

    public InputValidation(Context context) {
        this.context = context;
    }

    public boolean isInputEditTextSubject(TextInputEditText textInputEditTextSubject, TextInputLayout textInputLayoutSubject, String message) {
        String value = textInputEditTextSubject.getText().toString().trim();
        if (value.isEmpty()) {
            textInputEditTextSubject.setError(message);
            hideKeyboardFrom(textInputLayoutSubject);
            return false;
        } else {
            textInputLayoutSubject.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputLayout);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextEmail(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputLayout);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean isPasswordvalid(TextInputEditText textInputEditText, TextInputLayout textInputLayout){
    String value = textInputEditText.getText().toString().trim();
    if(value.length() < 4){
        textInputLayout.setError("Password is to short, try more then 4 characters");
        hideKeyboardFrom(textInputLayout);
        return false;
    }else if (value.length() > 10){
        textInputLayout.setError("Password should not exceed limit of 10");
        hideKeyboardFrom(textInputLayout);
        return false;
    }else {
        textInputLayout.setErrorEnabled(false);
    }
    return true;
    }

    public boolean isValidMobile(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        String mobilePattern = "[0-9]{10}";
        if(!value.matches(mobilePattern)){
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputLayout);
            return false;
        }else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
    private void hideKeyboardFrom(View view) {
        InputMethodManager input = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        input.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }
}
