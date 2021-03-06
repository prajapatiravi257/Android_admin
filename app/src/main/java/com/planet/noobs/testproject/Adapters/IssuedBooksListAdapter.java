package com.planet.noobs.testproject.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.planet.noobs.testproject.R;

import java.util.List;

/**
 * Created by rio on 22/6/17.
 */

public class IssuedBooksListAdapter extends UltimateViewAdapter<IssuedBooksListAdapter.BookHolder> {
    private List<String> mBook;

    public IssuedBooksListAdapter(List<String> books) {
        mBook = books;
    }

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent) {
        // return view holder for your normal list item
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.issued_bookslist_item, parent, false);
        return new BookHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(BookHolder holder, int position) {
        holder.mItemname.setText(mBook.get(position));
    }

    @Override
    public int getAdapterItemCount() {
        Log.v(BooksListAdapter.class.getSimpleName(), " " + mBook.size());
        return mBook.size();
    }

    @Override
    public BookHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public BookHolder newHeaderHolder(View view) {
        return null;
    }


    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    public class BookHolder extends RecyclerView.ViewHolder {
        //private ImageView mImageView;
        private TextView mItemname;
        private List<String> mBook;

        public BookHolder(View itemView) {
            super(itemView);
            mItemname = (TextView) itemView.findViewById(R.id.item_issued_Book_name);
        }
    }
}
