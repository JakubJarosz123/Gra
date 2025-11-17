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
import java.io.*;

public class UX extends Application {

    private Board board3;
    private Board board10;
    private AI ai3;
    private AI ai10;
    public static final double cellSize3x3 = 200.0;
    public static final double cellSize10x10 = 30.0;
    private boolean gameOver3 = false;
    private boolean gameOver10 = false;
    private Scene sceneHome, scene3x3, scene10x10;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Scene Home

        board3 = new Board(3, 3);
        ai3 = new AI(board3);

        board10 = new Board(10, 5);
        ai10 = new AI(board10);

        //Image
        Image backGroundHome = new Image(getClass().getClassLoader().getResourceAsStream("tttbackground.jpg"));
        ImageView backgroundHome = new ImageView(backGroundHome);
        backgroundHome.setFitHeight(700);
        backgroundHome.setFitWidth(950);
        backgroundHome.setPreserveRatio(false);

        //Title label
        Label title = new Label("Welcome to Tic Tac Toe!");
        title.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-text-fill: white; -fx-font-size: 40");
        title.setPadding(new Insets(250, 10, 150, 10));

        //Drop shadow for title
        DropShadow titleShadow = new DropShadow();
        titleShadow.setOffsetX(3.0);
        titleShadow.setOffsetY(3.0);
        titleShadow.setColor(Color.BLACK);
        title.setEffect(titleShadow);

