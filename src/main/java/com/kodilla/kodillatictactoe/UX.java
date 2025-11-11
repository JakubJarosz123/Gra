package com.kodilla.kodillatictactoe;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UX extends Application {

    private Board board = new Board(3);
    private AI ai = new AI(board);
    public static final double CELL_SIZE = 200.0;
    private boolean gameOver = false;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Label title = new Label("Tic Tac Toe");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPrefSize(CELL_SIZE * 3, CELL_SIZE * 3);

        TextArea statistics = new TextArea();
        statistics.setEditable(false);
        statistics.setPrefSize(300, 300);
        statistics.setStyle("-fx-control-inner-background: rgb(137,207,240); -fx-font-size: 20px; -fx-text-fill: black");

        TextField[][] cells = new TextField[board.getSize()][board.getSize()];
        for (int row = 0; row < board.getSize(); row++) {
            for (int column = 0; column < board.getSize(); column++) {
                TextField cell = new TextField();
                cell.setPrefSize(CELL_SIZE, CELL_SIZE);
                cell.setStyle("-fx-font-size: 70px; -fx-text-fill: white; -fx-background-color: rgb(137,207,240); -fx-border-style: solid; -fx-border-width: 2");
                cell.setAlignment(Pos.CENTER);
                cell.setEditable(false);
                cells[row][column] = cell;
                gridPane.add(cell, column, row);
            }
        }

        gridPane.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (gameOver) return;

            Point2D localPoint = gridPane.sceneToLocal(e.getSceneX(), e.getSceneY());
            double x = localPoint.getX();
            double y = localPoint.getY();

            int col = (int) (x / CELL_SIZE);
            int row = (int) (y / CELL_SIZE);

            if (row < board.getSize() && col <  board.getSize()) {
                TextField cell = cells[row][col];
                if (cell.getText().isEmpty()) {
                    //Player move
                    cell.setText("X");
                    cell.setStyle("-fx-font-size: 80px; -fx-text-fill: red; -fx-border-style: solid; -fx-border-width: 2; -fx-background-color: rgb(137,207,240)");
                    board.setValue(row, col, "X");

                    //Checks winner
                    String winner = board.whoIsWinner();
                    if (!winner.equals("")) {
                        board.registerWinner(winner);
                        gameOver = true;
                    } else if (board.isDraw()) {
                        gameOver = true;
                    }
                    statistics.setText(board.stats());

                    //AI move (if game not over)
                    if (!gameOver) {
                        int[] aiMove = ai.getAiMove();
                        if (aiMove != null) {
                            int r = aiMove[0];
                            int c = aiMove[1];
                            TextField aiCell = cells[r][c];
                            aiCell.setText("O");
                            aiCell.setStyle("-fx-font-size: 80px; -fx-text-fill: green; -fx-border-style: solid; -fx-border-width: 2; -fx-background-color: rgb(137,207,240)");
                            board.setValue(r, c, "O");
                        }

                        //Check winner after ai move
                        String aiWinner = board.whoIsWinner();
                        if (!aiWinner.equals("") || aiWinner.isEmpty()) {
                            board.registerWinner(aiWinner);
                        } else if (board.isDraw()) {
                        }
                        statistics.setText(board.stats());
                    }
                }
            }
        });

        Button newGameButton = new Button("New Game");
        newGameButton.setPrefSize(150, 70);
        newGameButton.setStyle("-fx-background-color: rgb(137,207,240); -fx-text-fill: black; -fx-font-size: 22px");
        newGameButton.setOnAction(e -> {
            for (int r = 0; r < board.getSize(); r++) {
                for (int c = 0; c < board.getSize(); c++) {
                    TextField cell = cells[r][c];
                    cell.clear();
                    cell.setEditable(false);
                    cell.setStyle("-fx-font-size: 80px; -fx-text-fill: white; -fx-background-color: rgb(137,207,240); -fx-border-style: solid; -fx-border-width: 2");
                }
            }

            gameOver = false;
            board.resetBoard();
            gridPane.setDisable(false);
            statistics.setText(board.stats());

            DropShadow glow = new DropShadow();
            glow.setColor(Color.GOLD);
            glow.setRadius(30);
            newGameButton.setEffect(glow);

            PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
            pause.setOnFinished(ev -> newGameButton.setEffect(null));
            pause.play();
        });

        Image logo = new Image(getClass().getResourceAsStream("/logo.jpg"));
        ImageView imageView = new ImageView();
        imageView.setImage(logo);
        imageView.setFitHeight(70);
        imageView.setPreserveRatio(true);

        VBox vbox = new VBox(statistics, newGameButton, imageView);
        vbox.setPadding(new Insets(50));
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(60);

        HBox root = new HBox(gridPane, vbox);
        root.setStyle("-fx-background-color: blue");

        Scene scene = new Scene(root, 950, 700);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

