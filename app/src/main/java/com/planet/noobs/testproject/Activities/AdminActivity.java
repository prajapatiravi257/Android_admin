package com.planet.noobs.testproject.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.planet.noobs.testproject.Helpers.EmptyRecyclerView;
import com.planet.noobs.testproject.Helpers.InputValidation;
import com.planet.noobs.testproject.R;

/**
 * Created by rio on 21/6/17.
 */

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonAddept;
    private TextInputEditText editTextdept;
    private EmptyRecyclerView recyclerViewHods;
    private TextInputLayout inputLayoutDept;
    private InputValidation inputValidation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initViews();
        initListners();
        initObjects();
    }

    private void initViews() {
        editTextdept = (TextInputEditText) findViewById(R.id.editTextDept);
        inputLayoutDept = (TextInputLayout) findViewById(R.id.textInputLayoutDept);
        buttonAddept = (Button) findViewById(R.id.addDept);
        recyclerViewHods = (EmptyRecyclerView) findViewById(R.id.recyclerviewHods);
    }

    private void initListners() {
        buttonAddept.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addDept:
                if (!inputValidation.isInputEditTextFilled(editTextdept, inputLayoutDept, "This should not be empty!")) {
                    return;
                }
                break;
            case R.id.allow_dept:
                break;
            case R.id.deny_dept:
                break;

        }
    }
}
