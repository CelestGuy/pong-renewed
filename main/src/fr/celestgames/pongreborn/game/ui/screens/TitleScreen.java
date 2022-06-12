package fr.celestgames.pongreborn.game.ui.screens;

import fr.celestgames.pongreborn.game.ui.Button;
import fr.celestgames.pongreborn.game.ui.Component;

import java.awt.*;
import java.util.ArrayList;

import static fr.celestgames.pongreborn.game.Game.*;
import static fr.celestgames.pongreborn.game.io.Window.scale;

public class TitleScreen {
    private final ArrayList<Button> buttons;
    private final Component title;
    private int wait;
    private int selButton;

    public TitleScreen() {
        Button solo = new Button("Solo", (window.width / 2 - 32), (window.height / 2), PLAY_SOLO_STATE, true);
        Button multi = new Button("Multiplayer", (window.width / 2 - 32), (window.height / 2 + 32), PLAY_MULTI_STATE, true);
        Button online = new Button("Online", (window.width / 2 - 32), (window.height / 2 + 64), PLAY_ONLINE_STATE, false);

        this.buttons = new ArrayList<>();
        buttons.add(solo);
        buttons.add(multi);
        buttons.add(online);

        this.wait = 0;

        title = new Component(Component.IMAGE, "/assets/textures/ui/title.png");
        title.x = window.width / 2 - ((title.width / 2) * scale);
        title.y = window.height / 2 - ((title.height * 3) * scale);
    }

    public void draw(Graphics2D g2) {
        title.draw(g2);
        for (Button b : buttons) {
            b.draw(g2);
        }
    }

    public void update() {
        buttons.get(selButton).isSelected = true;
        if (keyboard.downKeyPressed || keyboard.upKeyPressed) {
            if (keyboard.upKeyPressed && selButton > 0) {
                if (wait == 0) {
                    buttons.get(selButton).isSelected = false;
                    selButton--;
                }
                wait = (wait + 1) % (TICK / 4);
            }
            if (keyboard.downKeyPressed && selButton < buttons.size() - 1) {
                if (wait == 0) {
                    buttons.get(selButton).isSelected = false;
                    selButton++;
                }
                wait = (wait + 1) % (TICK / 4);
            }
        } else {
            wait = 0;
        }
        if (keyboard.enterKeyPressed && buttons.get(selButton).isActive) {
            setGameState(buttons.get(selButton).toGameState);
            gameWait(3);
        }
    }
}
