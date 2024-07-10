package it.unibo.risiko.view.gameView.gameViewComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 * ColoredImageButton enables to create a button with a given image as
 * background,
 * it also allows to change the image color by generating the right path to find
 * it in the resources folder.
 * 
 * @author Michele Farneti
 */
public class ColoredImageButton extends JButton {
    private final String STANDARD_COLOR = "white";
    private final String resourcesPackagePath;
    private static final String fileFormat = ".png";
    private static final int BORDER_THICKNESS = 2;

    private String imageUri;
    private String imageColor = STANDARD_COLOR;

    /**
     * @param imageUri The url of the image to be set as backgroud of the button
     */
    public ColoredImageButton(String resourcesPackagePath, final String imageUri) {
        this.imageUri = imageUri;
        this.resourcesPackagePath = resourcesPackagePath;
    }

    /**
     * Constructor wich sets the button background image and also sets its bounds
     * 
     * @param imageUrl
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public ColoredImageButton(String resourcesPackagePath, final String imageUrl, final int x, final int y,
            final int width, final int height) {
        this.resourcesPackagePath = resourcesPackagePath;
        this.imageUri = imageUrl;
        this.setBounds(x, y, width, height);
        this.setOpaque(false);
    }

    /**
     * @param imageColor A string raprresenting the color in rgb format.
     */
    public void setColor(final String imageColor) {
        this.imageColor = imageColor;
    }

    @Override
    protected void paintComponent(final Graphics g) {
        String coloredImageUrl = resourcesPackagePath + imageUri + imageColor + fileFormat;
        try {
            Image image = ImageIO.read(new File(coloredImageUrl));
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        } catch (IOException e) {
            System.out.println("Failed to load image " + coloredImageUrl);
        }
    }

    /**
     * Sets a prederminated border of a giveng color
     */
    public void setCustomBorder(Color borderColor) {
        this.setBorder(BorderFactory.createLineBorder(borderColor, BORDER_THICKNESS));
    }
}
