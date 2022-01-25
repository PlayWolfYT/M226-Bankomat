package ch.bbzsogr.bankomat.gui;

import ch.bbzsogr.bankomat.BankCard;
import ch.bbzsogr.bankomat.remote.BankSystem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class GUIController {

    public static final BankSystem BANK_SYSTEM = new BankSystem();
    public static final File CARD_FOLDER = new File("./resources/cards");


    // Number Buttons
    @FXML
    private Button btnNbr0, btnNbr1, btnNbr2, btnNbr3, btnNbr4, btnNbr5, btnNbr6, btnNbr7, btnNbr8, btnNbr9;

    // Number Action Buttons
    @FXML
    private Button btnStop, btnCorr, btnHelp, btnOk;

    // Screen Area
    @FXML
    private Label screen;

    // Screen Buttons
    @FXML
    private Button btnTopLft, btnTopRgt, btnBotLft, btnBotRgt;

    // Screen Labels for Buttons
    @FXML
    private Label labelTopLft, labelTopRgt, labelBotLft, labelBotRgt;

    // Dropdown for Cardselection
    @FXML
    private ComboBox<String> comboBoxCard;

    private boolean isUserInputting = true;
    private String userInput = "";
    private boolean displayUserInput = false;
    private boolean displayUserInputCensored = false;
    private Consumer<String> actionTopLeft, actionTopRight, actionBottomLeft, actionBottomRight, actionOkay;

    private BankCard bankCard = null;

    public void setup() {
        // TODO: Update screen
        prepareCardSelection();
    }

    public void setOkayAction(Consumer<String> dataAction) {
        actionOkay = dataAction;
    }

    public void setTopLeftAction(Consumer<String> actionTopLeft, String displayText) {
        this.actionTopLeft = actionTopLeft;
        this.labelTopLft.setText(displayText);
        this.labelTopLft.setVisible(!displayText.isEmpty());
    }
    public void setTopRightAction(Consumer<String> actionTopRight, String displayText) {
        this.actionTopRight = actionTopRight;
        this.labelTopRgt.setText(displayText);
        this.labelTopRgt.setVisible(!displayText.isEmpty());
    }
    public void setBottomLeftAction(Consumer<String> actionBottomLeft, String displayText) {
        this.actionBottomLeft = actionBottomLeft;
        this.labelBotLft.setText(displayText);
        this.labelBotLft.setVisible(!displayText.isEmpty());
    }
    public void setBottomRightAction(Consumer<String> actionBottomRight, String displayText) {
        this.actionBottomRight = actionBottomRight;
        this.labelBotRgt.setText(displayText);
        this.labelBotRgt.setVisible(displayText.isEmpty());
    }

    public void onActionButton(Event event) {
        Button btn = (Button) event.getTarget();
        if(btn.equals(this.btnTopLft)) {
            if(this.actionTopLeft != null) this.actionTopLeft.accept(this.userInput);
        } else if(btn.equals(this.btnTopRgt)) {
            if(this.actionTopRight != null) this.actionTopRight.accept(this.userInput);
        } else if(btn.equals(this.btnBotLft)) {
            if(this.actionBottomLeft != null) this.actionBottomLeft.accept(this.userInput);
        } else if(btn.equals(this.btnBotRgt)) {
            if(this.actionBottomRight != null) this.actionBottomRight.accept(this.userInput);
        } else {
            System.err.println("An illegal button got pressed");
            System.exit(-1);
        }
    }

    public void onNumberButton(ActionEvent event) {
        if(this.isUserInputting) {
            this.userInput += ((Button) event.getTarget()).getText();
            if(this.displayUserInput) {
                this.displayText(this.screen.getText() + (this.displayUserInputCensored ? '*' : ((Button) event.getTarget()).getText()));
            }
        }
    }

    public void onStopButton(ActionEvent event) {
        this.prepareCardSelection();
    }

    public void onCorrButton(ActionEvent event) {
        if(this.isUserInputting) {
            // Check if user even inputted something
            if(this.userInput.isEmpty()) return;

            // Remove 1 character from user input
            this.userInput = this.userInput.substring(0, Math.max(this.userInput.length()-1, 0));

            if(this.displayUserInput) {
                this.displayText(this.screen.getText().substring(0, this.screen.getText().length()-1));
            }
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

    private void setComboBoxCardValues(ArrayList<String> values) {
        if(values.size() <= 0) {
            this.comboBoxCard.setItems(null);
            this.comboBoxCard.setPlaceholder(new Label("No Cards Available"));
            this.comboBoxCard.setDisable(true);
            this.comboBoxCard.getSelectionModel().select(null);
        } else this.comboBoxCard.setItems(FXCollections.observableArrayList(values));
    }

    public void displayText(String text) {
        this.screen.setText(text);
    }

    public void setUserInput(boolean userInputting) {
        this.isUserInputting = userInputting;
        this.userInput = "";

        // Make these false when allowing user input
        // (Can be manually changed by code afterwards)
        setUserInputShown(false);
        setUserInputCensored(false);
    }

    public void setUserInputShown(boolean userInputShown) {
        this.displayUserInput = userInputShown;
    }

    public void setUserInputCensored(boolean userInputCensored) {
        this.displayUserInputCensored = userInputCensored;
    }

    public static ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();

        if(!CARD_FOLDER.exists()) CARD_FOLDER.mkdirs();

        // Filter cards that have a .lock file
        for(File file : Objects.requireNonNull(CARD_FOLDER.listFiles())) {
            if(file.getName().endsWith(".lock")) cards.remove(file.getName().replace(".lock", ""));
            else cards.add(file.getName());
        }

        return cards;
    }


    private void prepareCardSelection() {
        this.bankCard = null;
        this.displayText("Please enter card and press OK");
        this.setOkayAction(null);
        this.setTopLeftAction(null, "");
        this.setTopRightAction(null, "");
        this.setBottomLeftAction(null, "");
        this.setBottomRightAction(null, "");

        ArrayList<String> cards = getCards();
        this.setComboBoxCardValues(cards);

        this.comboBoxCard.setVisible(true);
        this.comboBoxCard.setDisable(false);

        // Stop here if we don't have any cards
        if(cards.size() <= 0) return;

        this.setOkayAction((ignored) -> {
            String card = this.comboBoxCard.getSelectionModel().getSelectedItem();

            if(card != null) {
                this.comboBoxCard.setVisible(false);
                this.comboBoxCard.setDisable(true);
                this.preparePinEntry(card);
            }
        });
    }

    private void preparePinEntry(String card) {
        try {
            this.bankCard = new BankCard(new File(CARD_FOLDER.getAbsolutePath(), card));
            this.displayText("Please enter card pin: \n");
            this.setUserInput(true);
            this.setUserInputShown(true);
            this.setUserInputCensored(true);

            this.setOkayAction((code) -> {
                if(this.bankCard.validatePin(code)) {
                    // Card is valid. Load card details
                    this.setUserInput(false);
                    this.prepareCardInformationDisplay();
                } else {
                    // Check if card got locked
                    if(new File(this.bankCard.getCardFile().getAbsolutePath() + ".lock").exists()) {
                        this.displayText("Card consumed. Entered pin incorrectly 3 times. Please contact your bank.");
                        this.setUserInput(false);
                    } else {
                        this.displayText("Incorrect pin. You have " + this.bankCard.getRemainingAttempts() + " attempt(s) left.\nPlease enter card pin: \n");
                        this.setUserInput(true);
                        this.setUserInputShown(true);
                        this.setUserInputCensored(true);
                    }
                }
            });
        } catch (IOException e) {
            this.displayText("Card could not get read. Please try again.\nError: " + e.getMessage());
        }
    }

    private void prepareCardInformationDisplay() {
        String display = "CARD INFORMATION\n\n";
        display += "Surname: " + this.bankCard.getCardSurname() + "\n";
        display += "Name: " + this.bankCard.getCardName() + "\n";
        display += "Account Designation: " + this.bankCard.getCardAccountDesignation() + "\n";
        display += "IBAN: " + this.bankCard.getCardIBAN() + "\n";
        display += "Bank Name: " + this.bankCard.getCardBankName() + "\n";
        display += "Card Number: " + this.bankCard.getCardNumber() + "\n";
        display += "Valid Until: " + this.bankCard.getCardExpirationMonth() + "/" + this.bankCard.getCardExpirationYear() + "\n";

        this.displayText(display);

        this.setTopLeftAction(null, "Withdraw Money");
    }
}
