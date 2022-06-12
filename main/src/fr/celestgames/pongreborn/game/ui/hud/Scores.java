package fr.celestgames.pongreborn.game.ui.hud;

import fr.celestgames.pongreborn.game.ui.Numbers;
import staticfr.celestgames.pongreborn.game.io.Window.scale;

import java.awt.*;

import static fr.celestgames.pongreborn.game.Game.window;

public class Scores {
    public Numbers numbers = new Numbers();
    public int x1, x2, x3, x4, y;
    public int score1, score2;

    public Scores() {
        score1 = 0;
        score2 = 0;
        x1 = window.width / 4 - (numbers.images[0].width * (scale / 3));
        x2 = window.width / 4 + (numbers.images[0].width * (scale / 3));
        x3 = (3 * (window.width / 4)) - (numbers.images[0].width * (scale / 3)) * 5;
        x4 = (3 * (window.width / 4)) - (numbers.images[0].width * (scale / 3)) * 3;
        y = (numbers.images[0].height * scale / 2);
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(numbers.images[(score1 / 10) % 10].sprites[0], x1, y, (numbers.images[0].width * scale), (numbers.images[0].height * scale), null);
        g2.drawImage(numbers.images[score1 % 10].sprites[0], x2, y, (numbers.images[0].width * scale), (numbers.images[0].height * scale), null);
        g2.drawImage(numbers.images[(score2 / 10) % 10].sprites[0], x3, y, (numbers.images[0].width * scale), (numbers.images[0].height * scale), null);
        g2.drawImage(numbers.images[score2 % 10].sprites[0], x4, y, (numbers.images[0].width * scale), (numbers.images[0].height * scale), null);
    }
}