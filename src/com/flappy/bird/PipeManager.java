package com.flappy.bird;


import java.awt.*;
import java.util.ArrayList;

public class PipeManager {
    private static final int PIPE_WIDTH = 64;
    private  static final int PIPE_HEIGHT = 512;
    private  static final int OPENING_SPACE = GamePanel.BOARD_HEIGHT / 4;
    private ArrayList<Pipe> pipes;

    public PipeManager() {
        pipes = new ArrayList<>();
    }

    public void placePipes() {
        int randomY = (int) (-PIPE_HEIGHT / 4 - Math.random() * (PIPE_HEIGHT / 2));

        Pipe topPipe = new Pipe(randomY, true);
        Pipe bottomPipe = new Pipe(randomY + PIPE_HEIGHT + OPENING_SPACE, false);
        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    public void movePipes() {
        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.move();
            if (pipe.isOffScreen()) {
                pipes.remove(i);
                i--;
            }
        }
    }

    public double updateScore(Bird bird) {
        double scoreIncrease = 0;
        for (Pipe pipe : pipes) {
            if (!pipe.isPassed() && bird.getX() > pipe.getX() + PIPE_WIDTH) {
                scoreIncrease += 0.5;
                pipe.setPassed(true);
            }
        }
        return scoreIncrease;
    }

    public boolean checkCollision(Bird bird) {
        for (Pipe pipe : pipes) {
            if (pipe.getBounds().intersects(bird.getBounds())) {
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
    }

    public void reset() {
        pipes.clear();
    }
}
