package org.example.steganofraphy.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.Setter;
import org.example.steganofraphy.controller.ExtractTextController;
import org.example.steganofraphy.controller.HideTextController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class View {
    @FXML
    private Button hideButton;
    @FXML
    private TextField textToHide;
    @FXML
    private TextField textLengthField;
    @FXML
    private Button fileChooser;
    private final ArrayList<String> lstFiles = new ArrayList<>();
    @Setter
    private HideTextController hideTextController = new HideTextController();
    @Setter
    private ExtractTextController extractTextController = new ExtractTextController();

    @FXML
    private void hideText(ActionEvent event) throws IOException {
        hideTextController.hideText(textToHide.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Длина текста");
        alert.setHeaderText(null);
        alert.setContentText("Длина текста = " + hideTextController.getLastMessageLength());
        alert.showAndWait();
        textLengthField.setText(String.valueOf(hideTextController.getLastMessageLength()));
        showImage(hideTextController.getFile(), "Исходное изображение");
        showImage(hideTextController.getModifiedImage(), "Модифицированное изображение");
    }

    @FXML
    private void extractText(ActionEvent event) throws IOException {
        Integer textLength = Integer.parseInt(textLengthField.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сокрытый текст");
        alert.setHeaderText(null);
        alert.setContentText("Сокрытый текст = " + extractTextController.extractText(textLength));
        alert.showAndWait();
    }

    @FXML
    private void chooseFile(ActionEvent event) {
        setAvailableFileExtensions();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Выбор файла");
        alert.setHeaderText(null);
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", lstFiles));
        File file = fc.showOpenDialog(null);
        if (file != null) {
            alert.setContentText("Файл успешно выбран!");
        } else {
            alert.setContentText("Не удалось выбрать файл :(");
        }
        hideTextController.setFile(file);
        extractTextController.setFile(file);
        alert.showAndWait();
    }

    private void setAvailableFileExtensions() {
        lstFiles.add("*.png");
        lstFiles.add("*.jpg");
        lstFiles.add("*.bmp");
    }

    private void showImage(File file, String title) {
        Stage imageStage = new Stage();
        Image image = new Image(file.toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(600);
        Button closeButton = new Button("Закрыть");
        closeButton.setOnAction(e -> imageStage.close());
        VBox vbox = new VBox(imageView, closeButton);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 1000, 600);
        imageStage.setScene(scene);
        imageStage.setTitle(title);
        imageStage.show();
    }
}
