package com.planet.noobs.testproject.Activities;

import android.annotation.TargetApi;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.planet.noobs.testproject.Adapters.LecListAdapter;
import com.planet.noobs.testproject.Data.DBHelper;
import com.planet.noobs.testproject.Helpers.EmptyRecyclerView;
import com.planet.noobs.testproject.Helpers.InputValidation;
import com.planet.noobs.testproject.Model.Lectures;
import com.planet.noobs.testproject.R;

import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {

    LecListAdapter listAdapter;
    private TextInputEditText editTextSubject;
    private TextInputLayout textInputLayoutSubject;
    private AppCompatButton buttonTimeSlot;
    private AppCompatTextView textViewTime;
    private AppCompatButton appCompatButtonSave;
    private TimePickerDialog timePickerDialog;
    //private RecyclerView recyclerViewLec;
    //private FloatingActionButton fab;
    private int mHour, mMinute;
    private Lectures lectures;
    private InputValidation inputValidation;
    private DBHelper dbHelper;
    private boolean gotTime = false;
    private List<Lectures> lecturesList;
    private LinearLayout addLecParent;
    private ImageView empty_statview;
    private EmptyRecyclerView recyclerViewLec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        intiViews();
        initListeners();
        initObjects();
    }

    private void intiViews() {
        textViewTime = (AppCompatTextView) findViewById(R.id.timeView);
        buttonTimeSlot = (AppCompatButton) findViewById(R.id.appCompatButtonTimeSlot);
        textInputLayoutSubject = (TextInputLayout) findViewById(R.id.textInputLayoutSubject);
        editTextSubject = (TextInputEditText) findViewById(R.id.appCompatEditTextSubject);
        appCompatButtonSave = (AppCompatButton) findViewById(R.id.appCompatButtonSave);
        //recyclerViewLec = (RecyclerView) findViewById(R.id.recyclerview_lectures);
        //fab = (FloatingActionButton) findViewById(R.id.lec_fab);
        addLecParent = (LinearLayout) findViewById(R.id.parent_addlec);
        empty_statview = (ImageView) findViewById(R.id.empty_image_lec);
        recyclerViewLec = (EmptyRecyclerView) findViewById(R.id.recyclerview_lectures);
        recyclerViewLec.setEmptyView(findViewById(R.id.empty_image_lec));
    }

    private void initListeners() {
        appCompatButtonSave.setOnClickListener(this);
        buttonTimeSlot.setOnClickListener(this);
//        fab.setOnClickListener(this);
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
            case R.id.appCompatButtonTimeSlot:
                getTime();
                break;
            case R.id.appCompatButtonSave:
                postDataToDB();
                getDataFromSQLite();
                break;
            // case R.id.lec_fab:

        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void getTime() {
        // Get Current Time
        final Calendar c;
        c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                textViewTime.setText(new StringBuilder().append(hourOfDay)
                        .append(" : ")
                        .append(minute)
                        .append(getAMPM(hourOfDay)));

                textViewTime.setText(textViewTime.getText().toString());
                gotTime = true;
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private String getAMPM(int hourOfDay) {
        String format;
        if (hourOfDay == 0) {
            hourOfDay += 12;
            format = "AM";
        } else if (hourOfDay == 12) {
            format = "PM";
        } else if (hourOfDay > 12) {
            hourOfDay -= 12;
            format = "PM";
        } else {
            format = "AM";
        }
        return format;
    }

    private void postDataToDB() {
        if (!inputValidation.isInputEditTextSubject(editTextSubject, textInputLayoutSubject, "Enter the subject first.")) {
            return;
        }
        if (!gotTime) {
            Snackbar.make(recyclerViewLec, "Please select the time", Snackbar.LENGTH_LONG).show();
            return;
        }
        lectures.setLecTitle(editTextSubject.getText().toString().trim());
        lectures.setLecDateTime(textViewTime.getText().toString());

        dbHelper.addLec(lectures);
        emptyEditText();
        Toast.makeText(this, "Lecture added", Toast.LENGTH_LONG).show();

    }


    private void emptyEditText() {
        editTextSubject.setText(null);
        textViewTime.setText(null);
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
}

