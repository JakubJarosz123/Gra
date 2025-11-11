package com.kodilla.kodillatictactoe;

public class Board3x3 {
    private String[][] figures;
    private int playerXWins = 0;
    private int computerOWins = 0;

    public Board3x3(int size) {
        figures = new String[size][size];
        resetBoard();
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
            for (int col = 0; col < figures.length; col++) {
                figures[row][col] = "";
            }
        }
    }

    public String whoIsWinner() {
        String winner = "";

        for (int row = 0; row < figures.length; row++) {
            if (!figures[row][0].isEmpty() &&
                    figures[row][0].equals(figures[row][1]) &&
                    figures[row][1].equals(figures[row][2])) {
                winner = figures[row][0];
                return winner;
            }
        }

        for (int col = 0; col < figures.length; col++) {
            if (!figures[0][col].isEmpty() &&
                    figures[0][col].equals(figures[1][col]) &&
                    figures[1][col].equals(figures[2][col])) {
                winner = figures[0][col];
                return winner;
            }
        }

        if (!figures[0][0].isEmpty() &&
        figures[0][0].equals(figures[1][1]) &&
        figures[1][1].equals(figures[2][2])) {
            winner = figures[0][0];
            return winner;
        }

        if (!figures[0][2].isEmpty() &&
                figures[0][2].equals(figures[1][1]) &&
                figures[1][1].equals(figures[2][0])) {
            winner = figures[0][2];
            return winner;
        }
        return "";
    }

    public void registerWinner(String winner) {
        if (winner.equals("X")) {
            playerXWins++;
        } else if (winner.equals("O")) {
            computerOWins++;
        }
    }

    public boolean isDraw() {
        if (!whoIsWinner().equals("")) {
            return false;
        }

        for (int row = 0; row < figures.length; row++) {
            for (int col = 0; col < figures[row].length; col++) {
                if (figures[row][col] == null || figures[row][col].equals("")) {
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
