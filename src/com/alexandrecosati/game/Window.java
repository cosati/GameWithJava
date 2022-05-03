package com.alexandrecosati.game;

import javax.swing.JFrame;

public class Window extends JFrame {

    // Screen settings
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRol = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRol;

    public Window() {
        setTitle("My Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(screenWidth, screenHeight, tileSize));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
