package com.planet.noobs.testproject.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.ui.floatingactionbutton.AddFloatingActionButton;
import com.planet.noobs.testproject.Adapters.AdminListAdapter;
import com.planet.noobs.testproject.Data.DBHelper;
import com.planet.noobs.testproject.Helpers.InputValidation;
import com.planet.noobs.testproject.Model.User;
import com.planet.noobs.testproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rio on 21/6/17.
 */

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {
    private UltimateRecyclerView recyclerViewHods;
    private List<User> listDeptReq;
    private AdminListAdapter adminListAdapter;
    private User user;
    private DBHelper dbHelper;
    private InputValidation inputValidation;
    private AddFloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initViews();
        initListners();
        initObjects();

        /*recyclerViewHods.setParallaxHeader(getLayoutInflater().inflate(R.layout.parallax_recyclerview_header, recyclerViewHods.mRecyclerView, false));
        recyclerViewHods.setOnParallaxScroll(new UltimateRecyclerView.OnParallaxScroll() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onParallaxScroll(float percentage, float offset, View parallax) {
                Toolbar toolbar = new Toolbar(getApplicationContext());
                Drawable c = toolbar.getBackground();
                c.setAlpha(Math.round(127 + percentage * 128));
                toolbar.setBackgroundDrawable(c);
            }
        });*/

    }

    private void initViews() {
        recyclerViewHods = (UltimateRecyclerView) findViewById(R.id.recyclerviewHods);
        floatingActionButton = (AddFloatingActionButton) findViewById(R.id.fab_addDept);
    }

    private void initListners() {
        floatingActionButton.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(this);
        listDeptReq = new ArrayList<>();
        dbHelper = new DBHelper(this);
        adminListAdapter = new AdminListAdapter(listDeptReq);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewHods.setLayoutManager(mLayoutManager);
        recyclerViewHods.setItemAnimator(new DefaultItemAnimator());
        recyclerViewHods.setHasFixedSize(true);
        recyclerViewHods.setAdapter(adminListAdapter);
        getDataFromSQLite();
        recyclerViewHods.setParallaxHeader(getLayoutInflater().inflate(R.layout.parallax_recyclerview_header, recyclerViewHods.mRecyclerView, false));

        recyclerViewHods.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDataFromSQLite();
                        recyclerViewHods.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_addDept:
                new MaterialDialog.Builder(this)
                        .title("Add Department")
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .input("Department name", null, false, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {

                            }
                        })
                        .positiveText("Save")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            }
                        })
                        .negativeText("Cancel")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            }
                        })
                        .show();

                break;
        }
    }


    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listDeptReq.clear();
                listDeptReq.addAll(dbHelper.getAllUnapprovedDept());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adminListAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
