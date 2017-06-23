package com.planet.noobs.testproject.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.planet.noobs.testproject.R;

import java.util.List;

/**
 * Created by rio on 15/6/17.
 */

public class BooksListAdapter extends RecyclerView.Adapter<BooksListAdapter.BookHolder> {
    private List<String> mBook;

    public BooksListAdapter(List<String> books) {
        mBook = books;
    }

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int i) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.books_list_item, parent, false);
        return new BookHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(BookHolder bookHolder, int position) {
        bookHolder.mItemname.setText(mBook.get(position));
    }

    @Override
    public int getItemCount() {
        Log.v(BooksListAdapter.class.getSimpleName(), " " + mBook.size());
        return mBook.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder {
        //private ImageView mImageView;
        private TextView mItemname;
        private List<String> mBook;

        public BookHolder(View itemView) {
            super(itemView);
            mItemname = (TextView) itemView.findViewById(R.id.item_Book_name);
        }

    }
}
