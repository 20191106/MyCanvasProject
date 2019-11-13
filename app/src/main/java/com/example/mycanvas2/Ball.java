package com.example.mycanvas2;

public class Ball {
    int posX;
    int posY;
    int velX;
    int velY;
    int rad;
    int color;

    public Ball(int posX, int posY, int velX, int velY, int rad, int color) {
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY ;
        this.rad = rad;
        this.color = color;
    }

    public void move(int width, int height) {
        if (posX > width - rad || posX < rad) velX *= -1;
        if (posY > height - rad || posY < rad) velY *= -1;
        posX += velX;
        posY += velY;
    }

    public void move2(int width, int height) {
        posX += velX;
        posY += velY;
        if (posX > width - rad || posX < rad) posX -= velX;
        if (posY > height - rad || posY < rad) posY -= velY;
    }


    public void getButton(int dir, boolean isDown){
        if (isDown){
            switch (dir){
                case 0:
                    velY = -10;
                    break;
                case 1:
                    velY = 10;
                    break;
                case 2:
                    velX = -10;
                    break;
                case 3:
                    velX = 10;
                    break;
            }
        }
        else{
            velX = 0;
            velY = 0;
        }

    }
}
