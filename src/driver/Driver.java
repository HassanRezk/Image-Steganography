package driver;

import image.BlackAndWhiteImage;
import image.RGBImage;
import image.ImageManager;

import java.io.IOException;

public class Driver {

    public static void main(final String[] args) throws IOException {
        ImageManager imageManager = new ImageManager();
        BlackAndWhiteImage message =
                imageManager.getBlackAndWhiteImageImage("C:\\Users\\Hassan\\Desktop\\Lab5NS\\m.png");
        RGBImage hostImage = imageManager.getRGBImage("C:\\Users\\Hassan\\Desktop\\Lab5NS\\o.png");
        message.display("Message");
        hostImage.display("Host image");
        RGBImage hiddenImage = hostImage.hide(message);
        hiddenImage.display("Host image including the message");
        BlackAndWhiteImage recoveredMessage = hiddenImage.reveal();
        recoveredMessage.display("Message extracted from image.");
    }

}
