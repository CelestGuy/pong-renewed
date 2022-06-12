package fr.celestgames.pongreborn.game.engine;

import fr.celestgames.pongreborn.game.entities.Ball;
import fr.celestgames.pongreborn.game.entities.Entity;
import fr.celestgames.pongreborn.game.io.Window;

import java.awt.*;

import static fr.celestgames.pongreborn.game.Game.*;

public class CollisionChecker {
    public static void check(Entity entity, Window window) {
        if (entity.x + (entity.dirX + entity.width) >= window.width || entity.x + entity.dirX <= 0) {
            if (entity instanceof Ball) {
                if (entity.x + entity.dirX == 0) {
                    uis.scores.score2++;
                }
                if ((entity.x + entity.width) + entity.dirX == window.width) {
                    uis.scores.score1++;
                }
                setGameState(gameState);
            }
            gameWait(3);
        }
        if (entity.y + (entity.dirY + entity.height) >= window.height || entity.y + entity.dirY <= 0) {
            entity.dirY = entity.dirY * -1;
            entity.isCollideY = true;
        }
    }

    public static void check(Entity entity, Entity target) {
        Rectangle entityArea = new Rectangle(entity.x + entity.collisionArea.x + (entity.speed * entity.dirX), entity.y + entity.collisionArea.y + (entity.speed * entity.dirY), entity.collisionArea.width, entity.collisionArea.height);
        Rectangle targetArea = new Rectangle(target.x + target.collisionArea.x + (target.speed * target.dirX), target.y + target.collisionArea.y + (target.speed * target.dirY), target.collisionArea.width, target.collisionArea.height);

        if (entityArea.intersects(targetArea)) {
            if ((entity.x + entity.dirX + entity.collisionArea.x + entity.collisionArea.width >= target.collisionArea.x)
                    || entity.x + entity.collisionArea.x + entity.dirX <= target.x + target.collisionArea.x) {
                entity.dirX = entity.dirX * -1;
                entity.isCollideX = true;
            }
            if ((entity.dirY < 0 && target.dirY > 0) || (entity.dirY > 0 && target.dirY < 0)) {
                entity.dirY = entity.dirY * -1;
                entity.isCollideY = true;
            }
            if (entity instanceof Ball && entity.speed < 10) {
                entity.speed++;
            }
        } else {
            entity.isCollideX = false;
            entity.isCollideY = false;
        }
    }
}