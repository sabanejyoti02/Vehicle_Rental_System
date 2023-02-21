package com.sparklab.car_rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {
EditText card_no,cvv,card_holder_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        card_no=(EditText) findViewById(R.id.txt_card_no);
        card_holder_name=(EditText) findViewById(R.id.txt_card_holder_name);
        cvv=(EditText) findViewById(R.id.txt_cvv);

        Button btn_pay=(Button) findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences prefs = getSharedPreferences("Car_Rental", MODE_PRIVATE);

                DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
                DatabaseReference db=ref.child("Payment").push();


                Bundle data=getIntent().getExtras();
                Map<String,String> payment_data=new HashMap<>();


                payment_data.put("car_name",data.getString("car_name"));
                payment_data.put("brand",data.getString("brand"));
                payment_data.put("model",data.getString("model"));
                payment_data.put("vno",data.getString("vno"));
                payment_data.put("total",data.getString("total"));
                payment_data.put("from",data.getString("from"));
                payment_data.put("to",data.getString("to"));


                payment_data.put("customer_name",prefs.getString("fname",null));
                payment_data.put("contact",prefs.getString("contact",null));
                payment_data.put("email",prefs.getString("email",null));


                payment_data.put("card_no",card_no.getText().toString());
                payment_data.put("cvv",cvv.getText().toString());
                payment_data.put("card_holder_name",card_holder_name.getText().toString());

                SimpleDateFormat formate=new SimpleDateFormat("yyyy/MM/dd");
                Date date=new Date();
                String todays=formate.format(date);
                payment_data.put("bookdate",todays);
                db.setValue(payment_data);
                Toast.makeText(Payment.this, "Booking Successfull...", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Payment.this, Home.class);
                startActivity(intent);
            }
        });
    }
}