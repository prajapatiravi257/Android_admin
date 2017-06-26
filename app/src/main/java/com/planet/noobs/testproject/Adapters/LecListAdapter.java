package com.planet.noobs.testproject.Adapters;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.planet.noobs.testproject.Data.DBHelper;
import com.planet.noobs.testproject.Model.Lectures;
import com.planet.noobs.testproject.R;

import java.util.List;


public class LecListAdapter extends RecyclerView.Adapter<LecListAdapter.LecViewHolder> {
    public static final String LOG_TAG = LecListAdapter.class.getSimpleName();
    private List<Lectures> mlec;
    private Context mcontext;
    public LecListAdapter(List<Lectures> mlec) {
        this.mlec = mlec;
    }


    @Override
    public LecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
                DBHelper dbHelper = new DBHelper(mcontext);
                Lectures lec = new Lectures();
                lec.setLecId(position);
                dbHelper.deleteLec(lec);
                mlec.remove(position);
                Log.v(LOG_TAG, "Click!");

            }
        });
    }

    @Override
    public int getItemCount() {
        Log.v(LecListAdapter.class.getSimpleName(), String.valueOf(mlec.size()));
        return mlec.size();
    }

    public class LecViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView textViewSub;
        private AppCompatTextView textViewLecTime;
        private ImageButton deletelec;
        //private TextView emptyView;
        public LecViewHolder(View itemView) {
            super(itemView);
            //emptyView = (TextView) itemView.findViewById(R.id.emptyView);
            textViewSub = (AppCompatTextView) itemView.findViewById(R.id.lec_title);
            textViewLecTime = (AppCompatTextView) itemView.findViewById(R.id.lec_time);
            deletelec = (ImageButton) itemView.findViewById(R.id.deleteLec);

        }
    }
}
