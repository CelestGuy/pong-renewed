package fr.celestgames.pongreborn.game.entities;

import java.awt.*;

import static fr.celestgames.pongreborn.game.Game.TICK;

public class Entity {
    public int x, y, dirX, dirY, speed, width, height;
    public boolean isCollideX, isCollideY;
    public String name;
    public Rectangle collisionArea;
    public Component component;

    public Entity(int width, int height) {
        this.width = width;
        this.height = height;
        this.isCollideX = false;
        this.isCollideY = false;
    }

    public void setSpeed(int speed) {
        this.speed = speed / (TICK / 20);
    }
}
