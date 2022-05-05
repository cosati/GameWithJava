package com.alexandrecosati.game.entity;

import com.alexandrecosati.game.GamePanel;
import com.alexandrecosati.game.KeyHandler;
import com.alexandrecosati.game.util.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    private boolean isRightleg;
    private int drawCount;
    private final int FPA = 10; // Frames per animation

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        super.setSpritePath("/player/boy_");
        super.setSpriteExtension(".png");
        setDefaultValues();
        this.sprites = new HashMap<>();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        isRightleg = true;
        drawCount = 0;
        super.setDirection(Direction.DOWN);
    }

    public void update() {

        drawCount++;

        if (drawCount > FPA) {
            drawCount = 0;
            isRightleg = !isRightleg;
        }

        if (keyH.upPressed == true) {
            y -= speed;
            super.setDirection(Direction.UP);
        } else if (keyH.downPressed == true) {
            y += speed;
        super.setDirection(Direction.DOWN);
        } else if (keyH.leftPressed == true) {
            x -= speed;
            super.setDirection(Direction.LEFT);
        } else if (keyH.rightPressed == true) {
            x += speed;
            super.setDirection(Direction.RIGHT);
        }

    }

    public void getPlayerImage() {

        try {

            for (Direction direction : Direction.values()) {

                Map<Boolean, BufferedImage> temp = new HashMap<>();

                for (int i = 0; i < 2; i++) {
                    String spriteNumber = "_" + (i+1);
                    StringBuilder imagePath = new StringBuilder(super.getSpritePath() + direction.getDirection() + spriteNumber + super.getSpriteExtension());
                    BufferedImage image = ImageIO.read(getClass().getResourceAsStream(imagePath.toString()));

                    temp.put(i == 0 ? true : false, image);

                }

                sprites.put(direction, temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        //System.out.println(drawCount);
        image = sprites.get(super.getDirection()).get(isRightleg);
        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }

}
