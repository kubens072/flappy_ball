package pls.s226939.flappybird_game;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;

import static android.content.Context.VIBRATOR_SERVICE;
import static java.lang.Math.sqrt;

public class Gameplay extends View {

    private int canvasWidth;
    private int canvasHeight;

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
    private int bulletX2;
    private int bulletY;
    private int bulletY2;
    private int bulletSpeed = 20;
    private Paint bulletPaint = new Paint();
    private Paint bullet2 = new Paint();
    private int sizeEnemy=20;
    //food
    private int foodX;
    private int foodY;
    private int foodSpeed = 15;
    private Paint foodPaint = new Paint();
    private int sizeFood=10;

    //level
    private Paint levelPaint = new Paint();
    private int level = 1;
    //score
    private Paint scorePaint = new Paint();
    private int score=0;

    //status check
    private boolean touch = false;

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
        int maxPlayerY = canvasHeight ;

        playerY += playerSpeed;
        if(playerY <minPlayerY) playerY = minPlayerY;
        if(playerY >maxPlayerY)
        {
            playerY = maxPlayerY;
            Toast.makeText(getContext(),"Przegrałeś",Toast.LENGTH_SHORT).show();
            try {
                Thread.sleep(2000);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
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
        if (checkCollisionFood(foodX, foodY)) {
            score += 10;
            foodX = -100;
            sizePlayer += 10;
        }

        if (foodX < 0) {
            foodX = canvasWidth + 20;
            foodY = (int) Math.floor(Math.random() * (maxPlayerY - minPlayerY)) + minPlayerY-sizePlayer;
        }
        canvas.drawCircle(foodX, foodY, sizeFood, foodPaint);


        bulletX -= bulletSpeed;
        if(checkCollisionBullet(bulletX, bulletY)){
            sendToast();
            bulletX = -100;
            life_count--;
            if(life_count==0)
            {
                //Game Over

                try {

                    Thread.sleep(3000);
                    System.exit(0);
                    Toast.makeText(getContext(),"Koniec gry! Twoj wynik to: " +score+ " punktów",Toast.LENGTH_SHORT).show();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if(bulletX <0){
            bulletX =canvasWidth+200;
            bulletY = (int) Math.floor(Math.random() * (maxPlayerY - minPlayerY)) + minPlayerY;
        }
        canvas.drawCircle(bulletX, bulletY,sizeEnemy, bulletPaint);

        if(score == 50) {
            level = 2;
            bulletX2=canvasWidth+200;
            bulletY2 = (int) Math.floor(Math.random() * (maxPlayerY - minPlayerY)) + minPlayerY;

            canvas.drawCircle(bulletX2,bulletY2,sizeEnemy,bullet2);
        }
        else if(score == 100) {
            level = 3;
        }
        canvas.drawText("Score :" + score, 20,60,scorePaint);

        canvas.drawText("Lv. " + level, canvasWidth - 60, 60, levelPaint);

        //life
        for(int i = 0; i<3; i++)
        {
            int x = (int) (460 + 45 * 1.5 * i);
            int y = 40;
            if(i<life_count)
            {
                canvas.drawCircle(x,y,30,lifeRed);
            }
            else{
                canvas.drawCircle(x,y,30,lifeWhite);
            }
        }
    }
    public void sendToast()
    {
        Toast.makeText(getContext(),"Zostales trafiony",Toast.LENGTH_SHORT).show();
    }
    public boolean checkCollisionBullet(int x, int y)
    {
        if((sizePlayer + sizeEnemy) >= sqrt(((bulletX-playerX)*(bulletX-playerX))+((bulletY-playerY)*(bulletY-playerY))))
        {
            return true;
        }
        return false;
    }
    public boolean checkCollisionFood(int x, int y)
    {
        if((sizePlayer + sizeFood) >= sqrt(((foodX-playerX)*(foodX-playerX))+((foodY-playerY)*(foodY-playerY))))
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
}
