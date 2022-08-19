package com.example.sih.Employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sih.R;

import java.util.ArrayList;

public class LetestScholershipAdapter extends RecyclerView.Adapter<LetestScholershipAdapter.MyViewHolder> {


    ArrayList<LetestScholershipForm> arrayList;
    Context context;

    public LetestScholershipAdapter() {
    }

    public LetestScholershipAdapter(Context context, ArrayList<LetestScholershipForm> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public LetestScholershipAdapter(ArrayList<LetestScholershipForm> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.letest_scholership_recycle_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        
        TextView details = view.findViewById(R.id.details);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Changeing here to showing the scholership full details
                Toast.makeText(view.getContext(), "Showing", Toast.LENGTH_SHORT).show();
            }
        });
        
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LetestScholershipAdapter.MyViewHolder holder, int position) {
        LetestScholershipForm letestScholershipForm = (LetestScholershipForm) arrayList.get(position);
        holder.scholership_name_recyler.setText(letestScholershipForm.name);
        holder.scholership_amount_recyler.setText(letestScholershipForm.amount);
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
            this.scholership_amount_recyler = itemView.findViewById(R.id.scholership_name);
            this.scholership_name_recyler = itemView.findViewById(R.id.scholership_amount);
        }
    }
}
