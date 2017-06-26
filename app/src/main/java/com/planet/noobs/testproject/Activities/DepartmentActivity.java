package com.planet.noobs.testproject.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.planet.noobs.testproject.Adapters.DeptListAdapter;
import com.planet.noobs.testproject.Data.DBHelper;
import com.planet.noobs.testproject.Helpers.EmptyRecyclerView;
import com.planet.noobs.testproject.Model.User;
import com.planet.noobs.testproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rio on 21/6/17.
 */

public class DepartmentActivity extends AppCompatActivity {

    private EmptyRecyclerView recyclerViewReqList;
    private List<User> studentList;
    private DBHelper dbHelper;
    private DeptListAdapter listAdapter;
    private ImageView empty_state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_hod);
        recyclerViewReqList = (EmptyRecyclerView) findViewById(R.id.recyclerviewReqList);
        empty_state = (ImageView) findViewById(R.id.empty_image_hod);
        recyclerViewReqList.setEmptyView(findViewById(R.id.empty_image_hod));
        User user = new User();
        studentList = new ArrayList<>();
        listAdapter = new DeptListAdapter(studentList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewReqList.setLayoutManager(mLayoutManager);
        recyclerViewReqList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewReqList.setHasFixedSize(true);
        dbHelper = new DBHelper(this);

        recyclerViewReqList.setAdapter(listAdapter);
        getDataFromSQLite();
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                studentList.clear();
                studentList.addAll(dbHelper.getAllUnapprovedStudent());
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
