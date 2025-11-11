package com.kodilla.kodillatictactoe;

public class Board10x10 {
    private String[][] figures;
    private int playerXWins = 0;
    private int computerOWins = 0;
    private int winLength;

    public Board10x10(int size, int winLength) {
        this.winLength = winLength;
        figures = new String[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                figures[row][col] = "";
            }
        }
    }

    public int getSize() {
        return figures.length;
    }

    public void setValue(int x, int y, String value) {
        figures[x][y] = value;
    }

    public String getValue(int x, int y) {
        return figures[x][y];
    }

    public void resetBoard() {
        for (int row = 0; row < figures.length; row++) {
            for (int col = 0; col < figures[row].length; col++) {
                figures[row][col] = "";
            }
        }
    }

    public String whoIsWinner() {
        int size = figures.length;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                String current = figures[row][col];
                if (current.isEmpty()) continue;

                //Horizontal Check
                if (col + winLength <= size) {
                    boolean winner = true;
                    for (int i = 0; i < winLength; i++) {
                        if (!figures[row][col + i].equals(current)) {
                            winner = false;
                            break;
                        }
                    }
                    if (winner) {
                        return current;
                    }
                }

                //Vertical Check
                if (row + winLength <= size) {
                    boolean winner = true;
                    for (int i = 0; i < winLength; i++) {
                        if (!figures[row + i][col].equals(current)) {
                            winner = false;
                            break;
                        }
                    }
                    if (winner) {
                        return current;
                    }
                }

                //Diagonal top-left to bottom-right
                if (row + winLength <= size && col + winLength <= size) {
                    boolean winner = true;
                    for (int i = 0; i < winLength; i++) {
                        if (!figures[row + i][col + i].equals(current)) {
                            winner = false;
                            break;
                        }
                    }
                    if (winner) {
                        return current;
                    }
                }

                //Diagonal top-right to bottom-left
                if (row + winLength <= size && col - winLength + 1 >= 0) {
                    boolean winner = true;
                    for (int i = 0; i < winLength; i++) {
                        if (!figures[row + i][col - i].equals(current)) {
                            winner = false;
                            break;
                        }
                    }
                    if (winner) {
                        return current;
                    }
                }
            }
        }
        return "";
    }

    public void registerWinner(String winner) {
        if (winner.equals("X")) {
            playerXWins ++;
        } else if (winner.equals("O")) {
            computerOWins ++;
        }
    }

    public boolean isDraw() {
        if (!whoIsWinner().equals("")) {
            return true;
        }

        for (int row = 0; row < figures.length; row++) {
            for (int col = 0; col < figures.length; col++) {
                if (figures[row][col].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public String stats() {
        String winner = whoIsWinner();

        String result;
        if (!winner.equals("")) {
            result = "Winner: " + winner;
        } else if (isDraw()) {
            result = "Draw";
        } else {
            result = "Game In Progress";
        }

        return "Player X: " + playerXWins +
                "\nPlayer O: " + computerOWins +
                "\n" + result;
    }
}
