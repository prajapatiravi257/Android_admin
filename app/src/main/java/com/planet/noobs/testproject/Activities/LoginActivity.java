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
    private Spinner spinner_login;
    private SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //session manager
        session = new SessionManagement(getApplicationContext());

        initViews();
        initListeners();
        initObjects();

        // Create an ArrayAdapter using the string array and a default spinner_login layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.userType, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_login.setAdapter(adapter);
        spinner_login.setSelection(adapter.getPosition(getResources().getString(R.string.default_spinner_usertype_selection)), true);
        spinner_login.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner_login.setSelection(parent.getFirstVisiblePosition(), true);
            }
        });

    }
/*
    private void saveIntoPref(User user){
        editor.putString("userName",user.getName());
        editor.putString("userEmail",user.getEmail());
        editor.commit();
    }
*/

    private void initViews() {
        scrollView = (NestedScrollView) findViewById(R.id.login_form_scrollview);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPasswd = (TextInputLayout) findViewById(R.id.textInputLayoutPasswd);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.email);
        textInputEditTextPasswd = (TextInputEditText) findViewById(R.id.passwd);
        spinner_login = (Spinner) findViewById(R.id.spinner_login);
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
                verifyFromDB();
                break;
            case R.id.textViewRegister:
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                break;
        }
    }

    private void verifyFromDB() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_field_required))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, "Email ID not valid")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPasswd, textInputLayoutPasswd, getString(R.string.error_field_required))) {
            return;
        }

        String name, email;
        email = textInputEditTextEmail.getText().toString().trim();
        name = email.substring(0, email.indexOf("@"));

        if (spinner_login.getSelectedItemPosition() == 0 && dbHelper.checkStudent(email,
                textInputEditTextPasswd.getText().toString().trim())) {

            session.createLoginSession(name, email);
            emptyInputEditText();

            Intent studentIntent = new Intent(getApplicationContext(), StudentActivity.class);
            startActivity(studentIntent);
            Snackbar.make(scrollView, "Logged in successfully", Snackbar.LENGTH_LONG).show();

        } else if (spinner_login.getSelectedItemPosition() == 1 && dbHelper.checkTeacher(email,
                textInputEditTextPasswd.getText().toString().trim())) {

            session.createLoginSession(name, email);
            emptyInputEditText();
            Intent teacherIntent = new Intent(getApplicationContext(), TeacherActivity.class);
            startActivity(teacherIntent);
            Snackbar.make(scrollView, "Logged in successfully", Snackbar.LENGTH_LONG).show();

        } else if (spinner_login.getSelectedItemPosition() == 2 && dbHelper.checkDept(email,
                textInputEditTextPasswd.getText().toString().trim())) {

            session.createLoginSession(name, email);
            emptyInputEditText();
            Intent deptIntent = new Intent(getApplicationContext(), DepartmentActivity.class);
            startActivity(deptIntent);
            Snackbar.make(scrollView, "Logged in successfully", Snackbar.LENGTH_LONG).show();

        } else if (spinner_login.getSelectedItemPosition() == 3 && dbHelper.checkAdmin(email,
                textInputEditTextPasswd.getText().toString().trim())) {

            session.createLoginSession(name, email);
            emptyInputEditText();
            Intent adminIntent = new Intent(getApplicationContext(), AdminActivity.class);
            startActivity(adminIntent);
            Snackbar.make(scrollView, "Logged in successfully", Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(scrollView, "Credentials not valid", Snackbar.LENGTH_SHORT).show();
        }
    }


    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPasswd.setText(null);
    }
}
