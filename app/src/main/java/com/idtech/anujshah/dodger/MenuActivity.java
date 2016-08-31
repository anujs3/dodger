package com.idtech.anujshah.dodger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MenuActivity extends AppCompatActivity
{
    ImageView gif;
    ArrayList<Integer> frames;
    int imageNumber;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thread = new Thread();
        thread.start();

        gif = (ImageView)findViewById(R.id.gif);
        frames = new ArrayList<Integer>();
        frames.add(R.drawable.frame0);
        frames.add(R.drawable.frame1);
        frames.add(R.drawable.frame2);
        frames.add(R.drawable.frame3);
        frames.add(R.drawable.frame4);
        imageNumber = 0;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        gif.setImageResource(frames.get(imageNumber));
                        imageNumber++;
                        if (imageNumber == frames.size())
                        {
                            imageNumber = 0;
                        }
                    }
                });
            }
        }, 0, 100);
    }

    public void runGame(View v)
    {
        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);
    }
}