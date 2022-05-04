package com.alexandrecosati.game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    private final int FPS = 60;

    public static int width;
    public static int height;
    public static int tileSize;

    private Thread thread;
    private boolean running = false;

    private BufferedImage img;
    private Graphics2D g;

    KeyHandler keyH = new KeyHandler();

    // Player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(int width, int height, int tileSize) {
        this.width = width;
        this.height = height;
        this.tileSize = tileSize;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        this.addKeyListener(keyH);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();

        if (thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void init() {
        running = true;

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) img.getGraphics();
    }

    @Override
    public void run() {
        init();

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (running) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }

    }

    private int x = 0;

    public void update() {

        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
        }
        if (keyH.downPressed == true) {
            playerY += playerSpeed;
        }
        if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        }
        if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }

    }

    public void render() {
        if (g != null) {
            g.setColor(new Color(66, 134, 244));
            g.fillRect(0, 0, width, height);
        }
    }

    public void draw() {
        Graphics g2 = (Graphics) this.getGraphics();
        g2.drawImage(img, 0, 0, width, height, null);
        g2.dispose();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
    }

    public void input() {

    }

}
