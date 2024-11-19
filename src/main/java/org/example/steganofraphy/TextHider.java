package org.example.steganofraphy;

import lombok.Getter;

import java.awt.image.BufferedImage;

public class TextHider {
    @Getter
    private int textLength;

    public BufferedImage hideTextInImage(BufferedImage image, String text) {
        int width = image.getWidth();
        int height = image.getHeight();
        int textIndex = 0;
        text = text.replaceAll(" ", "");
        textLength = text.length();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (textIndex < text.length()) {
                    String bytes = text.substring(textIndex, Math.min(textIndex + 4, text.length()));
                    int pixel = image.getRGB(x, y);
                    int alpha = (pixel >> 24) & 0xFF;
                    int redOld = (pixel >> 16) & 0xFF;
                    int greenOld = (pixel >> 8) & 0xFF;
                    int blueOld = pixel & 0xFF;
                    int newRed = (redOld & 0xFC) | (Integer.parseInt(bytes.substring(0, 2), 2) & 0x03);
                    int newGreen = (greenOld & 0xFE) | (Integer.parseInt(bytes.substring(2, 3), 2) & 0x01);
                    int newBlue = (blueOld & 0xFE) | (Integer.parseInt(bytes.substring(3, 4), 2) & 0x01);
                    int newPixel = (alpha << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
                    image.setRGB(x, y, newPixel);
                    textIndex += 4;
                }
            }
        }
        return image;
    }
}
