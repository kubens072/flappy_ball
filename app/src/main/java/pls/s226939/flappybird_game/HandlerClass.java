package pls.s226939.flappybird_game;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class HandlerClass extends AppCompatActivity{

    private Gameplay gameView;
    private Handler handler = new Handler();

    private final static long TIMER_INTERVAL = 20;


    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        },0,TIMER_INTERVAL);
        /*try {

            Thread.sleep(100);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Thread thread = new Thread((Runnable) gameView);
                    thread.start();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }





    /*        gameView = new Gameplay(this);
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
        },0,gameView.getValue());*/
}
