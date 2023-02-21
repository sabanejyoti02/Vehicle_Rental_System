package com.sparklab.car_rental.Brand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sparklab.car_rental.R;

import java.util.ArrayList;

public class Brands extends AppCompatActivity {
RecyclerView rec;
Brans_Adapter adapter;
ArrayList<Brand_Model> brnds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);
        rec=(RecyclerView) findViewById(R.id.car_recycle);
        GridLayoutManager gridmanager=new GridLayoutManager(getApplicationContext(),2);
        rec.setLayoutManager(gridmanager);
        brnds=new ArrayList<>();
        DatabaseReference brands= FirebaseDatabase.getInstance().getReference();
        DatabaseReference data1=brands.child("brands");
        data1.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot data : snapshot.getChildren())
                {
                   Brand_Model brands=data.getValue(Brand_Model.class);
                   brnds.add(brands);


                }
                Toast.makeText(Brands.this, ""+brnds.size(), Toast.LENGTH_SHORT).show();
                adapter=new Brans_Adapter(brnds, getApplicationContext());
                rec.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}