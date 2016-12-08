package image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BlackAndWhiteImage {

    private final BlackAndWhitePixel[][] blackAndWhitePixels;
    private final int width;
    private final int height;

    public BlackAndWhiteImage(final BlackAndWhitePixel[][] blackAndWhitePixels, final int width, final int height) {
        this.blackAndWhitePixels = blackAndWhitePixels;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private boolean isPositionExists(final int x, final int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public BlackAndWhitePixel getPixel(final int x, final int y) {
        if(isPositionExists(x, y)) {
            return blackAndWhitePixels[x][y];
        } else {
            throw new IllegalArgumentException("Position does not exist.");
        }
    }

    public void display(final String title) {
        BufferedImage image = new ImageManager().generateBlackAndWhiteImage(this);
        ImageIcon icon = new ImageIcon(image);
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setLayout(new FlowLayout());
        frame.setSize(width,height);
        JLabel jLabel = new JLabel();
        jLabel.setIcon(icon);
        frame.add(jLabel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
