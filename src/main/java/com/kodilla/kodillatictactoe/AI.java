package com.kodilla.kodillatictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AI {
    private final Board board;
    private final Random random = new Random();

    public AI(Board board) {
        this.board = board;
    }

    public int[] getAiMove() {
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
}
