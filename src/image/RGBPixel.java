package image;

public class RGBPixel {

    private final int red;
    private final int green;
    private final int blue;

    public RGBPixel(final RGBPixel rgbPixel) {
        this.red = rgbPixel.getRed();
        this.green = rgbPixel.getGreen();
        this.blue = rgbPixel.getBlue();
    }

    public RGBPixel(int red, int green, int blue) {
        if(red < 0 || green < 0 || blue < 0) {
            throw new IllegalArgumentException("Colors should not be negative.");
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

}
