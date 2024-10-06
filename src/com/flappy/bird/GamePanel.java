package com.flappy.bird;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
    public static final int BOARD_WIDTH = 500;
    public static final int BOARD_HEIGHT = 889;
    private static final String FLAPPY_BIRD_IMG = "/com/flappy/bird/images/flappybird.png";
    private static final String  FLAPPY_BIRD_BG= "/com/flappy/bird/images/flappybirdbg.png";
    private static final String FONT = "Arial";

    private Bird bird;
    private PipeManager pipeManager;

    private Timer gameLoop;
    private Timer pipeTimer;

    private double score = 0;
    private boolean gameOver = false;

    public GamePanel() {
        setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        setFocusable(true);
        addKeyListener(this);

        bird = new Bird(BOARD_HEIGHT / 8, BOARD_WIDTH / 2, new ImageIcon(getClass().getResource(FLAPPY_BIRD_IMG)).getImage());
        pipeManager = new PipeManager();

        pipeTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pipeManager.placePipes();
            }
        });
        pipeTimer.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.drawImage(new ImageIcon(getClass().getResource(FLAPPY_BIRD_BG)).getImage(), 0, 0, BOARD_WIDTH, BOARD_HEIGHT, null);
        bird.draw(g);
        pipeManager.draw(g);

        g.setColor(Color.white);
        g.setFont(new Font(FONT, Font.PLAIN, 32));
        if (gameOver) {
            g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
        } else {
            g.drawString(String.valueOf((int) score), 10, 35);
        }
    }

    private void move() {
        bird.move();
        pipeManager.movePipes();

        score += pipeManager.updateScore(bird);
        if (pipeManager.checkCollision(bird) || bird.getY() > BOARD_HEIGHT) {
            gameOver = true;
            stopTimers();
        }
    }

    private void restartGame() {
        gameOver = false;
        score = 0;
        bird.resetPosition();
        pipeManager.reset();

        pipeTimer.restart();
        gameLoop.restart();
        requestFocusInWindow();
    }

    private void stopTimers() {
        pipeTimer.stop();
        gameLoop.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameOver) {
                bird.jump();
            } else {
                restartGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
