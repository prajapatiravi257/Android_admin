package com.planet.noobs.testproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.planet.noobs.testproject.R;

/**
 * Created by rio on 15/6/17.
 */

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton buttonIssueBooks;
    private AppCompatButton buttonViewLectures;
    private AppCompatButton buttonViewIssuedBooks;
    private AppCompatTextView textViewGreetName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        initViews();
        initListeners();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void initViews() {
        buttonIssueBooks = (AppCompatButton) findViewById(R.id.btn_issue_books);
        buttonViewLectures = (AppCompatButton) findViewById(R.id.btn_lectures);
        buttonViewIssuedBooks = (AppCompatButton) findViewById(R.id.btn_viewIssuedBooks);
        textViewGreetName = (AppCompatTextView) findViewById(R.id.welcome_logo);
    }

    private void initListeners() {
        buttonViewIssuedBooks.setOnClickListener(this);
        buttonViewLectures.setOnClickListener(this);
        buttonIssueBooks.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_issue_books:
                Intent BooksListIntent = new Intent(StudentActivity.this, BooksListActivity.class);
                startActivity(BooksListIntent);
                break;

            case R.id.btn_viewIssuedBooks:
                Intent IssuedBooksListIntent = new Intent(StudentActivity.this, IssuedBooksListActivity.class);
                startActivity(IssuedBooksListIntent);
                break;

            case R.id.btn_lectures:
                Intent lecIntent = new Intent(this, TeacherActivity.class);
                break;

        }
    }


}
