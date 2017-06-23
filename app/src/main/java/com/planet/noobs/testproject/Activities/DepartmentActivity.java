package com.planet.noobs.testproject.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.planet.noobs.testproject.R;

/**
 * Created by rio on 21/6/17.
 */

public class DepartmentActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerViewReqList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_hod);
        recyclerViewReqList = (RecyclerView) findViewById(R.id.recyclerviewReqList);

    }

    @Override
    public void onClick(View v) {

    }
}
