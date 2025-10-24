package com.example.sih.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.R;
import com.example.sih.model.ScholarshipStudentFormModel;

import java.util.List;

public class CollegeStudentDataViewAdapter extends RecyclerView.Adapter<CollegeStudentDataViewAdapter.MyViewHolder> {

    List<ScholarshipStudentFormModel> list;

    public CollegeStudentDataViewAdapter(List<ScholarshipStudentFormModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_for_college_students, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getStudentName());
        holder.studentId.setText(list.get(position).getStudentId());
        if (list.get(position).getPending())
            holder.status.setText("Panding");
        else if (list.get(position).getRejected())
            holder.status.setText("Rejected");
        else
            holder.status.setText("Scholared");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, studentId, status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Name);
            studentId = itemView.findViewById(R.id.stuId);
            status = itemView.findViewById(R.id.stuStatus);
        }
    }
}
