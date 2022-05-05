package com.alexandrecosati.game.util;

import java.awt.event.KeyEvent;

public enum Direction {

    UP("up", KeyEvent.VK_W),
    DOWN("down", KeyEvent.VK_S),
    LEFT("left", KeyEvent.VK_A),
    RIGHT("right", KeyEvent.VK_D);

    final String direction;
    final int keyCode;

    Direction(String direction, int keyCode) {
        this.direction = direction;
        this.keyCode = keyCode;
    }

    public String getDirection() {
        return direction;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public static Direction toEnum(int keyCode) {
        for (Direction direction : Direction.values()) {
            if (direction.getKeyCode() == keyCode) {
                return direction;
            }
        }
        throw new IllegalArgumentException("Invalid keycode: " + keyCode);
    }

}
