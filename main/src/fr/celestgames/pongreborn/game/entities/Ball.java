package fr.celestgames.pongreborn.game.entities;

import staticfr.celestgames.pongreborn.game.engine.CollisionChecker.check;

import java.awt.*;
import java.util.Random;

import static fr.celestgames.pongreborn.game.Game.entities;
import static fr.celestgames.pongreborn.game.Game.window;

public class Ball extends Entity {
    public int ballSize = 16;

    public Ball() {
        super(16, 16);
        Random random = new Random();
        dirX = random.nextInt(2);
        if (dirX == 0) {
            dirX--;
        }
        dirY = random.nextInt(2);
        if (dirY == 0) {
            dirY--;
        }

        x = window.width / 2 - ballSize / 2;
        y = window.height / 2 - ballSize / 2;
        component = new Component(Component.IMAGE, "/assets/textures/ball/ball.png");
        component.prefScale = 4;
        collisionArea = new Rectangle(0, 0, ballSize, ballSize);
        setSpeed(20);

        component.x = x;
        component.y = y;
    }

    public void update() {
        for (int i = 0; i < speed; i++) {
            check(this, window);
            if (x <= window.width / 4) {
                check(this, entities.player1);
            } else if (x >= (window.width / 4) * 3) {
                check(this, entities.player2);
            }
            component.x = x;
            component.y = y;
            x += dirX;
            y += dirY;
        }
    }
}
