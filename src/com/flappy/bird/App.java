package com.flappy.bird;

import javax.swing.*;

public class App {
    private static final String FLAPPY_BIRD_IMG = "/com/flappy/bird/images/flappybird.png";
    private static final String TITLE = "Flappy Bird";
    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame(TITLE);
        frame.setSize(GamePanel.BOARD_WIDTH, GamePanel.BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(App.class.getResource(FLAPPY_BIRD_IMG));
        frame.setIconImage(icon.getImage());

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.pack();
        gamePanel.requestFocus();
        frame.setVisible(true);
    }
}
