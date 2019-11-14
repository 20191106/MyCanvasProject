package com.example.mycanvas2;

public class Bullet extends DrawbleObjecct{
    public Bullet(int posX, int posY, int velX, int velY, int colWidth, int colHeight) {
        super(posX, posY, velX, velY, colWidth, colHeight);
    }

    public void moving(){
        posY += velY;
    }
}
