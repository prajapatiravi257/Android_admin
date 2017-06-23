package com.planet.noobs.testproject.Adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.planet.noobs.testproject.Model.Lectures;
import com.planet.noobs.testproject.R;

import java.util.List;


public class LecListAdapter extends RecyclerView.Adapter<LecListAdapter.LecViewHolder> {

    private List<Lectures> mlec;

    public LecListAdapter(List<Lectures> mlec) {
        this.mlec = mlec;
    }


    @Override
    public LecViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_lec_item, parent, false);
        return new LecViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LecViewHolder holder, int position) {
        holder.textViewLecTime.setText(mlec.get(position).getLecDateTime());
        holder.textViewSub.setText(mlec.get(position).getLecTitle());
    }

    @Override
    public int getItemCount() {
        Log.v(LecListAdapter.class.getSimpleName(), String.valueOf(mlec.size()));
        return mlec.size();
    }

    public class LecViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView textViewSub;
        private AppCompatTextView textViewLecTime;

        //private TextView emptyView;
        public LecViewHolder(View itemView) {
            super(itemView);
            //emptyView = (TextView) itemView.findViewById(R.id.emptyView);
            textViewSub = (AppCompatTextView) itemView.findViewById(R.id.lec_title);
            textViewLecTime = (AppCompatTextView) itemView.findViewById(R.id.lec_time);
        }
    }
}
