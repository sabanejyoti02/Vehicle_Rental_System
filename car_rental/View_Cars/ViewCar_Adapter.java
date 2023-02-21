package com.sparklab.car_rental.View_Cars;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sparklab.car_rental.R;
import com.sparklab.car_rental.View_Car_Details.View_Car_Details;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewCar_Adapter extends RecyclerView.Adapter<CarViewHolder>{
ArrayList<Car_Model> cars;
Context context;

    public ViewCar_Adapter(ArrayList<Car_Model> cars, Context context) {
        this.cars = cars;
        this.context = context;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_car,parent,false);

        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Picasso.with(this.context)
                .load(cars.get(position).getImage())


                .into(holder.car_image);
        holder.bname.setText(cars.get(position).getBrand());
        holder.cname.setText(cars.get(position).getName());
        holder.models.setText(cars.get(position).getModel());
        holder.btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context.getApplicationContext(), View_Car_Details.class);
                intent.putExtra("vno",cars.get(position).getVehicleno());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }
}

class CarViewHolder extends RecyclerView.ViewHolder
{
    ImageView car_image;
    TextView bname,cname,models;
    Button btn_view;
    public CarViewHolder(@NonNull View itemView) {
        super(itemView);
        car_image=itemView.findViewById(R.id.img_car);
        bname=itemView.findViewById(R.id.txt_cbrand);
        cname=itemView.findViewById(R.id.txt_carname);
        models=itemView.findViewById(R.id.txt_model);
        btn_view=itemView.findViewById(R.id.btn_viewmore);
    }
}
