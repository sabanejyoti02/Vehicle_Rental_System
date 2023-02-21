package com.sparklab.car_rental.View_Car_Details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sparklab.car_rental.Payment;
import com.sparklab.car_rental.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class View_Car_Details extends AppCompatActivity {
Button from,to,Days,booknow;
TextView carname,brand,fuel,txmodel,txvno,price,total_price;
ArrayList<Car_Details_Model> map;
Car_Model model;
ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__car__details);
        from=(Button) findViewById(R.id.btn_from);
        to=(Button) findViewById(R.id.btn_to);
        Days=(Button) findViewById(R.id.btn_days);
        carname=(TextView) findViewById(R.id.txt_cname);
        brand=(TextView) findViewById(R.id.txt_bname);
        fuel=(TextView) findViewById(R.id.txt_fuel);
        txmodel=(TextView) findViewById(R.id.txt_cdmodel);
        txvno=(TextView) findViewById(R.id.txt_vehical_no);
        price=(TextView) findViewById(R.id.txt_price);
        total_price=(TextView) findViewById(R.id.txt_total_price);
        image=(ImageView) findViewById(R.id.txt_imgviewcar);
        booknow=(Button) findViewById(R.id.btn_booknow);
        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(View_Car_Details.this, Payment.class);
                intent.putExtra("car_name",carname.getText());
                intent.putExtra("brand",brand.getText());
                intent.putExtra("model",txmodel.getText());
                intent.putExtra("vno",txvno.getText());
                intent.putExtra("total",total_price.getText());
                intent.putExtra("from",from.getText());
                intent.putExtra("to",to.getText());
                startActivity(intent);
            }
        });



            Bundle bundle = getIntent().getExtras();
            String vno = bundle.getString("vno");
           Query car_query=FirebaseDatabase.getInstance().getReference("cardetails").orderByChild("vehicleno").equalTo(vno);
            car_query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data:snapshot.getChildren())
                {
                     model=data.getValue(Car_Model.class);
                }
                carname.setText(model.getName().toString());
                brand.setText(model.getBrand().toString());
                fuel.setText(model.getFuel().toString());
                txmodel.setText(model.getName().toString());
                txvno.setText(vno.toString());
                price.setText(model.getPrice().toString());
                Picasso.with(getApplicationContext())
                        .load(model.getImage())
                        .into(image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(View_Car_Details.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });



        map=new ArrayList<>();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        DatabaseReference data=ref.child("maps");
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //map.clear();
                for(DataSnapshot data:snapshot.getChildren())
                {
                    Car_Details_Model model=data.getValue(Car_Details_Model.class);
                    map.add(model);
                }
                //Toast.makeText(View_Car_Details.this, ""+map.size(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(View_Car_Details.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(View_Car_Details.this,view);
                for(int i=0;i<map.size();i++)
                {
                    menu.getMenu().add(map.get(i).getForm());
                }


                menu.show();


                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        from.setText(menuItem.toString());
                        return false;
                    }
                });
            }
        });


        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(View_Car_Details.this,view);


                for(int i=0;i<map.size();i++)
                {
                    menu.getMenu().add(map.get(i).getTo());
                }
                menu.show();


                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        to.setText(menuItem.toString());
                        return false;
                    }
                });
            }
        });



        Days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(View_Car_Details.this,view);


                for(int i=0;i<=10;i++)
                {
                    menu.getMenu().add(String.valueOf(i));

                }
                menu.show();


                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Days.setText(menuItem.toString());
                        total_price.setText(String.valueOf(Integer.parseInt(menuItem.toString())*Integer.parseInt(price.getText().toString())));

                        return false;
                    }
                });
            }
        });

    }
}