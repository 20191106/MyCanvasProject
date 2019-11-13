package com.example.mycanvas2;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    MyCanvas can;
    Button button;
    ArrayList<Rect> buttons = new ArrayList<>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn);
        button.setOnClickListener(this);

        can = findViewById(R.id.myCanvas);
    }

    @Override
    public void onClick(View v) {
        can.addNewBall();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(10 < event.getX() && event.getX() < 110){
            if(1250 < event.getY() && event.getY() < 1350){
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    can.getButton(0, true);
                    can.getButton(2, true);
                }
                else {
                    can.getButton(0, false);
                    can.getButton(2, false);
                }
            }
            else if(1360 < event.getY() && event.getY() < 1460){
                if (event.getAction() == MotionEvent.ACTION_DOWN) can.getButton(2, true);
                else can.getButton(2, false);
            }
            else if(1470 < event.getY() && event.getY() < 1570){
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    can.getButton(1, true);
                    can.getButton(2, true);
                }
                else {
                    can.getButton(1, false);
                    can.getButton(2, false);
                }
            }
        }
        else if(120 < event.getX() && event.getX() < 220){
            if(1250 < event.getY() && event.getY() < 1350){
                if (event.getAction() == MotionEvent.ACTION_DOWN) can.getButton(0, true);
                else can.getButton(0, false);
            }
            else if(1360 < event.getY() && event.getY() < 1460){

            }
            else if(1470 < event.getY() && event.getY() < 1570){
                if (event.getAction() == MotionEvent.ACTION_DOWN) can.getButton(1, true);
                else can.getButton(1, false);
            }
        }
        else if(230 < event.getX() && event.getX() < 330){
            if(1250 < event.getY() && event.getY() < 1350){
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    can.getButton(0, true);
                    can.getButton(3, true);
                }
                else {
                    can.getButton(0, false);
                    can.getButton(3, false);
                }
            }
            else if(1360 < event.getY() && event.getY() < 1460){
                if (event.getAction() == MotionEvent.ACTION_DOWN) can.getButton(3, true);
                else can.getButton(3, false);
            }
            else if(1470 < event.getY() && event.getY() < 1570){
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    can.getButton(1, true);
                    can.getButton(3, true);
                }
                else {
                    can.getButton(1, false);
                    can.getButton(3, false);
                }
            }
        }
        
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
    
}
