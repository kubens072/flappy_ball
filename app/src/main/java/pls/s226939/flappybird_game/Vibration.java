package pls.s226939.flappybird_game;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;

public class Vibration extends AppCompatActivity {
    Vibrator vibrator;
    public void getVibration()
    {
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
