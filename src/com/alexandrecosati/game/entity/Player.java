package com.alexandrecosati.game.entity;

import com.alexandrecosati.game.GamePanel;
import com.alexandrecosati.game.KeyHandler;
import com.alexandrecosati.game.util.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        super.setSpritePath("/player/boy_");
        super.setSpriteExtension("_1.png");
        setDefaultValues();
        this.sprites = new HashMap<>();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        super.setDirection(Direction.DOWN);
    }

    public void update() {

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
                StringBuilder imagePath = new StringBuilder(super.getSpritePath() + direction.getDirection() + super.getSpriteExtension());
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream(imagePath.toString()));
                sprites.put(direction, image);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        image = sprites.get(super.getDirection());
        g2.drawImage(image, x, y, gp.getTileSize(), gp.getTileSize(), null);
    }

}
