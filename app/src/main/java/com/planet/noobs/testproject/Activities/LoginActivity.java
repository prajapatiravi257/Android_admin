package com.planet.noobs.testproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.planet.noobs.testproject.Data.DBHelper;
import com.planet.noobs.testproject.Helpers.InputValidation;
import com.planet.noobs.testproject.Model.User;
import com.planet.noobs.testproject.R;

/**
 * Created by rio on 14/6/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private NestedScrollView scrollView;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPasswd;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPasswd;

    private AppCompatButton btnLogin;
    private AppCompatTextView textViewRegister;
    private InputValidation inputValidation;
    private DBHelper dbHelper;
    private User user;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListeners();
        initObjects();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.userType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getPosition(getResources().getString(R.string.default_spinner_selection)), true);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.setSelection(parent.getFirstVisiblePosition(), true);
            }
        });

    }

    private void initViews() {
        scrollView = (NestedScrollView) findViewById(R.id.login_form_scrollview);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPasswd = (TextInputLayout) findViewById(R.id.textInputLayoutPasswd);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.email);
        textInputEditTextPasswd = (TextInputEditText) findViewById(R.id.passwd);
        spinner = (Spinner) findViewById(R.id.spinner);
        btnLogin = (AppCompatButton) findViewById(R.id.btn_signin);
        textViewRegister = (AppCompatTextView) findViewById(R.id.textViewRegister);
    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        btnLogin.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);
    }

    private void initObjects() {
        dbHelper = new DBHelper(LoginActivity.this);
        inputValidation = new InputValidation(LoginActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signin:
                //login usertype with spinners
                if (verifyFromDB()) {

                    if (spinner.getSelectedItemPosition() == 0) {
                        Intent studentIntent = new Intent(getApplicationContext(), StudentActivity.class);
                        startActivity(studentIntent);
                    }
                    if (spinner.getSelectedItemPosition() == 1) {
                        Intent teacherIntent = new Intent(getApplicationContext(), TeacherActivity.class);
                        startActivity(teacherIntent);
                    }
                    if (spinner.getSelectedItemPosition() == 2) {
                        Intent deptIntent = new Intent(getApplicationContext(), DepartmentActivity.class);
                        startActivity(deptIntent);
                    }
                    if (spinner.getSelectedItemPosition() == 3) {
                        Intent adminIntent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(adminIntent);
                    }
                }
                break;
            case R.id.textViewRegister:
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                break;
        }
    }

    private boolean verifyFromDB() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_field_required))) {
            return false;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, "Email ID not valid")) {
            return false;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPasswd, textInputLayoutPasswd, getString(R.string.error_field_required))) {
            return false;
        }
        if (dbHelper.checkUser(textInputEditTextEmail.getText().toString().trim(),
                textInputEditTextPasswd.getText().toString().trim())) {
            emptyInputEditText();
            Snackbar.make(scrollView, "Logged in successfully", Snackbar.LENGTH_LONG).show();
            return true;
        } else {
            Snackbar.make(scrollView, "Credentials not valid", Snackbar.LENGTH_SHORT).show();
            return false;
        }
    }

    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPasswd.setText(null);
    }
}
