package fr.celestgames.pongreborn.game.ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static fr.celestgames.pongreborn.game.Game.window;
import static fr.celestgames.pongreborn.game.io.Window.scale;

public class Component {
    public static final int DIALOGUE = 0;
    public static final int IMAGE = 1;
    public static final int ANIMATION = 2;
    public final int type;
    public int x, y, offset, counter, width, height, prefScale;
    public String text;
    public BufferedImage[] sprites;
    public boolean loop;

    public Component(int type, String... strings) {
        InputStream is;
        this.type = type;
        this.prefScale = 1;

        switch (type) {
            case DIALOGUE -> {
                loop = false;
                text = Arrays.toString(strings);
            }
            case IMAGE -> {
                loop = false;
                counter = 0;
                is = getClass().getResourceAsStream(strings[0]);
                if (is != null) {
                    try {
                        sprites[counter] = ImageIO.read(is);
                        width = sprites[counter].getWidth();
                        height = sprites[counter].getHeight();
                    } catch (IOException e) {
                        e.printStackTrace();
                        sprites[counter] = null;
                    }
                }
            }
            case ANIMATION -> {
                sprites = new BufferedImage[strings.length];
                offset = 0;
                counter = 0;
                for (int i = 0; i < strings.length; i++) {
                    is = getClass().getResourceAsStream(strings[i]);
                    if (is != null) {
                        try {
                            sprites[i] = ImageIO.read(is);
                        } catch (IOException e) {
                            e.printStackTrace();
                            sprites[i] = null;
                        }
                    }
                }
                width = sprites[0].getWidth();
                height = sprites[0].getHeight();
            }
        }
    }

    public void draw(Graphics2D g2) {
        switch (type) {
            case DIALOGUE -> {
                System.out.println(text);
            }
            case IMAGE -> {
                g2.drawImage(sprites[0], x, y, (width * scale / prefScale), (height * scale / prefScale), null);
            }
            case ANIMATION -> {
                g2.drawImage(sprites[this.offset], x, y, (width * (scale / prefScale)), (width * (scale / prefScale)), null);
                if (this.offset + 1 >= sprites.length) {
                    this.offset = 0;
                } else if (counter == 0) {
                    this.offset++;
                }
                counter = (counter + 1) % (window.maxFPS / 8);
            }
        }
    }
}
