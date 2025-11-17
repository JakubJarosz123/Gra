package com.kodilla.kodillatictactoe;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UXTestSuite {
    @BeforeAll
    static void beforeAll() {
        System.out.println("Beginning of tests");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Starting test...");
    }

    @AfterEach
    void afterEach() {
        System.out.println("Test finished.");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Tests have been executed");
    }

    @Nested
    @DisplayName("Testing winner O")
    class winnerO {
        @DisplayName("In rows")
        @Test
        public void winnerOinRowsTest() {
            //Given
            Board board = new Board(3, 3);

            for (int col = 0; col < board.getSize(); col++) {
                board.setValue(0, col, "O");
            }
            //When
            String winner = board.whoIsWinner();
            //Then
            assertEquals("O", winner);
        }

        @DisplayName("In columns")
        @Test
        public void winnerOinColumnsTest() {
            //Given
            Board board = new Board(3, 3);

            for (int row = 0; row < board.getSize(); row++) {
                board.setValue(row, 0, "O");
            }
            //When
            String winner = board.whoIsWinner();
            //Then
            assertEquals("O", winner);
        }

        @DisplayName("Diagonally")
        @Test
        public void winnerOinDiagonalTest() {
            //Given
            Board board = new Board(3, 3);

            for (int row = 0; row < board.getSize(); row++) {
                for (int col = 0; col < board.getSize(); col++) {
                    board.setValue(row, col, "O");
                }
            }
            //When
            String winner = board.whoIsWinner();
            //Then
            assertEquals("O", winner);
        }
    }

    @Nested
    @DisplayName("Testing winner X")
    class winnerXTest {
        @DisplayName("In rows")
        @Test
        public void winnerXinRowsTest() {
            //Given
            Board board = new Board(3, 3);

            for (int row = 0; row < board.getSize(); row++) {
                board.setValue(row, 0, "X");
            }
            //When
            String winner = board.whoIsWinner();
            //Then
            assertEquals("X", winner);
        }

        @DisplayName("In columns")
        @Test
        public void winnerXinColumnsTest() {
            //Given
            Board board = new Board(3, 3);

            for (int row = 0; row < board.getSize(); row++) {
                board.setValue(row, 0, "X");
            }
            //When
            String winner = board.whoIsWinner();
            //Then
            assertEquals("X", winner);
        }

        @DisplayName("Diagonally")
        @Test
        public void winnerXinDiagonalTest() {
            //Given
            Board board = new Board(3, 3);

            for (int row = 0; row < board.getSize(); row++) {
                for (int col = 0; col < board.getSize(); col++) {
                    board.setValue(row, col, "X");
                }
            }
            //When
            String winner = board.whoIsWinner();
            //Then
            assertEquals("X", winner);
        }
    }

    @Nested
    @DisplayName("Testing draw")
    class drawTest {
        @Test
        public void drawTest() {
            //Given
            Board board = new Board(3, 3);

            String[][] layout = {
                    {"O", "X", "O"},
                    {"O", "X", "O"},
                    {"X", "O", "X"},
            };

            for (int row = 0; row < board.getSize(); row++) {
                for (int col = 0; col < board.getSize(); col++) {
                    board.setValue(row, col, layout[row][col]);
                }
            }
            //When
            boolean winner = board.isDraw();
            //Then
            assertTrue(winner);
        }
    }

    @Nested
    @DisplayName("Testing exception")
    class exceptionTest {
        @Test
        public void shouldThrowExceptionForInvalidPosition() {
            //Given
            Board board = new Board(3, 3);
            //When & Then
            assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
                board.setValue(9, 0, "O");
            });
        }
    }
}
