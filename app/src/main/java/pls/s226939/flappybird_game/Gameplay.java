package pls.s226939.flappybird_game;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import static android.content.Context.VIBRATOR_SERVICE;

public class Gameplay extends View{

    Vibration v;
    Vibrator vibrator;
    private int canvasWidth;
    private int canvasHeight;
    private int change_level_speed = 30;

    //player
    private Paint birdMove = new Paint();
    private int playerX =10;
    private int playerY=500;
    private int playerSpeed;
    private int sizePlayer=30;
    //life
    private Paint lifeRed = new Paint();
    private  Paint lifeWhite = new Paint();
    private int life_count=3;
    //bullet
    private int bulletX;
    private int bulletY;
    private int bulletSpeed = 20;
    private Paint bulletPaint = new Paint();
    //food
    private int foodX;
    private int foodY;
    private int foodSpeed = 15;
    private Paint foodPaint = new Paint();

    //level
    private Paint levelPaint = new Paint();
    private int level = 1;
    //score
    private Paint scorePaint = new Paint();
    private int score=0;

    //status check
    private boolean touch = false;


    public int getValue()
    {
        return change_level_speed;
    }

    public Gameplay(Context context) {
        super(context);

        birdMove.setColor(Color.GREEN);
        birdMove.setAntiAlias(false);

        foodPaint.setColor(Color.BLUE);
        foodPaint.setAntiAlias(false);

        bulletPaint.setColor(Color.BLACK);
        bulletPaint.setAntiAlias(false);

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(32);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        levelPaint.setColor(Color.DKGRAY);
        levelPaint.setTextSize(32);
        levelPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelPaint.setTextAlign(Paint.Align.CENTER);
        levelPaint.setAntiAlias(true);

        lifeRed.setColor(Color.RED);
        lifeRed.setAntiAlias(true);

        lifeWhite.setColor(Color.WHITE);
        lifeWhite.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvasWidth=canvas.getWidth();
        canvasHeight=canvas.getHeight()+sizePlayer;

        int minPlayerY = sizePlayer;
        int maxPlayerY = canvasHeight - sizePlayer;

        playerY += playerSpeed;
        if(playerY <minPlayerY) playerY = minPlayerY;
        if(playerY >maxPlayerY) playerY = maxPlayerY;
        playerSpeed +=2;

        if(touch)
        {
            canvas.drawCircle(playerX + sizePlayer, playerY, sizePlayer,birdMove);
            touch =false;
        }
        else
        {
            canvas.drawCircle(playerX + sizePlayer, playerY, sizePlayer,birdMove);
        }


        foodX -= foodSpeed;
        if (checkCollision(foodX, foodY)) {
            score += 10;
            foodX = -100;
            sizePlayer += 10;
            //v.getVibration();
        }
        else if(score == 50) {
            level = 2;
            change_level_speed=change_level_speed-20;
        }
        else if(score == 100) {
            level = 3;
        }
        if (foodX < 0) {
            foodX = canvasWidth + 20;
            foodY = (int) Math.floor(Math.random() * (maxPlayerY - minPlayerY)) + minPlayerY;
        }
        canvas.drawCircle(foodX, foodY, 10, foodPaint);


        bulletX -= bulletSpeed;
        if(checkCollision(bulletX, bulletY)){
            getVibration();
            sendToast();
            bulletX = -100;
            life_count--;
            if(life_count==0)
            {
                //Game Over
                Toast.makeText(getContext(),"Koniec gry!",Toast.LENGTH_SHORT).show();

            }
        }
        if(bulletX <0){
            bulletX =canvasWidth+200;
            bulletY = (int) Math.floor(Math.random() * (maxPlayerY - minPlayerY)) + minPlayerY;
        }
        canvas.drawCircle(bulletX, bulletY,15, bulletPaint);

        canvas.drawText("Score :" + score, 20,60,scorePaint);

        canvas.drawText("Lv. " + level, canvasWidth - 60, 60, levelPaint);

        //life
        for(int i = 0; i<3; i++)
        {
            int x = (int) (460 + 20 * 1.5 * i);
            int y = 30;
            if(i<life_count)
            {
                canvas.drawCircle(x,y,15,lifeRed);
            }
            else{
                canvas.drawCircle(x,y,15,lifeWhite);
            }
        }
    }

    public void sendToast()
    {
        Toast.makeText(getContext(),"Zostales trafiony",Toast.LENGTH_SHORT).show();
    }
    public boolean checkCollision(int x, int y)
    {
        if(playerX <x && x<(playerX + sizePlayer) && playerY < y && y < (playerY + sizePlayer))
        {
            return true;
        }
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            touch =true;
            playerSpeed = -30;
        }
        return true;
    }
    public void getVibration()
    {
        vibrator = (Vibrator) v.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
