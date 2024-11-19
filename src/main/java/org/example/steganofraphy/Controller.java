package org.example.steganofraphy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML
    private Button hideButton;
    @FXML
    private TextField textToHide;
    @FXML
    private TextField textLengthField;
    @FXML
    private Button fileChooser;
    private TextHider textHider = new TextHider();
    private Converter converter = new Converter();
    private TextExtracter textExtracter = new TextExtracter();
    private File file;
    private ArrayList<String> lstFiles;

    @FXML
    private void hideText(ActionEvent event) throws IOException {
        BufferedImage image = ImageIO.read(file);
        String binaryText = converter.convertTextToBinary(textToHide.getText());
        BufferedImage modifiedImage = textHider.hideTextInImage(image, binaryText);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Длина текста");
        alert.setHeaderText(null);
        alert.setContentText("Длина текста = " + textHider.getTextLength() / 4);
        alert.showAndWait();
        textLengthField.setText(String.valueOf(textHider.getTextLength() / 4));
        saveImage(modifiedImage);
    }

    @FXML
    private void extractText(ActionEvent event) throws IOException {
        int textLength = Integer.parseInt(textLengthField.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сокрытый текст");
        alert.setHeaderText(null);
        alert.setContentText("Сокрытый текст = " + converter.convertBinaryToText(textExtracter.extractText(file, textLength)));
        alert.showAndWait();
    }

    @FXML
    private void chooseFile(ActionEvent event) {
        init();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Выбор файла");
        alert.setHeaderText(null);
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", lstFiles));
        file = fc.showOpenDialog(null);
        if (file != null) {
            alert.setContentText("Файл успешно выбран!");
        } else {
            alert.setContentText("Не удалось выбрать файл :(");
        }
        alert.showAndWait();
    }

    private void init() {
        lstFiles = new ArrayList<>();
        lstFiles.add("*.png");
        lstFiles.add("*.jpg");
        lstFiles.add("*.bmp");
    }

    private void saveImage(BufferedImage image) throws IOException {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex);
        ImageIO.write(image, extension.substring(1), new File("output" + extension));
    }
}
