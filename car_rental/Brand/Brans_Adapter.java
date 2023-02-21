package com.sparklab.car_rental.Brand;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sparklab.car_rental.R;
import com.sparklab.car_rental.View_Cars.View_Cars;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Brans_Adapter extends RecyclerView.Adapter<BrandViewHolder>{

    ArrayList<Brand_Model> brands;
    Context context;

    public Brans_Adapter(ArrayList<Brand_Model> brands, Context context) {
        this.brands = brands;
        this.context = context;
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_brans,parent,false);
        return new BrandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        Picasso.with(this.context)
                .load(brands.get(position).getImage())


                .into(holder.img_brand);

        holder.brand_name.setText(brands.get(position).getBrand());

        holder.card. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context.getApplicationContext(),View_Cars.class);
                intent.putExtra("brand",brands.get(position).getBrand());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }
}

class BrandViewHolder extends RecyclerView.ViewHolder
{
ImageView img_brand;
TextView brand_name;
CardView card;

    public BrandViewHolder(@NonNull View itemView) {
        super(itemView);
        img_brand=(ImageView) itemView.findViewById(R.id.img_car);
        brand_name=(TextView) itemView.findViewById(R.id.txt_cbrand);
        card=(CardView) itemView.findViewById(R.id.brand_card);



    }
}
