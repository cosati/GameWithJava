package com.alexandrecosati.game.entity;

import com.alexandrecosati.game.util.Direction;

import java.awt.image.BufferedImage;
import java.util.Map;

public class Entity {

    public int x, y;
    public int speed;
    private String spritePath;
    private String spriteExtension;

    public Map<Direction, BufferedImage> sprites;
    private Direction direction;

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    public void setSpriteExtension(String spriteExtension) {
        this.spriteExtension = spriteExtension;
    }

    public String getSpritePath() {
        return this.spritePath;
    }

    public String getSpriteExtension() {
        return this.spriteExtension;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return this.direction;
    }
}
