package fr.celestgames.pongreborn.game;

import fr.celestgames.pongreborn.game.entities.Ball;
import fr.celestgames.pongreborn.game.entities.Player;
import fr.celestgames.pongreborn.game.io.Keyboard;
import fr.celestgames.pongreborn.game.io.Window;
import fr.celestgames.pongreborn.game.ui.hud.Scores;
import fr.celestgames.pongreborn.game.ui.hud.Wait;
import fr.celestgames.pongreborn.game.ui.screens.PausedScreen;
import fr.celestgames.pongreborn.game.ui.screens.TitleScreen;

public class Game implements Runnable {
    public static final int TICK = 60;
    public static final int GET_LAST_STATE = -10;
    public static final int TITLE_STATE = 0;
    public static final int PAUSE_STATE = -1;
    public static final int PLAY_SOLO_STATE = 1;
    public static final int PLAY_MULTI_STATE = 2;
    public static final int PLAY_ONLINE_STATE = 3;
    public static final int ONE_SEC_IN_NANO = 1000000000;

    public static final Keyboard keyboard = new Keyboard();
    public static final Window window = new Window();
    public static final UIs uis = new UIs();
    public static final Entities entities = new Entities();

    public static int gameState;
    public static int lastGameState;
    public static long timer;
    public static int waitSeconds;

    private final Thread mainThread = new Thread(this, "MAIN_THREAD");

    public Game() {
        timer = 0;
        setGameState(TITLE_STATE);
        mainThread.start();
    }

    public static void gameWait(int seconds) {
        waitSeconds = seconds;
        timer = 0;
    }

    public static void pauseGame() {
        uis.pausedScreen = new PausedScreen();
        lastGameState = gameState;
        gameState = PAUSE_STATE;
    }

    public static void resumeGame() {
        gameState = lastGameState;
    }

    public static void setGameState(int state) {
        if (state != gameState) {
            uis.scores = new Scores();
        }
        switch (state) {
            case PLAY_SOLO_STATE -> {
                entities.ball = new Ball();
                entities.player1 = new Player(1);
                entities.player2 = new Player(0);
            }
            case PLAY_MULTI_STATE -> {
                entities.ball = new Ball();
                entities.player1 = new Player(1);
                entities.player2 = new Player(2);
            }
        }
        gameState = state;
    }

    @Override
    public void run() {
        long interval;
        window.renderThread.start();

        while (mainThread.isAlive()) {
            interval = ONE_SEC_IN_NANO / TICK;
            this.update();
            timer += interval;
            if (timer >= ONE_SEC_IN_NANO) {
                if (waitSeconds > 0 && gameState != PAUSE_STATE) {
                    waitSeconds--;
                }
                timer = 0;
            }
            try {
                Thread.sleep(interval / 1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        switch (gameState) {
            case PLAY_SOLO_STATE, PLAY_MULTI_STATE -> {
                if (waitSeconds == 0) {
                    entities.ball.update();
                    entities.player1.update();
                    entities.player2.update();
                }
            }
            case PAUSE_STATE -> {
                uis.pausedScreen.update();
            }
            case TITLE_STATE -> {
                uis.titleScreen.update();
            }
        }
    }

    public static class UIs {
        public TitleScreen titleScreen = new TitleScreen();
        public PausedScreen pausedScreen = new PausedScreen();
        public Wait waitScreen = new Wait();
        public Scores scores = new Scores();
    }

    public static class Entities {
        public Ball ball = new Ball();
        public Player player1 = new Player(1);
        public Player player2 = new Player(2);
    }
}
