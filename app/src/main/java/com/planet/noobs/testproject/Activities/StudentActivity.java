package com.planet.noobs.testproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.planet.noobs.testproject.R;

import java.util.HashMap;

/**
 * Created by rio on 15/6/17.
 */

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton buttonIssueBooks;
    private AppCompatButton buttonViewLectures;
    private AppCompatButton buttonViewIssuedBooks;
    private AppCompatTextView textViewGreetName;
    private AppCompatTextView textViewEmail;
    private SessionManagement session;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        initViews();
        initListeners();
        session = new SessionManagement(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(SessionManagement.KEY_NAME);
        String email = user.get(SessionManagement.KEY_EMAIL);
        textViewGreetName.setText(name);
        textViewEmail.setText(email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.menu.logout_menu) {
            session.logoutUser();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void initViews() {
        buttonIssueBooks = (AppCompatButton) findViewById(R.id.btn_issue_books);
        buttonViewLectures = (AppCompatButton) findViewById(R.id.btn_lectures);
        buttonViewIssuedBooks = (AppCompatButton) findViewById(R.id.btn_viewIssuedBooks);
        textViewGreetName = (AppCompatTextView) findViewById(R.id.user_name_view);
        textViewEmail = (AppCompatTextView) findViewById(R.id.email_view);

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
                startActivity(lecIntent);
                break;

        }
    }


}
