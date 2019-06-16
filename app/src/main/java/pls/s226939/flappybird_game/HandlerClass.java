package pls.s226939.flappybird_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class HandlerClass extends AppCompatActivity{

    private Gameplay gameView;
    private Handler handler = new Handler();
    private int TimeInterval;

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int Interval = getIntent().getIntExtra("interval",0);
        if(Interval==1)
        {
            TimeInterval=30;
        }
        else if(Interval==2)
        {
            TimeInterval=25;
        }
        else if(Interval==3)
        {
            TimeInterval=20;
        }

        gameView = new Gameplay(this);
        setContentView(gameView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        },0,TimeInterval);
    }
}
