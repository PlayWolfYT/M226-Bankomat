package ch.bbzsogr.bankomat.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    // Number Buttons
    @FXML
    Button btnNbr0, btnNbr1, btnNbr2, btnNbr3, btnNbr4, btnNbr5, btnNbr6, btnNbr7, btnNbr8, btnNbr9;

    // Number Action Buttons
    @FXML
    Button btnStop, btnCorr, btnHelp, btnOk;


    // Screen Area
    @FXML
    TextArea screen;

    // Screen Buttons
    @FXML
    Button btnTopLft, btnTopRgt, btnBotLft, btnBotRgt;

    // Screen Labels for Buttons
    @FXML
    Label labelTopLft, labelTopRgt, labelBotLft, labelBotRgt;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Bankomat - Dominik Boss und Ruben Martins");
        primaryStage.show();
    }
}
