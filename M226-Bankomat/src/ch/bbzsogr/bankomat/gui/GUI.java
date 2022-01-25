package ch.bbzsogr.bankomat.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

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

    private boolean isUserInputting = false;
    private String userInput = "";

    private Consumer<String> actionTopLeft, actionTopRight, actionBottomLeft, actionBottomRight, actionOkay;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Bankomat - Dominik Boss und Ruben Martins");
        primaryStage.show();
    }

    public void onActionButton(Event event) {

        // Check which button was pressed
        // Trigger right event
        System.out.println("Action Button");
        System.out.println(event);
    }

    public void onNumberButton(ActionEvent event) {
        System.out.println("Number button");
        System.out.println(event);
    }

    public void onStopButton(ActionEvent event) {
        System.out.println("Stop");
    }

    public void onCorrButton(ActionEvent event) {
        System.out.println("Corr");
        if(this.isUserInputting) {
            // Remove 1 character from user input
            this.userInput = this.userInput.substring(0, this.userInput.length()-1);
        }
    }

    public void onHelpButton(ActionEvent event) throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        }
    }

    public void onOkayButton(ActionEvent event) {
        if(this.actionOkay != null) {
            this.actionOkay.accept(this.userInput);
        }
    }
}
