package com.kodilla.javafxexamples;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Random;

public class GuessNumberApp extends Application {

    private int attempts;
    private int targetNr;
    private Random random = new Random();

    private Label messageLabel;
    private Label attemptsLabel;
    private TextField inputField;

    @Override
    public void start(Stage primaryStage) {

        startGame();

        Label title = new Label("Guess number from 1 to 100!");
        title.setStyle("-fx-text-size: 22px; -fx-font-weight: bold");

        messageLabel = new Label("Enter your guess number:");
        messageLabel.setStyle("-fx-font-size: 16px");

        attemptsLabel = new Label("Total attempts: ");
        attemptsLabel.setStyle("-fx-font-size: 16px");

        inputField = new TextField();
        inputField.setMaxWidth(100);

        Button checkButton = new Button("Check");
        checkButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #2196f3");

        checkButton.setOnAction(e -> handleGuess());

        VBox vBox = new VBox(15, title, messageLabel, inputField, checkButton, attemptsLabel);
        vBox.setStyle("-fx-background-color: #f3f3f3");
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(10));

        Scene scene = new  Scene(vBox, 500, 500);
        primaryStage.setTitle("Guess Number");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleGuess() {
         String text = inputField.getText();

         if (text.isEmpty()) {
             System.out.println("Please enter a number from 1 to 100!");
             return;
         }

        try {
            int guess = Integer.parseInt(text);
            attempts++;
            attemptsLabel.setText("Attempts: " + attempts);

            if (guess < targetNr) {
                messageLabel.setText("Guess is too small!");
                messageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 20px");
            } else if (guess > targetNr) {
                messageLabel.setText("Guess is too big!");
                messageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 20px");
            } else {
                messageLabel.setText("Congratulations you guessed after: " + attempts + " attempts!");
                messageLabel.setStyle("-fx-text-fill: green; -fx-font-size: 20px");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("YOU WIN !!!");
            }

            inputField.clear();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR !!!");
        }
    }

    private void startGame() {
        targetNr = random.nextInt(100) + 1;
        attempts = 0;

        if(messageLabel != null) {
            messageLabel.setText("Type a number and press 'CLICK'");
            messageLabel.setStyle("-fx-text-fill: black; -fx-font-size: 16px");
        }

        if(attemptsLabel != null) {
            attemptsLabel.setText("Attempts: 0");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
