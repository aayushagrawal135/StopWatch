package com.example.aayushagrawal.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity1 extends AppCompatActivity
{
    TextView time;
    Button start;
    Button stop;
    Button reset;

    int seconds;
    boolean running = false;
    boolean wasrunning = false;
    int wassec;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        if(savedInstanceState!= null)
        {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }

        Runtimer();

        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);
        reset = (Button)findViewById(R.id.reset);

        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                running = true;
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                seconds = 0;
            }
        });
    }

    public void Runtimer()
    {
        time = (TextView)findViewById(R.id.time);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;

                String Time = String.format("%d : %d : %d", hours, minutes, sec);
                time.setText(Time);

                if (running == true) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        running = wasrunning;
        seconds = wassec;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putInt("seconds", seconds);
        outState.putBoolean("running", running);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        wasrunning = running;
        wassec = seconds;
        running = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
