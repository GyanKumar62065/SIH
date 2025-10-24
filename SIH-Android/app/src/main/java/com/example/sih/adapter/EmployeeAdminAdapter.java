package com.example.sih.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.R;
import com.example.sih.model.UsersResponse;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdminAdapter extends RecyclerView.Adapter<EmployeeAdminAdapter.MyViewHolder> {


    private List<UsersResponse>data = new ArrayList<>();
    private ItemClickListener clickListener;

    public EmployeeAdminAdapter(List<UsersResponse> data ,ItemClickListener clickListener ) {
        this.data = data;
        this.clickListener = clickListener;
    }

    public EmployeeAdminAdapter(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setData(List<UsersResponse> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_admin_ist_view_demo , parent , false);
        MyViewHolder viewHolder = new  MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UsersResponse usersResponse = data.get(position);
        holder.employeeName.setText(String.valueOf(usersResponse.getName()));
        holder.employeeRole.setText(usersResponse.getRole());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickListener.onItemClick(usersResponse.getEmailId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {

        LinearLayout llRow;
        TextView employeeName , employeeRole;
//        String emailId;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            llRow = itemView.findViewById(R.id.llRow);
            employeeName = itemView.findViewById(R.id.employeeName);
            employeeRole = itemView.findViewById(R.id.employeeRole);
        }
    }

    public interface ItemClickListener{
        public void onItemClick(String Id);
    }
}
