package com.planet.noobs.testproject.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.planet.noobs.testproject.Data.DBHelper;
import com.planet.noobs.testproject.Model.User;
import com.planet.noobs.testproject.R;

import java.util.List;

/**
 * Created by rio on 22/6/17.
 */

public class DeptListAdapter extends UltimateViewAdapter<DeptListAdapter.DeptHolder> {
    private static final String LOG_TAG = DeptListAdapter.class.getSimpleName();
    private DBHelper dbHelper;
    private List<User> mdept;
    private User user;

    public DeptListAdapter(List<User> mdept) {
        this.mdept = mdept;
    }

    @Override
    public DeptHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public DeptHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public DeptHolder onCreateViewHolder(ViewGroup parent) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dept_request_listitem, parent, false);
        return new DeptHolder(inflatedView);
    }


    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(DeptHolder holder, int position) {
        holder.textViewUserType.setText(mdept.get(position).getUserType());
        holder.textViewUserEmail.setText(mdept.get(position).getEmail());
        user = new User();

        holder.buttonAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(LOG_TAG, "Button allow clicked!");
            }
        });
        holder.buttonDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }


    @Override
    public int getAdapterItemCount() {
        return mdept.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    public class DeptHolder extends RecyclerView.ViewHolder {
        private TextView textViewUserType;
        private ImageButton buttonAllow;
        private ImageButton buttonDeny;
        private TextView textViewUserEmail;

        public DeptHolder(View itemView) {
            super(itemView);
            textViewUserEmail = (TextView) itemView.findViewById(R.id.userEmail);
            textViewUserType = (TextView) itemView.findViewById(R.id.usertype);
            buttonAllow = (ImageButton) itemView.findViewById(R.id.allow_user);
            buttonDeny = (ImageButton) itemView.findViewById(R.id.deny_user);
        }
    }
}
