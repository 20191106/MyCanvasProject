package com.example.mycanvas2;

public class DrawbleObjecct {
    int posX;
    int posY;
    int velX;
    int velY;

    int colWidth;
    int colHeight;

    public DrawbleObjecct(int posX, int posY, int velX, int velY, int colWidth, int colHeight) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        this.colWidth = colWidth;
        this.colHeight = colHeight;
    }

    synchronized public void moving(int width, int height) {
        posX += velX;
        posY += velY;
        if (posX > width - colWidth || posX < colWidth) posX -= velX;
        if (posY > height - colHeight || posY < colHeight) posY -= velY;
    }
}
