package com.example.stopwatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int second = 0;
    private boolean running;
    private Button start,stop,reset,resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        //
       this.start = findViewById(R.id.tv_start);
       this.stop = findViewById(R.id.tv_stop);
       this.reset = findViewById(R.id.tv_reset);
       this.resume = findViewById(R.id.tv_resume);
        //
        runningTimer();
        //
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = true;
            }
        });
        //
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
            }
        });
        //
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running = false;
                second = 0;
            }
        });
        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               running = true;
            }
        });
    }

    public void runningTimer() {
         TextView timeView = (TextView) findViewById(R.id.tv_timeView);
        //
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second / 3600;
                int minutes = (second % 3600) / 60;
                int seconds = second % 60;
                String timer = String.format("%d:%02d:%02d", hours, minutes, seconds);
                  timeView.setText(timer);
                if (running) {
                    second++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}