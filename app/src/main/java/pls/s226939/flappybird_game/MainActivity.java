package pls.s226939.flappybird_game;
import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private Button exit;
    private HandlerClass activity;

    Vibrator vibrator;
    //private final static long TIMER_INTERVAL = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.button_start);
        exit = (Button) findViewById(R.id.button_exit);

        //BUTTON START
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getVibration();
                //activity = new HandlerClass(new Handler());
               // Thread thread = new Thread(activity);
               // thread.start();
                Intent intent = new Intent(MainActivity.this, HandlerClass.class);
                startActivity(intent);
            }
        });
        //BUTTON EXIT
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Dzieki ze zagrales!!!!",Toast.LENGTH_LONG).show();
                finish();
            }
        });


    }
    public void getVibration()
    {
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}