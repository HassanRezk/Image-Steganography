package image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class RGBImage {

    private final RGBPixel[][] rgbPixels;
    private final int width;
    private final int height;

    public RGBImage(final RGBPixel[][] rgbPixels, final int width, final int height) {
        this.rgbPixels = rgbPixels;
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

    public int getPixelAsInt(final int x, final int y) {
        if(isPositionExists(x, y)) {
            return (rgbPixels[x][y].getBlue() | (rgbPixels[x][y].getGreen() << 8) | (rgbPixels[x][y].getGreen() << 16));
        } else {
            throw new IllegalArgumentException("Position does not exist.");
        }
    }

    public RGBPixel getPixel(final int x, final int y) {
        if(isPositionExists(x, y)) {
            return rgbPixels[x][y];
        } else {
            throw new IllegalArgumentException("Position does not exist.");
        }
    }

    public void setRGBPixel(final int x, final int y, final RGBPixel rgbPixel) {
        if(isPositionExists(x, y)) {
            rgbPixels[x][y] = rgbPixel;
        } else {
            throw new IllegalArgumentException("Position does not exist.");
        }
    }

    public RGBImage hide(final BlackAndWhiteImage message) {
        RGBPixel[][] encryptedImage = new RGBPixel[width][height];
        for(int i = 0 ; i < width ; ++i) {
            for(int j = 0 ; j < height ; ++j) {
                if(this.rgbPixels[i][j].getRed() % 2 == 0) {
                    if(!message.getPixel(i, j).isWhite()) {
                        encryptedImage[i][j] = new RGBPixel(this.rgbPixels[i][j].getRed() + 1,
                                this.rgbPixels[i][j].getGreen(), this.rgbPixels[i][j].getBlue());
                    } else {
                        encryptedImage[i][j] = new RGBPixel(this.rgbPixels[i][j]);
                    }
                } else {
                    if(message.getPixel(i, j).isWhite()) {
                        encryptedImage[i][j] = new RGBPixel(this.rgbPixels[i][j].getRed() - 1,
                                this.rgbPixels[i][j].getGreen(), this.rgbPixels[i][j].getBlue());
                    } else {
                        encryptedImage[i][j] = new RGBPixel(this.rgbPixels[i][j]);
                    }
                }
            }
        }
        return new RGBImage(encryptedImage, width, height);
    }

    public BlackAndWhiteImage reveal() {
        BlackAndWhitePixel[][] message = new BlackAndWhitePixel[width][height];
        for(int i = 0 ; i < width ; ++i) {
            for(int j = 0 ; j < height ; ++j) {
                if(this.rgbPixels[i][j].getRed() % 2 == 0) {
                    message[i][j] = new BlackAndWhitePixel(true);
                } else {
                    message[i][j] = new BlackAndWhitePixel(false);
                }
            }
        }
        return new BlackAndWhiteImage(message, width, height);
    }

    public void display(final String title) {
        BufferedImage image = new ImageManager().generateRGBImage(this);
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
