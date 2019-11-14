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
        can.addNewEnemies();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    protected void onDestroy() {
        can.isDone = true;
        super.onDestroy();
    }
}
