package com.flappy.bird;

import java.awt.*;

public class Bird {
    private static final int GRAVITY = 1;
    private static final int JUMP_STRENGTH = -9;

    private final int initialX, initialY;
    private int x, y;
    private int width, height;
    private int velocityY;
    private Image img;

    public Bird(int x, int y, Image img) {
        this.initialX = x;
        this.initialY = y;
        this.x = x;
        this.y = y;
        this.width = 34;
        this.height = 24;
        this.img = img;
        this.velocityY = 0;
    }

    public void jump() {
        velocityY = JUMP_STRENGTH;
    }

    public void move() {
        velocityY += GRAVITY;
        y += velocityY;
        y = Math.max(y, 0);
    }

    public void draw(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void resetPosition() {
        this.x = initialX;
        this.y = initialY;
        this.velocityY = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
