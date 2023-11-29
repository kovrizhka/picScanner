package com.example.axalittest;

import javafx.scene.image.Image;
import javafx.embed.swing.SwingFXUtils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageConverter {

    public static byte[] getImageBytes(Image image) throws IOException {
        BufferedImage bufferedImage = toBufferedImage(image);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(bufferedImage, "png", outputStream);
        } finally {
            outputStream.close();
        }

        return outputStream.toByteArray();
    }

    private static BufferedImage toBufferedImage(Image image) {
        BufferedImage bufferedImage = new BufferedImage(
                (int) image.getWidth(),
                (int) image.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        SwingFXUtils.fromFXImage(image, bufferedImage);

        return bufferedImage;
    }
}
