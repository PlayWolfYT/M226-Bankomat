package ch.bbzsogr.bankomat.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Consumer;

public class GUI extends Application {

    private GUIController controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Bankomat - Dominik Boss und Ruben Martins");
        primaryStage.show();

        this.controller = loader.getController();
        this.controller.setup();
    }

    public GUIController getController() {
        return controller;
    }
}
