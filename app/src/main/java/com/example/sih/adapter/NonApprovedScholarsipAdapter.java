package com.example.sih.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.R;
import com.example.sih.model.ScholarshipStudentFormModel;

import java.util.ArrayList;
import java.util.List;

public class NonApprovedScholarsipAdapter extends RecyclerView.Adapter<NonApprovedScholarsipAdapter.MyViewHolder> {

    List<ScholarshipStudentFormModel> data = new ArrayList<>();
    ItemClickListener clickListener;


    public NonApprovedScholarsipAdapter(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.non_approved_forms_recylerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ScholarshipStudentFormModel scholarshipStudentFormModel = data.get(position);
        holder.txtName.setText(scholarshipStudentFormModel.getStudentName());
        holder.scholarName.setText(scholarshipStudentFormModel.getCollegeName().toString());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ScholarshipStudentFormModel> data) {
        this.data = data;
    }

    public interface ItemClickListener {
        void onItemClick(String emailId);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llrow;
        TextView txtName, scholarName, currStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            llrow = itemView.findViewById(R.id.llrow);
            txtName = itemView.findViewById(R.id.txtName);
            scholarName = itemView.findViewById(R.id.scholarName);
            currStatus = itemView.findViewById(R.id.currStatus);
        }
    }
}
