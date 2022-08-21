package com.example.sih.Employee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.R;
import com.example.sih.model.ScholarShipFormModel;

import java.util.List;

public class LetestScholershipAdapter extends RecyclerView.Adapter<LetestScholershipAdapter.MyViewHolder> {

    List<ScholarShipFormModel> arrayList;
    private ItemClickListener clickListener;

    public void setArrayList(List<ScholarShipFormModel> arrayList) {
        this.arrayList = arrayList;
    }

    public LetestScholershipAdapter(List<ScholarShipFormModel> arrayList , ItemClickListener clickListener) {
        this.arrayList = arrayList;
        this.clickListener = clickListener;
    }

    public LetestScholershipAdapter(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.letest_scholership_recycle_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LetestScholershipAdapter.MyViewHolder holder, int position) {
        ScholarShipFormModel letestScholershipForm = arrayList.get(position);
        holder.scholership_name_recyler.setText(letestScholershipForm.getScholarShipName());
        holder.scholership_amount_recyler.setText(letestScholershipForm.getAmount());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(letestScholershipForm.getScholarShipId().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView scholership_name_recyler, scholership_amount_recyler;
        LinearLayout llRow;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.llRow = itemView.findViewById(R.id.llRow);
            this.scholership_amount_recyler = itemView.findViewById(R.id.scholership_amount);
            this.scholership_name_recyler = itemView.findViewById(R.id.scholership_name);
        }
    }

    public interface ItemClickListener{
        public void onItemClick(String emailId);
    }
}
