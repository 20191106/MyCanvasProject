package com.example.mycanvas2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
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

        buttons.add(new Rect(10 , 1470, 110, 1570));
        buttons.add(new Rect(120 , 1470, 220, 1570));
        buttons.add(new Rect(230 , 1470, 330, 1570));

        buttons.add(new Rect(10 , 1360, 110, 1460));
        buttons.add(new Rect(230 , 1360, 330, 1460));

        buttons.add(new Rect(10 , 1250, 110, 1350));
        buttons.add(new Rect(120 , 1250, 220, 1350));
        buttons.add(new Rect(230 , 1250, 330, 1350));

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
            for (int i = 0; i < 5000; i++) {
                for(int b = 0; b < balls.size(); b++){
                    balls.get(b).move2(getWidth(), getHeight());
                }
                
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }
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

    public void addNewBall(){
        balls.add(new Ball(500, 500, 0, 0, 50, 1));
    }

    public void addNewRandomBall(){
        Random rand = new Random();

        int rad = rand.nextInt(30) + 50;

        balls.add(new Ball(rand.nextInt(getWidth() - (rad * 2)) + rad, rand.nextInt(getHeight() - (rad * 2)) + rad, rand.nextInt(5) + 5, rand.nextInt(5) + 5, rad, rand.nextInt(3)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.WHITE);

        for (int i = 0; i < balls.size(); i++){
            switch (balls.get(i).color){
                case 0:
                    canvas.drawCircle(balls.get(i).posX, balls.get(i).posY, balls.get(i).rad, pGreen);
                    break;
                case 1:
                    canvas.drawCircle(balls.get(i).posX, balls.get(i).posY, balls.get(i).rad, pBlue);
                    break;
                case 2:
                    canvas.drawCircle(balls.get(i).posX, balls.get(i).posY, balls.get(i).rad, pYellow);
                    break;
            }
        }

        Paint p = new Paint();
        p.setColor(Color.GRAY);
        for (int i = 0; i < 8; i++){
            canvas.drawRect(buttons.get(i), p);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (buttons.get(0).contains((int)event.getX(), (int)event.getY())){

        }
        else if (buttons.get(1).contains((int)event.getX(), (int)event.getY())){

        }
        else if (buttons.get(2).contains((int)event.getX(), (int)event.getY())){

        }
        else if (buttons.get(3).contains((int)event.getX(), (int)event.getY())){

        }
        else if (buttons.get(4).contains((int)event.getX(), (int)event.getY())){

        }
        else if (buttons.get(5).contains((int)event.getX(), (int)event.getY())){

        }
        else if (buttons.get(6).contains((int)event.getX(), (int)event.getY())){

        }
        else if (buttons.get(7).contains((int)event.getX(), (int)event.getY())){

        }

        return super.onTouchEvent(event);
    }
}