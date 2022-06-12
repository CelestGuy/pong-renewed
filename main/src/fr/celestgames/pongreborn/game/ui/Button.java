package fr.celestgames.pongreborn.game.ui;

import java.awt.*;

import static fr.celestgames.pongreborn.game.io.Window.scale;

public class Button {
    private final Selector selector = new Selector();
    private final Font font;
    public String text;
    public int x, y, width, height, toGameState;
    public boolean isSelected, isActive;

    public Button(String text, int x, int y, int toGameState, boolean isActive) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.toGameState = toGameState;
        this.isActive = isActive;
        font = new Font("Arial", Font.PLAIN, 6 * scale);
        width = 32 * scale;
        height = 16 * scale;
        isSelected = false;
    }

    public void draw(Graphics2D g2) {
        if (isActive) {
            g2.setColor(Color.WHITE);
        } else {
            g2.setColor(Color.GRAY);
        }
        g2.setFont(font);
        g2.drawString(text, x, (y + this.height / 2 - font.getSize() / scale));
        if (isSelected) {
            if (selector.animation.x != x) {
                selector.animation.x = x - (width / scale);
            }
            if (selector.animation.y != y) {
                selector.animation.y = y;
            }
            selector.animation.draw(g2);
        }
    }

    public static class Selector {
        public Component animation;

        public Selector() {
            String[] strings = new String[6];
            for (int i = 0; i < strings.length; i++) {
                strings[i] = "/assets/textures/ui/selector/sel" + (i + 1) + ".png";
            }
            animation = new Component(Component.ANIMATION, strings);
            animation.prefScale = 2;
        }
    }
}
