package com.kodilla.kodillatictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AI {
    private final Board board;
    private final Random random = new Random();
    private int difficulty = 1; // 1 = easy, 2 = medium, 3 = hard

    public AI(Board board) {
        this.board = board;
    }

    public void setDifficulty(int level) {
        this.difficulty = Math.max(1, Math.min(3, level));
    }

    public int[] getAiMove() {
        int size = board.getSize();

        if (difficulty == 1) {
            return randomMove(board);
        } else if (difficulty == 2) {
            int[] block = findBlockingMove();
            return block != null ? block : randomMove(board)        ;
        } else {
            if (size == 3) {
                return getBestMove3();
            } else {
                return getBestMove10();
            }
        }
    }

    //Easy Difficulty
    private int[] randomMove(Board board) {
        List<int[]> emptyCells = new ArrayList<>();
        int size  = board.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.getValue(row, col).isEmpty()) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }
        if (emptyCells.isEmpty()) return null;
        return emptyCells.get(random.nextInt(emptyCells.size()));
    }

    //Medium Difficulty
    private int[] findBlockingMove() {
        int size = board.getSize();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.getValue(row, col).isEmpty()) {
                    board.setValue(row, col, "X");
                    if (board.whoIsWinner().equals("X")) {
                        board.setValue(row, col, "");
                        return new int[]{row, col};
                    }
                    board.setValue(row, col, "");
                }
            }
        }
        return null;
    }

    //Hard Difficulty for board 3x3
    private int[] getBestMove3() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;
        int size = board.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.getValue(row, col).isEmpty()) {
                    board.setValue(row, col, "O");
                    int score = miniMax(false);
                    board.setValue(row, col, "");
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{row, col};
                    }
                }
            }
        }
        return bestMove;
    }

    //Hard Difficulty for board 10x10
    private int[] getBestMove10() {
        int size = board.getSize();
        int mid = size / 2;

        //Middle of table
        if (board.getValue(mid, mid).isEmpty()) {
            return new int[]{mid, mid};
        }

        //Looking for position next to it's symbol
        int bestRow = -1;
        int bestCol = -1;
        int bestScore = -1;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.getValue(row, col).isEmpty()) {
                    int score = 0;

                    //Checking the 8 neighbouring cells
                    for (int dr = -1; dr <= 1; dr++) {
                        for (int dc = -1; dc <= 1; dc++) {
                            if (dr == 0 && dc == 0 ) continue;
                            int r =  row + dr;
                            int c = col + dc;
                            if (r >= 0 && r < size && c >= 0 && c < size) {
                                if (board.getValue(r, c).equals("O")) {
                                    score++;
                                }
                            }
                        }
                    }

                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }

        //When found good cell - play there!
        if (bestRow != -1 && bestCol != -1) {
            return new int[]{bestRow, bestCol};
        }

        //If no free cells pick random
        List<int[]> emptyCells = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board.getValue(row, col).isEmpty()) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }
        if (emptyCells.isEmpty()) return null;

        return emptyCells.get(random.nextInt(emptyCells.size()));
    }

    private int miniMax(boolean isMaximizing) {
        String winner = board.whoIsWinner();
        if (winner.equals("O")) { return 10;}
        if (winner.equals("X")) { return -10;}
        if (board.isDraw()) { return 0;}

        int bestScore;
        int size = board.getSize();

        if (isMaximizing) {
            //AI move (wants to maximize the score)
            bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (board.getValue(row, col).isEmpty()) {
                        board.setValue(row, col, "O");
                        int score = miniMax(false);
                        board.setValue(row, col, "");
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
        } else {
            //Players move (wants to minimize AI's score)
            bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    if (board.getValue(row, col).isEmpty()) {
                        board.setValue(row, col, "X");
                        int score = miniMax(true);
                        board.setValue(row, col, "");
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }
}
