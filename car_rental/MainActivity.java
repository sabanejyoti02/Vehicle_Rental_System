package com.sparklab.car_rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
static int count=25;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar simpleProgressBar=(ProgressBar) findViewById(R.id.progressBar); // initiate the progress bar
        simpleProgressBar.setProgress(25);
        simpleProgressBar.setMax(100); // 100 maximum value for the progress bar
        simpleProgressBar.incrementProgressBy(25);


        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (count > 100)
                {
                    startActivity(new Intent(MainActivity.this,Login.class));
                    cancel();
                }
                else {
                    simpleProgressBar.setProgress(count);
                    count = count + 25;
                }
            }

            public void onFinish() {

            }
        }.start();
    }
}