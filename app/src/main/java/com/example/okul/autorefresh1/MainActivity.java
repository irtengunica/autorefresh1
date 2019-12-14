package com.example.okul.autorefresh1;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private int saat;
    private int dakika;
    private int saniye;
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ProgressBar pr_buton=(ProgressBar) findViewById(R.id.circularProgressbar);
        pr_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        pr_buton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN ) {
                    progressStatus+=1;
                    pr_buton.setProgress(progressStatus);
                    if(progressStatus>100){progressStatus=0;}

                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){

                }

                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        this.mHandler = new Handler();

        this.mHandler.postDelayed(m_Runnable,5000);
    }

    private Handler mHandler;
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
            TextView mesaj1=(TextView) findViewById(R.id.saat);
            Toast.makeText(MainActivity.this,"çalýþtým",Toast.LENGTH_SHORT).show();
            final Calendar c = Calendar.getInstance();
            saat = c.get(Calendar.HOUR_OF_DAY);
            dakika = c.get(Calendar.MINUTE);
            saniye=c.get(Calendar.SECOND);
            mesaj1.setText(new StringBuilder().append(padding_str(saat)).append(":").append(padding_str(dakika)).append(":").append(padding_str(saniye)));


            MainActivity.this.mHandler.postDelayed(m_Runnable,20000);
        }

    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    private static String padding_str(int c)
    {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }
}
