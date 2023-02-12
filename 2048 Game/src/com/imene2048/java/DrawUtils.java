package com.imene2048.java;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import java.awt.font.TextLayout;

public class DrawUtils {
    public DrawUtils() {
        // constructor
    }

    // how wide the message is in order to center it
    public static int getMessageWidth(String message, Font font, Graphics2D g) {
        g.setFont(font);
        Rectangle2D bounds = g.getFontMetrics().getStringBounds(message, g);
        return (int) bounds.getWidth();
    }

    // the height of the message in order to well place it
    // the real font weight is not always as we set it up (> thanthe value set)
    public static int getMessageHeight(String message, Font font, Graphics2D g) {
        g.setFont(font);

        // if there is no message: empty message so the height is 0
        if (message.length() == 0) {
            return 0;
        }

        TextLayout tl = new TextLayout(message, font, g.getFontRenderContext());
        return (int) tl.getBounds().getHeight();
    }
}
