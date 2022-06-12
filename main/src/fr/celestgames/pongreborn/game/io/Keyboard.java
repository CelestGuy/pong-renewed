package fr.celestgames.pongreborn.game.io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static fr.celestgames.pongreborn.game.Game.*;

public class Keyboard implements KeyListener {
    public boolean enterKeyPressed, upKeyPressed, downKeyPressed, zPressed, sPressed, oPressed, kPressed;

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (gameState) {
            case TITLE_STATE, PAUSE_STATE -> {
                if (keyCode == KeyEvent.VK_ENTER) {
                    enterKeyPressed = true;
                }
                if (keyCode == KeyEvent.VK_UP) {
                    upKeyPressed = true;
                }
                if (keyCode == KeyEvent.VK_DOWN) {
                    downKeyPressed = true;
                }
            }
            case PLAY_SOLO_STATE, PLAY_MULTI_STATE, PLAY_ONLINE_STATE -> {
                if (keyCode == KeyEvent.VK_Z) {
                    zPressed = true;
                }
                if (keyCode == KeyEvent.VK_S) {
                    sPressed = true;
                }
                if (keyCode == KeyEvent.VK_O) {
                    oPressed = true;
                }
                if (keyCode == KeyEvent.VK_K) {
                    kPressed = true;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_ENTER) {
            enterKeyPressed = false;
        }
        if (keyCode == KeyEvent.VK_UP) {
            upKeyPressed = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downKeyPressed = false;
        }
        if (keyCode == KeyEvent.VK_Z) {
            zPressed = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            sPressed = false;
        }
        if (keyCode == KeyEvent.VK_O) {
            oPressed = false;
        }
        if (keyCode == KeyEvent.VK_K) {
            kPressed = false;
        }
        if (keyCode == KeyEvent.VK_P && gameState != TITLE_STATE) {
            if (gameState == PAUSE_STATE) {
                resumeGame();
            } else {
                pauseGame();
            }
        }
    }
}
