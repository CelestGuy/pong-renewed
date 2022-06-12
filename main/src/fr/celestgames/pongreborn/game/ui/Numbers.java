package fr.celestgames.pongreborn.game.ui;

public class Numbers {
    public final Component[] images = new Component[10];

    public Numbers() {
        for (int i = 0; i < 10; i++) {
            images[i] = new Component(Component.IMAGE, "/assets/textures/ui/font/" + i + ".png");
        }
    }
}
