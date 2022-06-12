package fr.celestgames.pongreborn.game.io;

import javax.swing.*;
import java.awt.*;

import static fr.celestgames.pongreborn.game.Game.*;

public class Window extends JPanel implements Runnable {
    public static int scale;
    public int width, height;
    public int maxFPS;
    public JFrame window = new JFrame();
    public Thread renderThread = new Thread(this, "RENDER_THREAD");

    public Window() {
        window.setTitle("Pong Renewed");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        height = 720;
        width = 1280;
        maxFPS = 60;
        scale = width / height * 4;

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(keyboard);
        setFocusable(true);

        window.add(this);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    @Override
    public void run() {
        long interval = 0;
        long timer = 0;
        int frame = 0;

        while (renderThread.isAlive()) {
            repaint();
            if (System.getProperty("os.name").equalsIgnoreCase("linux")) {
                Toolkit.getDefaultToolkit().sync();
            }

            interval = ONE_SEC_IN_NANO / maxFPS;
            frame++;
            timer += interval;

            if (timer >= ONE_SEC_IN_NANO) {
                window.setTitle("PONG RENEWED - FPS : " + frame);
                frame = 0;
                timer = 0;
            }

            try {
                Thread.sleep(interval / 1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics;

        if (gameState == TITLE_STATE) {
            uis.titleScreen.draw(g2);
        } else if (gameState == PLAY_SOLO_STATE || gameState == PLAY_MULTI_STATE || gameState == PLAY_ONLINE_STATE || gameState == PAUSE_STATE) {
            g2.setColor(Color.WHITE);
            g2.fillRect((width / 2 - 2), 0, 4, height);

            uis.scores.draw(g2);

            entities.ball.component.draw(g2);
            entities.player1.component.draw(g2);
            entities.player2.component.draw(g2);

            if (waitSeconds > 0) {
                uis.waitScreen.draw(g2);
            }
            if (gameState == PAUSE_STATE) {
                uis.pausedScreen.draw(g2);
            }
        }
        g2.dispose();
    }
}
