package com.sparklab.car_rental;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sparklab.car_rental.Booking_Report.Booking_Report_Adapter;
import com.sparklab.car_rental.Booking_Report.Booking_Report_Model;
import com.sparklab.car_rental.Booking_Report.Booking_rep;
import com.sparklab.car_rental.Brand.Brands;
import com.sparklab.car_rental.Login_Package.Login_Model;

public class Login extends AppCompatActivity {
EditText username,password;
Login_Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText) findViewById(R.id.ltxt_username);
        password=(EditText) findViewById(R.id.txt_lpass);


        TextView signup=(TextView) findViewById(R.id.txt_signp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Login.this,Signup.class));
            }
        });

        Button btn_Login=(Button) findViewById(R.id.btn_login);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                SharedPreferences prefs = getSharedPreferences("Car_Rental", MODE_PRIVATE);
                String username1 = prefs.getString("username", null);//"No name defined" is the default value.
                String password1 = prefs.getString("password", null); //0 is the default value.

                if((username1.equals(username.getText().toString())) && (password1.equals(password.getText().toString())))
                {
                   Intent intent=new Intent(Login.this, Home.class);
                   startActivity(intent);
                }
                else
                {
                    Toast.makeText(Login.this, "Login Faild", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}