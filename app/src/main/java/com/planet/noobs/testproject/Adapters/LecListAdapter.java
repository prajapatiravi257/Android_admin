package com.planet.noobs.testproject.Adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.planet.noobs.testproject.Data.DBHelper;
import com.planet.noobs.testproject.Model.Lectures;
import com.planet.noobs.testproject.R;

import java.util.List;


public class LecListAdapter extends UltimateViewAdapter<LecListAdapter.LecViewHolder> {
    public static final String LOG_TAG = LecListAdapter.class.getSimpleName();
    private List<Lectures> mlec;
    private Lectures lec;
    private DBHelper dbHelper;

    public LecListAdapter(List<Lectures> mlec) {
        this.mlec = mlec;
    }

    @Override
    public LecViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_lec_item, parent, false);
        return new LecViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final LecViewHolder holder, final int position) {
        holder.textViewLecTime.setText(mlec.get(position).getLecDateTime());
        holder.textViewSub.setText(mlec.get(position).getLecTitle());

        holder.deletelec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new DBHelper(v.getContext());
                removeItem(holder.getAdapterPosition());
            }
        });
    }

    public void removeItem(int position) {
        //init objects
        lec = new Lectures();
        lec.setLecId(position);
        //delete method called from dbhelper
        dbHelper.deleteLec(lec);
        //remove item form list
        mlec.remove(position);
        //notify that item is removed
        notifyItemRemoved(position);
    }


    @Override
    public int getAdapterItemCount() {
        Log.v(LecListAdapter.class.getSimpleName(), String.valueOf(mlec.size()));
        return mlec.size();
    }

    @Override
    public LecViewHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public LecViewHolder newHeaderHolder(View view) {
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

    public class LecViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView textViewSub;
        private AppCompatTextView textViewLecTime;
        private ImageButton deletelec;

        public LecViewHolder(View itemView) {
            super(itemView);
            textViewSub = (AppCompatTextView) itemView.findViewById(R.id.lec_title);
            textViewLecTime = (AppCompatTextView) itemView.findViewById(R.id.lec_time);
            deletelec = (ImageButton) itemView.findViewById(R.id.deleteLec);
        }
    }
}
