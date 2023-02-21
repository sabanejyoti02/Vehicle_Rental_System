package com.sparklab.car_rental.Booking_Report;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
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

public class Booking_rep extends AppCompatActivity {
RecyclerView rec;
Booking_Report_Adapter adapter;
ArrayList<Booking_Report_Model> booking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_rep);
        rec=(RecyclerView) findViewById(R.id.booking_recycle);
        booking=new ArrayList<>();
        LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        rec.setLayoutManager(manager);
        SharedPreferences prefs = getSharedPreferences("Car_Rental", MODE_PRIVATE);
        String contact = prefs.getString("contact", null);
        Query query= FirebaseDatabase.getInstance().getReference("Payment").orderByChild("contact").equalTo(contact);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                booking.clear();
                for(DataSnapshot data:snapshot.getChildren())
                {
                    Booking_Report_Model model=data.getValue(Booking_Report_Model.class);
                    booking.add(model);

                }

                adapter=new Booking_Report_Adapter(booking,getApplicationContext());
                rec.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
                rec.setLayoutManager(manager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Booking_rep.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });







    }
}