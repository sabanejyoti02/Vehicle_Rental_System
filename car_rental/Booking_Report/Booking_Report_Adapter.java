package com.sparklab.car_rental.Booking_Report;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sparklab.car_rental.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Booking_Report_Adapter extends RecyclerView.Adapter<Booking_Report_ViewHolder>{
    ArrayList<Booking_Report_Model> model;
    Context context;

    public Booking_Report_Adapter(ArrayList<Booking_Report_Model> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public Booking_Report_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_bookign_report,parent,false);
        return new Booking_Report_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Booking_Report_ViewHolder holder, int position) {
        holder.brand_name.setText(model.get(position).getBrand());
        holder.date.setText(model.get(position).getBookdate());
        holder.car_name.setText(model.get(position).getCar_name());
        holder.vno.setText(model.get(position).getVno());
        holder.from.setText(model.get(position).getFrom());
        holder.to.setText(model.get(position).getTo());
        holder.total.setText(model.get(position).getTotal());
        holder.model.setText(model.get(position).getModel());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }
}


 class Booking_Report_ViewHolder extends RecyclerView.ViewHolder {
    TextView brand_name,date,car_name,vno,from,to,total,model;
     public Booking_Report_ViewHolder(@NonNull View itemView) {
         super(itemView);

        brand_name=(TextView) itemView.findViewById(R.id.txt_rbname);
        date=(TextView) itemView.findViewById(R.id.txt_rdate);
        car_name=(TextView) itemView.findViewById(R.id.txt_rcarname);
         vno=(TextView) itemView.findViewById(R.id.txt_rvno);
         from=(TextView) itemView.findViewById(R.id.txt_rfrom);
         to=(TextView) itemView.findViewById(R.id.txt_rto);
         total=(TextView) itemView.findViewById(R.id.txt_rtotal);
         model=(TextView) itemView.findViewById(R.id.txt_rmodel);

     }
 }

