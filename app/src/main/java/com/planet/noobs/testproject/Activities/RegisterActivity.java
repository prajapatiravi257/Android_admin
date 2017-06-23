package com.planet.noobs.testproject.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.planet.noobs.testproject.Data.DBHelper;
import com.planet.noobs.testproject.Helpers.InputValidation;
import com.planet.noobs.testproject.Model.User;
import com.planet.noobs.testproject.R;

/**
 * Created by rio on 14/6/17.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private NestedScrollView scrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPasswd;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutContact;
    private TextInputLayout textInputLayoutConfirmPasswd;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPasswd;
    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextContact;
    private TextInputEditText textInputEditTextConfirmPasswd;

    private AppCompatButton btnRegister;
    private AppCompatTextView textViewLogin;

    private InputValidation inputValidation;
    private DBHelper dbHelper;
    private User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        scrollView = (NestedScrollView) findViewById(R.id.login_form_scrollview);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPasswd = (TextInputLayout) findViewById(R.id.textInputLayoutPasswd);
        textInputLayoutContact = (TextInputLayout) findViewById(R.id.textInputLayoutContact);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutConfirmPasswd = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPasswd);


        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.email);
        textInputEditTextPasswd = (TextInputEditText) findViewById(R.id.passwd);
        textInputEditTextName = (TextInputEditText) findViewById(R.id.name);
        textInputEditTextContact = (TextInputEditText) findViewById(R.id.contact);
        textInputEditTextConfirmPasswd = (TextInputEditText) findViewById(R.id.confirm_passwd);

        btnRegister = (AppCompatButton) findViewById(R.id.btn_signup);
        textViewLogin = (AppCompatTextView) findViewById(R.id.textViewLogin);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        btnRegister.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
    }

    private void initObjects() {
        dbHelper = new DBHelper(RegisterActivity.this);
        inputValidation = new InputValidation(RegisterActivity.this);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                postDataToDB();
                break;
            case R.id.textViewLogin:
                finish();
                break;
        }
    }

    private void postDataToDB() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_field_required))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_field_required))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, "Email ID not valid")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextContact, textInputLayoutContact, getString(R.string.error_field_required))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPasswd, textInputLayoutPasswd, getString(R.string.error_field_required))) {
            return;
        }

        if (!inputValidation.isInputEditTextMatches(textInputEditTextPasswd, textInputEditTextConfirmPasswd,
                textInputLayoutConfirmPasswd, "Password Don't match!")) {
            return;
        }

        if (!dbHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {
            user.setContact(Long.parseLong(textInputEditTextContact.getText().toString().trim()));
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setPasswd(textInputEditTextPasswd.getText().toString().trim());

            dbHelper.addUser(user);

            Snackbar.make(scrollView, "Registered successfully", Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        } else {
            Snackbar.make(scrollView, "Email already exists", Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPasswd.setText(null);
        textInputEditTextConfirmPasswd.setText(null);
        textInputEditTextContact.setText(null);
    }

}