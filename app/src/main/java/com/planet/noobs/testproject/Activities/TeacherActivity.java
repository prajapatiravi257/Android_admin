package com.planet.noobs.testproject.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private LecListAdapter listAdapter;
    private AddFloatingActionButton fab;
    private Lectures lectures;
    private InputValidation inputValidation;
    private DBHelper dbHelper;
    private List<Lectures> lecturesList;
    private UltimateRecyclerView recyclerViewLec;
    private Spinner spinnerLec;
    private TextInputEditText editTextSubject;
    private TextInputLayout textInputLayoutSubject;
    private SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        session = new SessionManagement(this);
        intiViews();
        initListeners();
        initObjects();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                Toast.makeText(getApplicationContext(),"Logging out...",Toast.LENGTH_SHORT).show();
                session.logoutUser();
                return  true;
        }
        return true;
    }

    private void intiViews() {
        fab = (AddFloatingActionButton) findViewById(R.id.lec_fab);
        recyclerViewLec = (UltimateRecyclerView) findViewById(R.id.recyclerview_lectures);
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

                MaterialDialog dialog = new MaterialDialog.Builder(this)
                        .title("Add Lectures")
                        .customView(R.layout.custom_spinner,false)
                        .positiveText("Save")
                        .negativeText("Cancel")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            postDataToDB();
                            }
                        })
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            }
                        })
                        .show();
                fullDialogCreation(dialog);
        }
    }

    private void fullDialogCreation(MaterialDialog dialog) {

        View view = dialog.getCustomView();
        spinnerLec = (Spinner) view.findViewById(R.id.spinner_lec);
        editTextSubject = (TextInputEditText) view.findViewById(R.id.editTextSubject);
        textInputLayoutSubject = (TextInputLayout) view.findViewById(R.id.textInputLayoutSubject);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lecTime, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerLec.setAdapter(adapter);
        spinnerLec.setSelection(adapter.getPosition("7.30 - 8.45"), true);
        spinnerLec.setOnItemSelectedListener(this);
    }

    private void postDataToDB() {
        if (!inputValidation.isInputEditTextSubject(editTextSubject, textInputLayoutSubject, "Enter the subject first.")) {
            return;
        }

        lectures.setLecTitle(editTextSubject.getText().toString().trim());
        lectures.setLecDateTime(spinnerLec.getSelectedItem().toString());
        dbHelper.addLec(lectures);
        listAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Lecture added", Toast.LENGTH_LONG).show();
    }

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

