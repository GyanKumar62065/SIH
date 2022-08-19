package com.example.sih.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.R;
import com.example.sih.model.UsersResponse;

import java.util.List;

public class EmployeeAdminAdapter extends RecyclerView.Adapter<EmployeeAdminAdapter.MyViewHolder> {


    List<UsersResponse>data;
    Context context;

    public EmployeeAdminAdapter(List<UsersResponse> data) {
        this.data = data;
    }

    public EmployeeAdminAdapter() {
    }

    public EmployeeAdminAdapter(Context context,List<UsersResponse>data) {
        this.data = data;
        this.context = context;
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

//        holder.llRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(context, DataAdminIntent.class);
////                context.startActivity(intent);
//
//                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
//            }
//        });


    }
    private void loadFragment() {
    }





    @Override
    public int getItemCount() {

        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {

        LinearLayout llRow;
        TextView employeeName , employeeRole;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            llRow = itemView.findViewById(R.id.llRow);
            employeeName = itemView.findViewById(R.id.employeeName);
            employeeRole = itemView.findViewById(R.id.employeeRole);
        }


    }
}
