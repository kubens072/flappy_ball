package pls.s226939.flappybird_game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class CheckLevel extends AppCompatActivity {
    Button button;
    CheckBox easy,medium,hard;
    private int Time_Interval;
    private int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_lvl);

        button = (Button) findViewById(R.id.button);
        easy = (CheckBox) findViewById(R.id.checkBox);
        medium = (CheckBox) findViewById(R.id.checkBox2);
        hard = (CheckBox) findViewById(R.id.checkBox3);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(easy.isChecked())
                {
                    x=1;
                    //Time_Interval = 30;
                }
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(medium.isChecked())
                {
                    x = 2;
                    //Time_Interval=25;
                }
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hard.isChecked())
                {
                    x = 3;
                    Time_Interval = 20;
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckLevel.this, HandlerClass.class);
                intent.putExtra("interval",x);
                startActivity(intent);
            }
        });
    }
    public int getInterval()
    {
        return Time_Interval;
    }
}
