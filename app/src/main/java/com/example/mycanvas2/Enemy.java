package com.example.mycanvas2;

public class Enemy extends DrawbleObjecct {
    int life;

    public Enemy(int posX, int posY, int velX, int velY, int colWidth, int colHeight) {
        super(posX, posY, velX, velY, colWidth, colHeight);
        life = 1;
    }

    public void movingPatternHorizontal(int width){
        if (posX > width - colWidth || posX < colWidth) velX *= -1;
        posX += velX;
    }
}
