package org.example.steganofraphy.controller;

import lombok.Setter;
import org.example.steganofraphy.model.Converter;
import org.example.steganofraphy.model.TextExtractor;

import java.io.File;
import java.io.IOException;

public class ExtractTextController {
    @Setter
    private File file;
    private final Converter converter;
    private final TextExtractor textExtractor;

    public ExtractTextController() {
        this.converter = new Converter();
        this.textExtractor = new TextExtractor();
    }

    public String extractText(Integer textLength) throws IOException {
        return converter.convertBinaryToText(textExtractor.extractText(file, textLength));
    }
}