        //Buttons for difficulty
        Button easyButton = new Button("Easy");
        easyButton.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 22; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: white");
        easyButton.setPadding(new Insets(10));
        easyButton.setOnMouseEntered(e -> {easyButton.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 26; -fx-text-fill: black; -fx-background-color: white; -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: black");});
        easyButton.setOnMouseExited(e -> {easyButton.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 26; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: white");});
        easyButton.setOnAction(e -> {
            ai3.setDifficulty(1);
            ai10.setDifficulty(1);
        });

        Button mediumButton = new Button("Medium");
        mediumButton.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 22; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: white");
        mediumButton.setPadding(new Insets(10));
        mediumButton.setOnMouseEntered(e -> {mediumButton.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 26; -fx-text-fill: black; -fx-background-color: white; -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: black");});
        mediumButton.setOnMouseExited(e -> {mediumButton.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 26; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: white");});
        mediumButton.setOnAction(e -> {
            ai3.setDifficulty(2);
            ai10.setDifficulty(2);
        });

        Button hardButton = new Button("Hard");
        hardButton.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 22; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: white");
        hardButton.setPadding(new Insets(10));
        hardButton.setOnMouseEntered(e -> {hardButton.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 26; -fx-text-fill: black; -fx-background-color: white; -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: black");});
        hardButton.setOnMouseExited(e -> {hardButton.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 26; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: white");});
        hardButton.setOnAction(e -> {
            ai3.setDifficulty(3);
            ai10.setDifficulty(3);
        });

        Button classic = new Button("Play classic: 3x3");
        classic.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 26; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: white");
        classic.setPadding(new Insets(10));
        classic.setOnMouseEntered(e -> {classic.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 26; -fx-text-fill: black; -fx-background-color: white; -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: black");});
        classic.setOnMouseExited(e -> {classic.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 26; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: white");});
        classic.setOnAction(e -> {primaryStage.setScene(scene3x3);});

        Button alternative = new Button("Play alternative: 10x10");
        alternative.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 26; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: white");
        alternative.setPadding(new Insets(10));
        alternative.setOnMouseEntered(e -> {alternative.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 26; -fx-text-fill: black; -fx-background-color: white; -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: black");});
        alternative.setOnMouseExited(e -> {alternative.setStyle("-fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-font-size: 26; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10; -fx-border-width: 1; -fx-border-color: white");});
        alternative.setOnAction(e -> {primaryStage.setScene(scene10x10);});

        HBox difficulty = new HBox(10, easyButton, mediumButton, hardButton);
        difficulty.setAlignment(Pos.CENTER);

        VBox buttons = new VBox(30, difficulty, classic, alternative);
        buttons.setAlignment(Pos.CENTER);

        VBox mainContent = new VBox(50, title, buttons);
        mainContent.setAlignment(Pos.TOP_CENTER);
        mainContent.setPadding(new Insets(10, 20, 50, 20));
        mainContent.setStyle("-fx-background-color: rgba(0,0,0,0.4); -fx-background-radius: 20");

        StackPane home = new StackPane();
        home.getChildren().addAll(backgroundHome, mainContent);

        sceneHome = new Scene(home, 950 ,700);

        //Scene 3x3

        // Background image (reuse from sceneHome)
        Image backGround3x3 = new Image(getClass().getClassLoader().getResourceAsStream("tttbackground.jpg"));
        ImageView background3x3 = new ImageView(backGround3x3);
        background3x3.setFitHeight(700);
        background3x3.setFitWidth(950);
        background3x3.setPreserveRatio(false);

        GridPane gridPane3x3 = new GridPane();
        gridPane3x3.setPadding(new Insets(20));
        gridPane3x3.setAlignment(Pos.CENTER);
        gridPane3x3.setPrefSize(cellSize3x3 * 3, cellSize3x3 * 3);

        TextArea statistics3x3 = new TextArea();
        statistics3x3.setEditable(false);
        statistics3x3.setPrefSize(300, 300);
        statistics3x3.setStyle("-fx-control-inner-background: #00001c; -fx-font-size: 20px; -fx-text-fill: white");

        TextField[][] cells3x3 = new TextField[board3.getSize()][board3.getSize()];
        for (int row = 0; row < board3.getSize(); row++) {
            for (int column = 0; column < board3.getSize(); column++) {
                TextField cell = new TextField();
                cell.setPrefSize(cellSize3x3, cellSize3x3);
                cell.setStyle("-fx-font-size: 70px; -fx-text-fill: white; -fx-background-color: #00001c; -fx-border-style: solid; -fx-border-width: 2; -fx-border-color: white");
                cell.setAlignment(Pos.CENTER);
                cell.setEditable(false);
                cells3x3[row][column] = cell;
                gridPane3x3.add(cell, column, row);
            }
        }

        gridPane3x3.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (gameOver3) return;

            Point2D localPoint = gridPane3x3.sceneToLocal(e.getSceneX(), e.getSceneY());
            double x = localPoint.getX();
            double y = localPoint.getY();

            int col = (int) (x / cellSize3x3);
            int row = (int) (y / cellSize3x3);

            if (row < board3.getSize() && col <  board3.getSize()) {
                TextField cell = cells3x3[row][col];
                if (cell.getText().isEmpty()) {
                    //Player move
                    cell.setText("X");
                    cell.setStyle("-fx-font-size: 80px; -fx-text-fill: red; -fx-border-style: solid; -fx-border-width: 2; -fx-background-color: #00001c; -fx-border-color: white");
                    board3.setValue(row, col, "X");

                    //Checks winner
                    String winner = board3.whoIsWinner();
                    if (!winner.equals("")) {
                        board3.registerWinner(winner);
                        gameOver3 = true;
                    } else if (board3.isDraw()) {
                        gameOver3 = true;
                    }
                    statistics3x3.setText(board3.stats());

                    //AI move (if game not over)
                    if (!gameOver3) {
                        int[] aiMove = ai3.getAiMove();
                        if (aiMove != null) {
                            int r = aiMove[0];
                            int c = aiMove[1];
                            TextField aiCell = cells3x3[r][c];
                            aiCell.setText("O");
                            aiCell.setStyle("-fx-font-size: 80px; -fx-text-fill: green; -fx-border-style: solid; -fx-border-width: 2; -fx-background-color: #00001c; -fx-border-color: white");
                            board3.setValue(r, c, "O");
                        }

                        //Check winner after ai move
                        String aiWinner = board3.whoIsWinner();
                        if (!aiWinner.equals("")) {
                            board3.registerWinner(aiWinner);
                            gameOver3 = true;
                        } else if (board3.isDraw()) {
                        }
                        statistics3x3.setText(board3.stats());
                    }
                }
            }
        });

        Button homeButton3x3 = new Button("Home");
        homeButton3x3.setPrefSize(150, 70);
        homeButton3x3.setStyle("-fx-background-color: #00001c; -fx-text-fill: white; -fx-font-size: 22px");
        homeButton3x3.setOnAction(e -> {
            statistics3x3.setText("");
            primaryStage.setScene(sceneHome);
        });

        Button newGameButton3x3 = new Button("New Game");
        newGameButton3x3.setPrefSize(150, 70);
        newGameButton3x3.setStyle("-fx-background-color: #00001c; -fx-text-fill: white; -fx-font-size: 22px");
        newGameButton3x3.setOnAction(e -> {
            gameOver3 = false;
            board3.resetBoard();

            for (int r = 0; r < board3.getSize(); r++) {
                for (int c = 0; c < board3.getSize(); c++) {
                    cells3x3[r][c].clear();
                    cells3x3[r][c].setEditable(false);
                    cells3x3[r][c].setStyle("-fx-font-size: 80px; -fx-text-fill: white; -fx-background-color: #00001c; -fx-border-style: solid; -fx-border-width: 2; -fx-border-color: white");
                }
            }

            gridPane3x3.setDisable(false);
            statistics3x3.setText(board3.stats());

            DropShadow glow = new DropShadow();
            glow.setColor(Color.GOLD);
            glow.setRadius(30);
            newGameButton3x3.setEffect(glow);

            PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
            pause.setOnFinished(ev -> newGameButton3x3.setEffect(null));
            pause.play();
        });

        Button saveGameButton3x3 = new Button("Save Game");
        saveGameButton3x3.setPrefSize(150, 70);
        saveGameButton3x3.setStyle("-fx-background-color: #00001c; -fx-text-fill: white; -fx-font-size: 22px");
        saveGameButton3x3.setOnAction(e -> {
            saveGame(board3, "ranking3.list");

            DropShadow glow = new DropShadow();
            glow.setColor(Color.GOLD);
            glow.setRadius(30);
            saveGameButton3x3.setEffect(glow);

            PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
            pause.setOnFinished(ev -> saveGameButton3x3.setEffect(null));
            pause.play();
        });

        Button loadGameButton3x3 = new Button("Load Game");
        loadGameButton3x3.setPrefSize(150, 70);
        loadGameButton3x3.setStyle("-fx-background-color: #00001c; -fx-text-fill: white; -fx-font-size: 22px");
        loadGameButton3x3.setOnAction(e -> {
            Board loaded = loadGame("ranking3.list");
            if (loaded != null) {
                board3 = loaded;
                ai3 = new AI(board3);
                gameOver3 = false;

                for (int row = 0; row < board3.getSize(); row++) {
                    for (int col = 0; col < board3.getSize(); col++) {
                        cells3x3[row][col].setText(board3.getValue(row, col));

                        String value = board3.getValue(row, col);
                        if (value.equals("X")) {
                            cells3x3[row][col].setStyle("-fx-font-size: 80px; -fx-text-fill: red; -fx-border-style: solid; -fx-border-width: 2; -fx-background-color: #00001c; -fx-border-color: white");
                        } else if (value.equals("O")) {
                            cells3x3[row][col].setStyle("-fx-font-size: 80px; -fx-text-fill: green; -fx-border-style: solid; -fx-border-width: 2; -fx-background-color: #00001c; -fx-border-color: white");
                        }
                    }
                }
                statistics3x3.setText(board3.stats());

                DropShadow glow = new DropShadow();
                glow.setColor(Color.GOLD);
                glow.setRadius(30);
                loadGameButton3x3.setEffect(glow);

                PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
                pause.setOnFinished(ev -> loadGameButton3x3.setEffect(null));
                pause.play();
            }

        });

        VBox vbox3x3 = new VBox(statistics3x3, homeButton3x3, newGameButton3x3, saveGameButton3x3, loadGameButton3x3);
        vbox3x3.setPadding(new Insets(30));
        vbox3x3.setAlignment(Pos.TOP_CENTER);
        vbox3x3.setSpacing(60);

        //Cały content
        HBox content3x3 = new HBox(gridPane3x3, vbox3x3);
        content3x3.setAlignment(Pos.CENTER);

        //StackPane jako layout
        StackPane root3x3 = new StackPane();
        root3x3.getChildren().addAll(background3x3, content3x3);

        scene3x3 = new Scene(root3x3, 950, 700);

        //Scene 10x10

        Image backGround10x10 = new Image(getClass().getClassLoader().getResourceAsStream("tttbackground.jpg"));
        ImageView background10x10 = new ImageView(backGround10x10);
        background10x10.setFitHeight(700);
        background10x10.setFitWidth(950);
        background10x10.setPreserveRatio(false);

        GridPane gridPane10x10 = new GridPane();
        gridPane10x10.setPadding(new Insets(20));
        gridPane10x10.setAlignment(Pos.CENTER);
        gridPane10x10.setPrefSize(600, 600);

        TextArea statistics10x10 = new TextArea();
        statistics10x10.setEditable(false);
        statistics10x10.setPrefSize(200, 300);
        statistics10x10.setStyle("-fx-control-inner-background: #00001c; -fx-font-size: 20px; -fx-text-fill: white");

        TextField[][] cells10x10 = new TextField[board10.getSize()][board10.getSize()];
        for (int row = 0; row < board10.getSize(); row++) {
            for (int column = 0; column < board10.getSize(); column++) {
                TextField cell = new TextField();
                cell.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-background-color: #00001c; -fx-border-style: solid; -fx-border-width: 2; -fx-border-color: white");
                cell.setAlignment(Pos.CENTER);
                cell.prefWidthProperty().bind(gridPane10x10.widthProperty().divide(10));
                cell.prefHeightProperty().bind(gridPane10x10.heightProperty().divide(10));
                cell.setEditable(false);
                cells10x10[row][column] = cell;
                gridPane10x10.add(cell, column, row);
            }
        }

        gridPane10x10.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
            if (gameOver10) return;

            double cellWidth = gridPane10x10.getWidth() / 10;
            double cellHeight = gridPane10x10.getHeight() / 10;

            double x = e.getX();
            double y = e.getY();

            int col = (int) (x / cellWidth);
            int row = (int) (y / cellHeight);

            if (row < board10.getSize() && col < board10.getSize()) {
                TextField cell = cells10x10[row][col];
                if (cell.getText().isEmpty()) {
                    //Player move
                    cell.setText("X");
                    cell.setStyle("-fx-font-size: 20px; -fx-text-fill: red; -fx-border-style: solid; -fx-border-width: 2; -fx-background-color: #00001c; -fx-border-color: white");
                    board10.setValue(row, col, "X");

                    //Checks winner
                    String winner = board10.whoIsWinner();
                    if (!winner.equals("")) {
                        board10.registerWinner(winner);
                        gameOver10 = true;
                    } else if (board10.isDraw()) {
                        gameOver10 = true;
                    }
                    statistics10x10.setText(board10.stats());

                    //AI move (if game not over)
                    if (!gameOver10) {
                        int[] aiMove = ai10.getAiMove();
                        if (aiMove != null) {
                            int r = aiMove[0];
                            int c = aiMove[1];
                            TextField aiCell = cells10x10[r][c];
                            aiCell.setText("O");
                            aiCell.setStyle("-fx-font-size: 20px; -fx-text-fill: green; -fx-border-style: solid; -fx-border-width: 2; -fx-background-color: #00001c; -fx-border-color: white");
                            board10.setValue(r, c, "O");
                        }

                        //Check winner after ai move
                        String aiWinner = board10.whoIsWinner();
                        if (!aiWinner.equals("")) {
                            board10.registerWinner(aiWinner);
                            gameOver10 = true;
                        } else if (board10.isDraw()) {
                        }
                        statistics10x10.setText(board10.stats());
                    }
                }
            }
        });

        Button homeButton10x10 = new Button("Home");
        homeButton10x10.setPrefSize(150, 70);
        homeButton10x10.setStyle("-fx-background-color: #00001c; -fx-text-fill: white; -fx-font-size: 22px");
        homeButton10x10.setOnAction(e -> {
            statistics10x10.setText("");
            primaryStage.setScene(sceneHome);
        });

        Button newGameButton10x10 = new Button("New Game");
        newGameButton10x10.setPrefSize(150, 70);
        newGameButton10x10.setStyle("-fx-background-color: #00001c; -fx-text-fill: white; -fx-font-size: 22px");
        newGameButton10x10.setOnAction(e -> {
            gameOver10 = false;
            board10.resetBoard();

            for (int r = 0; r < 10; r++) {
                for (int c = 0; c < 10; c++) {
                    cells10x10[r][c].clear();
                    cells10x10[r][c].setEditable(false);
                    cells10x10[r][c].setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-background-color: #00001c; -fx-border-style: solid; -fx-border-width: 2; -fx-border-color: white");
                }
            }

            gridPane10x10.setDisable(false);
            statistics10x10.setText(board10.stats());

            DropShadow glow = new DropShadow();
            glow.setColor(Color.GOLD);
            glow.setRadius(30);
            newGameButton10x10.setEffect(glow);

            PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
            pause.setOnFinished(ev -> newGameButton10x10.setEffect(null));
            pause.play();
        });

        Button saveGameButton10x10 = new Button("Save Game");
        saveGameButton10x10.setPrefSize(150, 70);
        saveGameButton10x10.setStyle("-fx-background-color: #00001c; -fx-text-fill: white; -fx-font-size: 22px");
        saveGameButton10x10.setOnAction(e -> {
            saveGame(board10, "ranking10.list");

            DropShadow glow = new DropShadow();
            glow.setColor(Color.GOLD);
            glow.setRadius(30);
            saveGameButton10x10.setEffect(glow);

            PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
            pause.setOnFinished(ev -> saveGameButton10x10.setEffect(null));
            pause.play();
        });

        Button loadGameButton10x10 = new Button("Load Game");
        loadGameButton10x10.setPrefSize(150, 70);
        loadGameButton10x10.setStyle("-fx-background-color: #00001c; -fx-text-fill: white; -fx-font-size: 22px");
        loadGameButton10x10.setOnAction(e -> {
            Board loaded = loadGame("ranking10.list");
            if (loaded != null) {
                board10 = loaded;
                ai10 = new AI(board10);
                gameOver10 = false;

                for (int row = 0; row < board10.getSize(); row++) {
                    for (int col = 0; col < board10.getSize(); col++) {
                        cells10x10[row][col].setText(board10.getValue(row, col));

                        String value = board10.getValue(row, col);
                        if (value.equals("X")) {
                            cells10x10[row][col].setStyle("-fx-font-size: 80px; -fx-text-fill: red; -fx-border-style: solid; -fx-border-width: 2; -fx-background-color: #00001c; -fx-border-color: white");
                        } else if (value.equals("O")) {
                            cells10x10[row][col].setStyle("-fx-font-size: 80px; -fx-text-fill: green; -fx-border-style: solid; -fx-border-width: 2; -fx-background-color: #00001c; -fx-border-color: white");
                        }
                    }
                }
                statistics10x10.setText(board10.stats());

                DropShadow glow = new DropShadow();
                glow.setColor(Color.GOLD);
                glow.setRadius(30);
                loadGameButton10x10.setEffect(glow);

                PauseTransition pause = new PauseTransition(Duration.seconds(0.3));
                pause.setOnFinished(ev -> loadGameButton10x10.setEffect(null));
                pause.play();
            }
        });

        VBox vbox10x10 = new VBox(statistics10x10, homeButton10x10, newGameButton10x10, saveGameButton10x10, loadGameButton10x10);
        vbox10x10.setPadding(new Insets(30));
        vbox10x10.setAlignment(Pos.TOP_CENTER);
        vbox10x10.setSpacing(40);

        HBox content10x10 = new HBox(gridPane10x10, vbox10x10);
        content10x10.setAlignment(Pos.CENTER);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(background10x10, content10x10);

        scene10x10 = new Scene(layout, 950, 700);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(sceneHome);
        primaryStage.show();
    }

    public void saveGame(Board board, String name) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name))) {
            oos.writeObject(board);
            System.out.println("Saved game!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Board loadGame(String name) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name))) {
            Board loaded = (Board) ois.readObject();
            System.out.println("Loaded game!");
            return loaded;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}