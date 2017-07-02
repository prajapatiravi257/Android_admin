package com.planet.noobs.testproject.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.planet.noobs.testproject.Data.DBHelper;
import com.planet.noobs.testproject.Model.User;
import com.planet.noobs.testproject.R;

import java.util.List;

/**
 * Created by rio on 22/6/17.
 */

public class AdminListAdapter extends UltimateViewAdapter<AdminListAdapter.AdminHolder> {
    private List<User> madmin;
    private User user;
    private DBHelper dbHelper;

    public AdminListAdapter(List<User> madmin) {
        this.madmin = madmin;
        user = new User();
    }

    @Override
    public AdminHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_dept_list_item, parent, false);
        AdminHolder ah = new AdminHolder(inflatedView);
        return ah;
    }

    @Override
    public AdminHolder newFooterHolder(View view) {
        return null;
    }

    @Override
    public AdminHolder newHeaderHolder(View view) {
        return null;
    }

    @Override
    public AdminHolder onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindViewHolder(final AdminHolder holder, final int position) {
        holder.textViewUserEmail.setText(madmin.get(position).getEmail());
        holder.buttonDenyHod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new DBHelper(v.getContext());
                user.setEmail(holder.textViewUserEmail.getText().toString());

                    dbHelper.deleteDept(user);
                    madmin.remove(position);
                    notifyItemRemoved(position);
                    Toast.makeText(v.getContext(),"User approved succesfully",Toast.LENGTH_SHORT).show();
            }
        });
        holder.buttonApproveHod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new DBHelper(v.getContext());
                user.setEmail(holder.textViewUserEmail.getText().toString());

                dbHelper.approveDept(user);
                madmin.remove(position);
                notifyItemRemoved(position);
                Toast.makeText(v.getContext(),"User approved succesfully",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getAdapterItemCount() {
        return madmin.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return 0;
    }

    public class AdminHolder extends RecyclerView.ViewHolder {
        private ImageButton buttonApproveHod;
        private ImageButton buttonDenyHod;
        private TextView textViewUserEmail;

        public AdminHolder(View itemView) {
            super(itemView);
            buttonApproveHod = (ImageButton) itemView.findViewById(R.id.allow_dept);
            buttonDenyHod = (ImageButton) itemView.findViewById(R.id.deny_dept);
            textViewUserEmail = (TextView) itemView.findViewById(R.id.hod_name);
        }
    }

}
