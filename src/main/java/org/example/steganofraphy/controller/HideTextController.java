package org.example.steganofraphy.controller;

import lombok.Getter;
import lombok.Setter;
import org.example.steganofraphy.model.Converter;
import org.example.steganofraphy.model.TextHider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HideTextController {
    @Setter
    @Getter
    private File file;
    @Getter
    private File modifiedImage;
    private final TextHider textHider;
    private final Converter converter;
    private static final Integer CODED_BITS_BY_STEP = 4;
    @Getter
    private Integer lastMessageLength;

    public HideTextController() {
        this.converter = new Converter();
        this.textHider = new TextHider();
    }

    public void hideText(String textToHide) throws IOException {
        BufferedImage image = ImageIO.read(file);
        String binaryText = converter.convertTextToBinary(textToHide);
        BufferedImage modifiedImage = textHider.hideTextInImage(image, binaryText);
        saveImage(modifiedImage);
        lastMessageLength = textHider.getTextLength() / CODED_BITS_BY_STEP;
    }

    private void saveImage(BufferedImage image) throws IOException {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);
        File modifiedImageFile;
        ImageIO.write(image, extension.substring(1), modifiedImageFile = new File("output" + extension));
        modifiedImage = modifiedImageFile;
    }

}
