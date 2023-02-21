package com.sparklab.car_rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sparklab.car_rental.Booking_Report.Booking_rep;
import com.sparklab.car_rental.Brand.Brands;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
  Button btn_book=(Button) findViewById(R.id.btn_bookcar);
  btn_book.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          Intent intent=new Intent(Home.this,Brands.class);
          startActivity(intent);
      }
  });


  Button btn_mybookings=(Button) findViewById(R.id.btn_mybooking);
  btn_mybookings.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          Intent intent=new Intent(Home.this,Booking_rep.class);
          startActivity(intent);
      }
  });



        Button exit=(Button) findViewById(R.id.btn_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });
    }
}