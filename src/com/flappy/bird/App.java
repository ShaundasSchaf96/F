package com.flappy.bird;

import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame("Flappy Bird");
        frame.setSize(GamePanel.BOARD_WIDTH, GamePanel.BOARD_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(App.class.getResource("/com/flappy/bird/images/flappybird.png"));
        frame.setIconImage(icon.getImage());

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        frame.pack();
        gamePanel.requestFocus();
        frame.setVisible(true);
    }
}
