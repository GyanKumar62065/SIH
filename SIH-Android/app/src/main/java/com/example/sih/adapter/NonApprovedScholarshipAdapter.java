package com.example.sih.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.R;
import com.example.sih.model.ScholarshipStudentFormModel;

import java.util.ArrayList;
import java.util.List;

public class NonApprovedScholarshipAdapter extends RecyclerView.Adapter<NonApprovedScholarshipAdapter.MyViewHolder> {
    List<ScholarshipStudentFormModel> list = new ArrayList<>();
    private ItemClickListener clickListener;

    public NonApprovedScholarshipAdapter(List<ScholarshipStudentFormModel> list) {
        this.list = list;
    }

    public NonApprovedScholarshipAdapter(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setList(List<ScholarshipStudentFormModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.non_approved_forms_recylerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ScholarshipStudentFormModel scholarshipStudentFormModel = list.get(position);
        holder.scholarName.setText(list.get(position).getCollegeName().toString());
        holder.txtName.setText(list.get(position).getStudentName());
        String status;
        if (scholarshipStudentFormModel.getPending())
            status = "Panding";
        else if (scholarshipStudentFormModel.getScholared())
            status = "Approved";
        else
            status = "Rejected";
        holder.currStatus.setText(status);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(scholarshipStudentFormModel.getApplicationId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ItemClickListener {
        void onItemClick(int Id);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, scholarName, currStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            scholarName = itemView.findViewById(R.id.scholarName);
            currStatus = itemView.findViewById(R.id.currStatus);
        }
    }
}
