package com.kodilla.kodillatictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AI {
    private final Board3x3 board3x3;
    private final Board10x10 board10x10;
    private final Random random = new Random();

    public AI(Board3x3 board3x3,  Board10x10 board10x10) {
        this.board3x3 = board3x3;
        this.board10x10 = board10x10;
    }

    public int[] getAiMove3x3() {
        List<int[]> emptyCells = new ArrayList<>();
        int size  = board3x3.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board3x3.getValue(row, col).isEmpty()) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        if (emptyCells.isEmpty()) return null;
        return emptyCells.get(random.nextInt(emptyCells.size()));
    }

    public int[] getAiMove10x10() {
        List<int[]> emptyCells = new ArrayList<>();
        int size = board10x10.getSize();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (board10x10.getValue(row, col).isEmpty()) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        if (emptyCells.isEmpty()) return null;
        return emptyCells.get(random.nextInt(emptyCells.size()));
    }
}
