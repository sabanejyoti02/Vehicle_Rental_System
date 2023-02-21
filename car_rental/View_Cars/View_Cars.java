package com.sparklab.car_rental.View_Cars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sparklab.car_rental.R;

import java.util.ArrayList;

public class View_Cars extends AppCompatActivity {
RecyclerView rec;
ArrayList<Car_Model> car_model;

ViewCar_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__cars);
        rec=(RecyclerView) findViewById(R.id.car_recycle);
        car_model=new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        String brandvalue = bundle.getString("brand");




        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        rec.setLayoutManager(manager);
        Query query= FirebaseDatabase.getInstance().getReference("cardetails").orderByChild("brand").equalTo(brandvalue);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                car_model.clear();
                for(DataSnapshot data:snapshot.getChildren())
                {
                    Car_Model carm=data.getValue(Car_Model.class);
                    car_model.add(carm);
                }
                adapter=new ViewCar_Adapter(car_model,getApplicationContext());
                rec.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(View_Cars.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}