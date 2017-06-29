package com.planet.noobs.testproject.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.planet.noobs.testproject.Adapters.IssuedBooksListAdapter;
import com.planet.noobs.testproject.Helpers.RecyclerItemClickListener;
import com.planet.noobs.testproject.R;

import java.util.ArrayList;
import java.util.List;

import static com.planet.noobs.testproject.Activities.BooksListActivity.issuedBooks;

/**
 * Created by rio on 22/6/17.
 */

public class IssuedBooksListActivity extends AppCompatActivity {

    private AppCompatTextView textViewBookName;
    private IssuedBooksListAdapter issuedBooksListAdapter;
    //    private RecyclerView mrecyclerViewBooks;
    private UltimateRecyclerView mrecyclerViewBooks;
    private AppCompatImageView ImageViewBook;
    private List<String> bookList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.issued_bookslist);
        initViews();
        initObjects();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void initViews() {
        textViewBookName = (AppCompatTextView) findViewById(R.id.item_issued_Book_name);
        //mrecyclerViewBooks = (RecyclerView) findViewById(R.id.recyclerviewIssuedBooks);
        mrecyclerViewBooks = (UltimateRecyclerView) findViewById(R.id.recyclerviewIssuedBooks);
    }

    private void initObjects() {
        //new empty array list for the listview
        bookList = new ArrayList<String>();
        if (issuedBooks != null) {
            bookList.addAll(issuedBooks);
        }
        issuedBooksListAdapter = new IssuedBooksListAdapter(bookList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mrecyclerViewBooks.setLayoutManager(mLayoutManager);
        mrecyclerViewBooks.setItemAnimator(new DefaultItemAnimator());
        mrecyclerViewBooks.setHasFixedSize(true);
        mrecyclerViewBooks.setAdapter(issuedBooksListAdapter);

        issuedBooksListAdapter.notifyDataSetChanged();

        mrecyclerViewBooks.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Log.d(IssuedBooksListActivity.class.getSimpleName(), "CLICK! on position " + position);
                    }
                })
        );
    }
}
