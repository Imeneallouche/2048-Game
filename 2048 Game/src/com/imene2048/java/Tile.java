package com.imene2048.java;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Tile {

    /* _______________________GLOBAL VARIABLES_______________________________ */
    public static final int WIDTH = 80;// the width in pixels of the tile
    public static final int HEIGHT = 80;// the height in pixels of the tile
    public static final int SLIDE_SPEED = 20; // the speed in which the tiles move when they slide
    public static final int ARC_WIDTH = 15;// for rounded rectangles: arc width
    public static final int ARC_HEIGHT = 15;// for rounded rectangles: arc height

    private int value;
    private BufferedImage tileImage;
    private Color text;
    private Color background;
    private Font font;
    private int x;
    private int y;

    // create the constructor
    public Tile(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
        tileImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        drawImage();
    }

    public void drawImage() {

        Graphics2D g = (Graphics2D) tileImage.getGraphics();

        switch (value) {
            case 2:
                background = new Color(0xe9e9e9);
                text = new Color(0x000000);
                break;

            case 4:
                background = new Color(0xe6daab);
                text = new Color(0x000000);
                break;

            case 8:
                background = new Color(0xf79d3d);
                text = new Color(0xffffff);
                break;

            case 16:
                background = new Color(0xe6daab);
                text = new Color(0xffffff);
                break;

            case 32:
                background = new Color(0xf55e3b);
                text = new Color(0x000000);
                break;

            case 64:
                background = new Color(0xff0000);
                text = new Color(0xffffff);
                break;

            case 128:
                background = new Color(0xe9de84);
                text = new Color(0xffffff);
                break;

            case 256:
                background = new Color(0xf6e873);
                text = new Color(0xffffff);
                break;

            case 512:
                background = new Color(0xf5e455);
                text = new Color(0xffffff);
                break;

            case 1024:
                background = new Color(0xf7e12c);
                text = new Color(0xffffff);
                break;

            case 2048:
                background = new Color(0xffe400);
                text = new Color(0xffffff);
                break;

            default:
                background = Color.black;
                text = Color.white;
                break;
        }

        g.setColor(new Color(0, 0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(background);
        g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);

        g.setColor(text);

        if (value <= 64) {
            font = Game.main.deriveFont(36f);
        }

        else {
            font = Game.main;
        }

        g.setFont(font);

    }
}
