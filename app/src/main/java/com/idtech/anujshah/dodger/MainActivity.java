package com.idtech.anujshah.dodger;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private GameView gv;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        gv = new GameView(this);
        setContentView(gv);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int savedScore = sharedPref.getInt("Saved HighScore", 0);
        gv.setHighScore(savedScore);

        // Music
        player = MediaPlayer.create(this, R.raw.sf);
        player.setLooping(true);
        player.setVolume(100,100);
        player.start();
        // Intent svc = new Intent(this, BackgroundSoundService.class);
        // startService(svc);
    }

    public void destroyMediaPlayer()
    {
        player.stop();
        player.release();
        player = null;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        player.pause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        player.start();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        destroyMediaPlayer();
    }
}