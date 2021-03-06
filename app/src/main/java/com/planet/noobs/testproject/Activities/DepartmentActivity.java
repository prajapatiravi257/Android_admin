package com.planet.noobs.testproject.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.planet.noobs.testproject.Adapters.DeptListAdapter;
import com.planet.noobs.testproject.Data.DBHelper;
import com.planet.noobs.testproject.Model.User;
import com.planet.noobs.testproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rio on 21/6/17.
 */

public class DepartmentActivity extends AppCompatActivity {

    private UltimateRecyclerView recyclerViewReqList;
    private List<User> requestList;
    private DBHelper dbHelper;
    private DeptListAdapter listAdapter;
    private ImageView empty_state;
    private SessionManagement session;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_hod);
        recyclerViewReqList = (UltimateRecyclerView) findViewById(R.id.recyclerviewReqList);

        User user = new User();
        session = new SessionManagement(getApplicationContext());

        requestList = new ArrayList<>();
        listAdapter = new DeptListAdapter(requestList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewReqList.setLayoutManager(mLayoutManager);
        recyclerViewReqList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewReqList.setHasFixedSize(true);
        dbHelper = new DBHelper(this);

        recyclerViewReqList.setAdapter(listAdapter);
        getDataFromSQLite();
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
        return  true;
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                requestList.clear();
                requestList.addAll(dbHelper.getAllUnapprovedStudent());
                requestList.addAll(dbHelper.getAllUnapprovedTeacher());
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
