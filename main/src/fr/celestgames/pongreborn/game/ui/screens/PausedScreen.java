package fr.celestgames.pongreborn.game.ui.screens;

import fr.celestgames.pongreborn.game.ui.Button;
import fr.celestgames.pongreborn.game.ui.Component;
import staticfr.celestgames.pongreborn.game.io.Window.scale;

import java.awt.*;
import java.util.ArrayList;

import static fr.celestgames.pongreborn.game.Game.*;

public class PausedScreen {
    private final Button resumeButton = new Button("Resume", 0, 0, GET_LAST_STATE, true);
    private final Button toTitleButton = new Button("Quit To Title", 0, 0, TITLE_STATE, true);
    private final Component pausedImage = new Component(Component.IMAGE, "/assets/textures/ui/paused.png");
    private final ArrayList<Button> buttons;
    private int selButton;
    private int wait;

    public PausedScreen() {
        buttons = new ArrayList<>();
        pausedImage.x = window.width / 2 - (pausedImage.width * scale) / 2;
        pausedImage.y = window.height / 2 - (pausedImage.height * scale) / 2;

        resumeButton.x = window.width / 2 - resumeButton.width / 2;
        resumeButton.y = window.height / 2 + resumeButton.height / 2;

        toTitleButton.x = window.width / 2 - toTitleButton.width / 2;
        toTitleButton.y = window.height / 2 + (toTitleButton.height / 2) * 2;

        buttons.add(resumeButton);
        buttons.add(toTitleButton);
    }

    public void draw(Graphics2D g2) {
        if (pausedImage.y - (30 / (window.maxFPS / 20)) >= window.height / 8) {
            pausedImage.y -= 30 / (window.maxFPS / 20);
        }
        if (resumeButton.y - (30 / (window.maxFPS / 20)) >= window.height / 2 - (toTitleButton.height / 2) * 2) {
            resumeButton.y -= 30 / (window.maxFPS / 20);
        }
        if (toTitleButton.y - (30 / (window.maxFPS / 20)) >= window.height / 2 - (toTitleButton.height / 2)) {
            toTitleButton.y -= 30 / (window.maxFPS / 20);
        }
        pausedImage.draw(g2);
        resumeButton.draw(g2);
        toTitleButton.draw(g2);
    }

    public void update() {
        buttons.get(selButton).isSelected = true;
        if (keyboard.downKeyPressed || keyboard.upKeyPressed || keyboard.enterKeyPressed) {
            if (keyboard.upKeyPressed && selButton > 0) {
                if (wait == 0) {
                    buttons.get(selButton).isSelected = false;
                    selButton--;
                }
            }
            if (keyboard.downKeyPressed && selButton < buttons.size() - 1) {
                if (wait == 0) {
                    buttons.get(selButton).isSelected = false;
                    selButton++;
                }
            }
            if (keyboard.enterKeyPressed && buttons.get(selButton).isActive) {
                if (wait == 2 && buttons.get(selButton).toGameState != GET_LAST_STATE) {
                    setGameState(buttons.get(selButton).toGameState);
                    wait = (TICK / 4);
                } else if (buttons.get(selButton).toGameState == GET_LAST_STATE) {
                    resumeGame();
                }
            }
            wait = (wait + 1) % (TICK / 4);
        } else {
            wait = 0;
        }
    }
}
