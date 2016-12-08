package image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class ImageManager {

    public RGBImage getRGBImage(final String imagePath) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        RGBPixel[][] rgbPixels = new RGBPixel[width][height];
        for(int i = 0 ; i < width ; ++i) {
            for(int j = 0 ; j < height ; ++j) {
                int rgb = bufferedImage.getRGB(i, j);
                int red = (rgb >> 16) & 0x000000FF;
                int green = (rgb >> 8) & 0x000000FF;
                int blue = (rgb) & 0x000000FF;
                rgbPixels[i][j] = new RGBPixel(red, green, blue);
            }
        }
        return new RGBImage(rgbPixels, width, height);
    }

    public BlackAndWhiteImage getBlackAndWhiteImageImage(final String imagePath) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BlackAndWhitePixel[][] blackAndWhitePixels = new BlackAndWhitePixel[width][height];
        for(int i = 0 ; i < width ; ++i) {
            for(int j = 0 ; j < height ; ++j) {
                int rgb = bufferedImage.getRGB(i, j);
                int red = (rgb >> 16) & 0x000000FF;
                int green = (rgb >> 8) & 0x000000FF;
                int blue = (rgb) & 0x000000FF;
                if(red == 0x000000FF && green == 0x000000FF && blue == 0x000000FF) {
                    blackAndWhitePixels[i][j] = new BlackAndWhitePixel(true);
                } else if(red == 0 && green == 0 && blue == 0) {
                    blackAndWhitePixels[i][j] = new BlackAndWhitePixel(false);
                } else {
                    throw new IllegalArgumentException("Expected black and white image." + red + " " + green + " " + blue);
                }
            }
        }
        return new BlackAndWhiteImage(blackAndWhitePixels, width, height);
    }

    public BufferedImage generateRGBImage(final RGBImage rgbImage) {
        BufferedImage bufferedImage =
                new BufferedImage(rgbImage.getWidth(), rgbImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int i = 0 ; i < rgbImage.getWidth() ; ++i) {
            for(int j = 0 ; j < rgbImage.getHeight() ; ++j) {
                bufferedImage.setRGB(i, j, rgbImage.getPixelAsInt(i, j));
            }
        }
        return bufferedImage;
    }

    public BufferedImage generateBlackAndWhiteImage(final BlackAndWhiteImage blackAndWhiteImage) {
        BufferedImage bufferedImage =
                new BufferedImage(blackAndWhiteImage.getWidth(),
                        blackAndWhiteImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for(int i = 0 ; i < blackAndWhiteImage.getWidth() ; ++i) {
            for(int j = 0 ; j < blackAndWhiteImage.getHeight() ; ++j) {
                bufferedImage.setRGB(i, j, blackAndWhiteImage.getPixel(i, j).isWhite() ? 0xFFFFFFFF : 0x00000000);
            }
        }
        return bufferedImage;
    }

}
