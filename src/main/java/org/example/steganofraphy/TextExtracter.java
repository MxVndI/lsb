package org.example.steganofraphy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TextExtracter {

    public String extractText(File file, int lastInfoLength) throws IOException {
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();
        int byteIndex = 0;
        StringBuilder answer = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (byteIndex < lastInfoLength) {
                    long pixel = image.getRGB(x, y);
                    long red = (pixel >> 16) & 0xFF;
                    String infoInRed = String.format("%8s", Long.toBinaryString(red)).replace(' ', '0').substring(6, 8);
                    long green = (pixel >> 8) & 0xFF;
                    String infoInGreen = String.format("%8s", Long.toBinaryString(green)).replace(' ', '0').substring(7, 8);
                    long blue = pixel & 0xFF;
                    String infoInBlue = String.format("%8s", Long.toBinaryString(blue)).replace(' ', '0').substring(7, 8);
                    answer.append(infoInRed).append(infoInGreen).append(infoInBlue);
                    byteIndex += 1;
                    if (byteIndex % 4 == 0 && byteIndex < lastInfoLength) {
                        answer.append(" ");
                    }
                } else {
                    break;
                }
            }
            if (byteIndex >= lastInfoLength) {
                break;
            }
        }
        return answer.toString().trim();
    }
}
