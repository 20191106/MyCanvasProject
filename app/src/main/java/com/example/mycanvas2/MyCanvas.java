package com.example.mycanvas2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class MyCanvas extends View {
    Paint pGreen, pBlue, pYellow;
    ArrayList<Ball> balls = new ArrayList<>();
    ArrayList<Rect> buttons = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();
    ArrayList<Enemy> enemies = new ArrayList<>();

    boolean isDone = false;

    Bitmap planeBm;

    // xml 호출 생성자
    public MyCanvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        pGreen = new Paint();
        pBlue = new Paint();
        pYellow = new Paint();

        pGreen.setColor(Color.GREEN);
        pBlue.setColor(Color.BLUE);
        pYellow.setColor(Color.YELLOW);

        pGreen.setAntiAlias(true);
        pBlue.setAntiAlias(true);
        pYellow.setAntiAlias(true);

        planeBm = BitmapFactory.decodeResource(getResources(), R.drawable.my2);
        planeBm = Bitmap.createScaledBitmap(planeBm, 100, 150, true);

        addNewBall();
        
        // 5 6 7
        // 3   4
        // 0 1 2
        buttons.add(new Rect(10 , 1470, 110, 1570));
        buttons.add(new Rect(120 , 1470, 220, 1570));
        buttons.add(new Rect(230 , 1470, 330, 1570));

        buttons.add(new Rect(10 , 1360, 110, 1460));
        buttons.add(new Rect(230 , 1360, 330, 1460));

        buttons.add(new Rect(10 , 1250, 110, 1350));
        buttons.add(new Rect(120 , 1250, 220, 1350));
        buttons.add(new Rect(230 , 1250, 330, 1350));

        buttons.add(new Rect(500, 1360, 800, 1660));

        MyThread th = new MyThread();
        th.start();
    }

    public MyCanvas(Context context) {
        super(context);
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (!isDone) {
                for(int b = 0; b < balls.size(); b++){
                    balls.get(b).move2(getWidth(), getHeight());
                }
                for(int b = 0; b < bullets.size(); b++){
                    bullets.get(b).moving();
                    if (bullets.get(b).posY < -bullets.get(b).colHeight){
                        bullets.remove(b);
                        b--;
                    }
                    else if (attackEnemy(b)){
                        bullets.remove(b);
                        b--;
                    }
                }
                for(int e = 0; e < enemies.size(); e++){
                    enemies.get(e).movingPatternHorizontal(getWidth());
                }
                
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }

            planeBm.recycle();
            planeBm = null;
        }
    }

    public boolean attackEnemy(int b){
        boolean isCol = false;
        for (int e = 0; e < enemies.size(); e++){
            if(checkCol(bullets.get(b), enemies.get(e))){
                enemies.get(e).life--;
                if(enemies.get(e).life <= 0) enemies.remove(e);
                e--;
                isCol = true;
            }
            else{
                isCol = false;
            }
        }
        return isCol;
    }

    public boolean checkCol(DrawbleObjecct o1, DrawbleObjecct o2){
        boolean isCol;
        Rect r1 = new Rect(o1.posX, o1.posY, o1.posX + o1.colWidth, o1.posY + o1.colHeight);
        Rect r2 = new Rect(o2.posX, o2.posY, o2.posX + o2.colWidth, o2.posY + o2.colHeight);
        if(r1.intersect(r2))isCol = true;
        else isCol = false;
        return isCol;
    }

    public void getButton(int dir, boolean isDown){
        for (int i = 0; i < balls.size(); i++){
            balls.get(i).getButton(dir, isDown);
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            invalidate();
        }
    };

    public void fire(Ball b){
        bullets.add(new Bullet(b.posX + 50, b.posY, 0, -10, 10, 50));
    }

    public void addNewEnemies(){
        enemies.add(new Enemy(500, 200, 10, 0, 100, 50));
    }

    public void addNewBall(){
        balls.add(new Ball(500, 500, 0, 0, 50, 1));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.WHITE);

        for (int i = 0; i < bullets.size(); i++){
            canvas.drawRect(bullets.get(i).posX, bullets.get(i).posY, bullets.get(i).posX + 10, bullets.get(i).posY + 50, pGreen);
        }

        for (int i = 0; i < enemies.size(); i++){
            canvas.drawRect(enemies.get(i).posX, enemies.get(i).posY, enemies.get(i).posX + enemies.get(i).colWidth, enemies.get(i).posY + enemies.get(i).colHeight, pBlue);
        }

        Paint p = new Paint();
        p.setColor(Color.GRAY);

        canvas.drawBitmap(planeBm, balls.get(0).posX, balls.get(0).posY, p);

        for (int i = 0; i < buttons.size(); i++){
            canvas.drawRect(buttons.get(i), p);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (buttons.get(0).contains((int)event.getX(), (int)event.getY())){
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                getButton(1, true);
                getButton(2, true);
            }
        }
        else if (buttons.get(1).contains((int)event.getX(), (int)event.getY())){
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) getButton(1, true);
        }
        else if (buttons.get(2).contains((int)event.getX(), (int)event.getY())){
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                getButton(1, true);
                getButton(3, true);
            }
        }
        else if (buttons.get(3).contains((int)event.getX(), (int)event.getY())){
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) getButton(2, true);
        }
        else if (buttons.get(4).contains((int)event.getX(), (int)event.getY())){
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) getButton(3, true);
        }
        else if (buttons.get(5).contains((int)event.getX(), (int)event.getY())){
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                getButton(0, true);
                getButton(2, true);
            }
        }
        else if (buttons.get(6).contains((int)event.getX(), (int)event.getY())){
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) getButton(0, true);
        }
        else if (buttons.get(7).contains((int)event.getX(), (int)event.getY())){
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                getButton(0, true);
                getButton(3, true);
            }
        }
        else if (buttons.get(8).contains((int)event.getX(), (int)event.getY())){
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                fire(balls.get(0));
            }
        }

        if (event.getAction() == MotionEvent.ACTION_UP){
            getButton(0, false);
            getButton(1, false);
            getButton(2, false);
            getButton(3, false);
        }

        return true;
    }
}