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
import android.widget.ImageView;
import android.widget.Toast;

import com.planet.noobs.testproject.Adapters.BooksListAdapter;
import com.planet.noobs.testproject.Helpers.EmptyRecyclerView;
import com.planet.noobs.testproject.Helpers.RecyclerItemClickListener;
import com.planet.noobs.testproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rio on 15/6/17.
 */

public class BooksListActivity extends AppCompatActivity {
    public static ArrayList<String> issuedBooks;
    private AppCompatTextView textViewBookName;
    private List<String> bookList;
    private BooksListAdapter booksListAdapter;
    //private RecyclerView mrecyclerViewBooks;
    private AppCompatImageView ImageViewBook;
    private EmptyRecyclerView mrecyclerViewBooks;
    private ImageView empty_State;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_list);
        getSupportActionBar();

        initViews();
        initObjects();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void initViews() {
        textViewBookName = (AppCompatTextView) findViewById(R.id.item_Book_name);
        //mrecyclerViewBooks = (RecyclerView) findViewById(R.id.recyclerviewBooks);
        mrecyclerViewBooks = (EmptyRecyclerView) findViewById(R.id.recyclerviewBooks);
        empty_State = (ImageView) findViewById(R.id.empty_image_books);
    }

    private void initObjects() {
        issuedBooks = new ArrayList<String>();
        bookList = new ArrayList<String>();
        dummyBook(bookList);
        booksListAdapter = new BooksListAdapter(bookList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mrecyclerViewBooks.setLayoutManager(mLayoutManager);
        mrecyclerViewBooks.setItemAnimator(new DefaultItemAnimator());
        mrecyclerViewBooks.setHasFixedSize(true);
        mrecyclerViewBooks.setAdapter(booksListAdapter);
        // emptyview set after settingAdapter due to dummydata
        mrecyclerViewBooks.setEmptyView(findViewById(R.id.empty_image_books));

        mrecyclerViewBooks.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Log.d(BooksListActivity.class.getSimpleName(), "CLICK! on position " + position);

                        if (!issuedBooks.contains(bookList.get(position))) {
                            issuedBooks.add(bookList.get(position));
                            Toast.makeText(getApplicationContext(), "Book Issued", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "The Book is already Issued", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
        );

    }

    private void dummyBook(List<String> books) {
        books.add("The Mythical Man-Month");
        books.add("Design Patterns");
        books.add("Programming Pearls (2nd Edition)");
        books.add("The Art of Computer Programming");
        books.add("Refactoring");
        books.add("Clean Code");
        books.add("Introduction to Algorithms");
        books.add("Code Complete 2");
        books.add("Pragmatic Programmer");
    }

}
