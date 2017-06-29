package com.planet.noobs.testproject.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.ui.floatingactionbutton.AddFloatingActionButton;
import com.planet.noobs.testproject.Adapters.LecListAdapter;
import com.planet.noobs.testproject.Data.DBHelper;
import com.planet.noobs.testproject.Helpers.InputValidation;
import com.planet.noobs.testproject.Model.Lectures;
import com.planet.noobs.testproject.R;

import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {

    LecListAdapter listAdapter;
    private AddFloatingActionButton fab;
    private Lectures lectures;
    private InputValidation inputValidation;
    private DBHelper dbHelper;
    private List<Lectures> lecturesList;
    private UltimateRecyclerView recyclerViewLec;
    private Spinner spinnerLec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        intiViews();
        initListeners();
        initObjects();
    }

    private void intiViews() {
        fab = (AddFloatingActionButton) findViewById(R.id.lec_fab);
        recyclerViewLec = (UltimateRecyclerView) findViewById(R.id.recyclerview_lectures);
        spinnerLec = (Spinner) findViewById(R.id.spinner_lec);
    }

    private void initListeners() {
        fab.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(this);
        lectures = new Lectures();
        lecturesList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewLec.setLayoutManager(mLayoutManager);
        recyclerViewLec.setItemAnimator(new DefaultItemAnimator());
        recyclerViewLec.setHasFixedSize(true);
        dbHelper = new DBHelper(this);

        listAdapter = new LecListAdapter(lecturesList);
        recyclerViewLec.setAdapter(listAdapter);
        getDataFromSQLite();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lec_fab:

                // Create an ArrayAdapter using the string array and a default spinner_login layout
                 /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                         R.array.lecTime, android.R.layout.simple_spinner_item);
                 adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                 spinnerLec.setAdapter(adapter);
                 spinnerLec.setSelection(adapter.getPosition("7.30 - 8.45"), true);*/

                new MaterialDialog.Builder(this)
                        .title("Add Lectures")
/*                         .content("Lectures will be viewed to students also")
                         .inputType(InputType.TYPE_CLASS_TEXT)
                         .input("Subject", null, false, new MaterialDialog.InputCallback() {
                             @Override
                             public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {

                             }
                         })
                         .customView(R.layout.custom_spinner,false)*/
                        .positiveText("Save")
                        .negativeText("Cancel")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            }
                        })
                        .show();

        }
    }
/*

    private void postDataToDB() {
        if (!inputValidation.isInputEditTextSubject(editTextSubject, textInputLayoutSubject, "Enter the subject first.")) {
            return;
        }

        lectures.setLecTitle(editTextSubject.getText().toString().trim());
        //lectures.setLecDateTime(textViewTime.getText().toString());
        lectures.setLecDateTime(spinnerLec.getSelectedItem().toString());
        dbHelper.addLec(lectures);
        emptyEditText();
        Toast.makeText(this, "Lecture added", Toast.LENGTH_LONG).show();

    }


    private void emptyEditText() {
        editTextSubject.setText(null);
        textViewTime.setText(null);
    }
*/

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                lecturesList.clear();
                lecturesList.addAll(dbHelper.getAllLec());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                listAdapter.notifyDataSetChanged();
            }
        }.execute();
    }


}

