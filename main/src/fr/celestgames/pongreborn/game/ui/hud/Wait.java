package fr.celestgames.pongreborn.game.ui.hud;

import fr.celestgames.pongreborn.game.ui.Component;
import fr.celestgames.pongreborn.game.ui.Numbers;
import staticfr.celestgames.pongreborn.game.io.Window.scale;

import java.awt.*;

import static fr.celestgames.pongreborn.game.Game.waitSeconds;
import static fr.celestgames.pongreborn.game.Game.window;

public class Wait {
    private final Numbers numbers = new Numbers();
    private final Component backgroundImage = new Component(Component.IMAGE, "/assets/textures/ui/font/background.png");

    public Wait() {
        backgroundImage.x = window.width / 2 - (backgroundImage.width / 2 * scale);
        backgroundImage.y = window.height / 2 - (backgroundImage.height / 2 * scale);
    }

    public void draw(Graphics2D g2) {
        if (waitSeconds >= 0 && waitSeconds < 10) {
            backgroundImage.draw(g2);
            g2.drawImage(numbers.images[waitSeconds].sprites[0], (window.width / 2 - (numbers.images[0].width * scale / 2)), (window.height / 2 - (numbers.images[0].height * scale / 2)), (numbers.images[0].width * scale), (numbers.images[0].height * scale), null);
        }
    }
}
