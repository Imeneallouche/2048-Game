package com.imene2048.java;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, Runnable {

    private static final long serialVersionUID = 1L;

    /*---------------GLOBAL VARIABLES------------------ */
    public static final int WIDTH = 400;// width of the screen
    public static final int HEIGHT = 630; // height of the screen
    public static final Font main = new Font("Bebas Neue Regular", Font.PLAIN, 28);// font of the screen
    /*-------------------------------------------------- */

    /*---------------LOCAL VARIABLES------------------ */
    private Thread game;// game thread
    private boolean running;// variable to check if the game thread is running
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    private long startTime;
    private long elapsed;
    private boolean set;

    public Game() {
        setFocusable(true);// allows keyborad input
        setPreferredSize(new Dimension(WIDTH, HEIGHT));// determine how big the frame is
        addKeyListener(this);
    }

    private void update() {

    }

    private void render() {
        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);// fill the rectangle with white
        g.dispose();// render the board

        Graphics2D g2d = (Graphics2D) getGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

    }

    @Override
    public void run() {
        // this method will be called when we start a thread
        int fps = 0;
        int updates = 0;
        long fpsTimer = System.currentTimeMillis();
        double nsPerupdate = 1000000000.0 / 60;

        // last update time in nano seconds
        double then = System.nanoTime();
        double unprocessed = 0;

        while (running) {
            boolean shouldRender = false;

            double now = System.nanoTime();
            unprocessed += (now - then) / nsPerupdate;
            then = now;

            // update queue
            while (unprocessed >= 1) {
                updates++;
                update();
                unprocessed--;
                shouldRender = true;
            }

            // rendering
            if (shouldRender) {
                fps++;
                render();
                shouldRender = false;
            }

            else {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        // FPS Timer
        if (System.currentTimeMillis() - fpsTimer > 1000) {
            System.out.printf("%d fps %d updates", fps, updates);
            System.out.println();
            fps = 0;
            updates = 0;
            fpsTimer += 1000;
        }
    }

    public synchronized void start() {
        if (running)
            return;

        running = true;
        game = new Thread(this, "game");
        game.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        running = false;
        System.exit(0);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }
}
