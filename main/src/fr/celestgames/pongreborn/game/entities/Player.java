package fr.celestgames.pongreborn.game.entities;

import java.awt.*;

import static fr.celestgames.pongreborn.game.Game.*;

public class Player extends Entity {
    public int points;
    private boolean upKey, downKey;
    private boolean bot;

    public Player(int playerNum) {
        super(16, 64);
        name = "player_" + playerNum;
        y = 0;
        x = 0;
        component = new Component(Component.IMAGE, "/assets/textures/player/player.png");
        component.prefScale = 4;
        points = 0;
        bot = false;

        if (name.equals("player_1")) {
            y = window.height / 2 - height / 2;
            collisionArea = new Rectangle(-5, 3, width, 60);
        } else if (name.equals("player_2") || name.equals("player_0")) {
            x = window.width - width;
            y = window.height / 2 - height / 2;
            collisionArea = new Rectangle(5, 3, width, 60);
        }

        if (playerNum == 0) {
            bot = true;
            component = new Component(Component.IMAGE, "/assets/textures/player/player_bot.png");
            component.prefScale = 4;
        }

        component.x = x;
        component.y = y;
    }

    public void update() {
        if (name.equals("player_1")) {
            upKey = keyboard.zPressed;
            downKey = keyboard.sPressed;
        } else if (name.equals("player_2") && !bot) {
            upKey = keyboard.oPressed;
            downKey = keyboard.kPressed;
        }

        if ((upKey || downKey) && !bot) {
            if (upKey) {
                if (y - 8 >= 0) {
                    dirY = -8;
                    y += dirY;
                }
            }
            if (downKey) {
                if (y + 8 <= window.height - height) {
                    dirY = 8;
                    y += dirY;
                }
            }
        } else if (bot) {
            if (entities.ball.x >= window.width / 2) {
                if (y + height / 2 >= entities.ball.y && entities.ball.dirX > 0 && y - 8 >= 0 && entities.ball.dirY < 0) {
                    dirY = -8;
                    y += dirY;
                }
                if (y + height / 2 <= entities.ball.y && y + 8 <= window.height - height && entities.ball.dirX > 0 && entities.ball.dirY > 0) {
                    dirY = 8;
                    y += dirY;
                }
            }
        }
        component.x = x;
        component.y = y;

        dirY = 0;
    }
}
