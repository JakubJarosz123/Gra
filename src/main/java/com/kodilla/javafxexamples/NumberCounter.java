package com.kodilla.javafxexamples;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class NumberCounter extends Application {

    private int counter = 0;

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("0");
        label.setStyle("-fx-font-size: 26px; -fx-text-color: white");

        Button submitButton = new Button("Click Me");
        Button resetButton = new Button("Reset");

        submitButton.setPrefSize(150, 50);
        resetButton.setPrefSize(150, 50);

        submitButton.setOnAction(e -> {
           counter++;
           label.setText(String.valueOf(counter));
        });

        resetButton.setOnAction(e -> {
            counter = 0;
            label.setText("0");
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(label);
        borderPane.setLeft(submitButton);
        borderPane.setRight(resetButton);
        borderPane.setStyle("-fx-background-color: black");

        Scene scene = new Scene(borderPane, 300, 300);

        primaryStage.setTitle("Number Counter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
