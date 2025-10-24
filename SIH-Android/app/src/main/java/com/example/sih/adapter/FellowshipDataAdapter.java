package com.example.sih.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.R;
import com.example.sih.model.FellowshipModel;

import java.util.ArrayList;
import java.util.List;

public class FellowshipDataAdapter extends RecyclerView.Adapter<FellowshipDataAdapter.MyViewHolder> {

    List<FellowshipModel>list = new ArrayList<>();
    ItemClickListener itemClickListener;

    public void setList(List<FellowshipModel> list) {
        this.list = list;
    }

    public FellowshipDataAdapter(List<FellowshipModel> list) {
        this.list = list;
    }

    public FellowshipDataAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_for_college_students , parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        FellowshipModel fellowshipModel = list.get(position);
        holder.clgName.setText(fellowshipModel.getCollegeName());
        holder.clgId.setText(fellowshipModel.getCollegeId());
        if (list.get(position).getPending())
            holder.status.setText("Panding");
        else if (list.get(position).getRejected())
            holder.status.setText("Rejected");
        else
            holder.status.setText("Approved");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(fellowshipModel.getApplicationFormId());
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView clgName , clgId , status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            clgName = itemView.findViewById(R.id.Name);
            clgId = itemView.findViewById(R.id.stuId);
            status = itemView.findViewById(R.id.stuStatus);
        }
    }

    public interface ItemClickListener {
        void onItemClick(int Id);
    }
}
