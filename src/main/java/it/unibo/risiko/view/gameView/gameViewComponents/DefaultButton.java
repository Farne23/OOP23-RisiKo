package it.unibo.risiko.view.gameView.gameViewComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 * A Default button.
 * @author Manuele D'Ambrosio.
 */

public class DefaultButton extends JButton {
    private static final int DEFAULT_FONT_SIZE = 20;
    private static final Color SECONDARY_COLOR = new Color(255, 204, 0);
    private static final Color BLACK_COLOR = new Color(0, 0, 0);
    private static final int DEFAULT_DIMENSION_FACTOR = 2;

    public DefaultButton(final String text, final int fontSize) {
        this.setFont(new Font("Copperplate", Font.BOLD, fontSize));
        this.setForeground(BLACK_COLOR);
        this.setText(text);
        this.setBackground(SECONDARY_COLOR);
        this.setBorder(BorderFactory.createLineBorder(BLACK_COLOR, 3));
        this.setPreferredSize(new Dimension(DEFAULT_FONT_SIZE * DEFAULT_DIMENSION_FACTOR,
                DEFAULT_FONT_SIZE * DEFAULT_DIMENSION_FACTOR));
    }

    public DefaultButton(final String text) {
        this(text, DEFAULT_FONT_SIZE);
    }
}
