package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    private MineCounter counter;
    private Game game;
    private List<Integer> randoms;
    private List<String> minesCoordinates;
    private String[][] initialField;

    @BeforeEach
    void setUp() {
        counter = new MineCounter();
        game = new Game(counter);
        randoms = Arrays.asList(53, 31, 30, 75, 79, 35, 16, 65, 56, 77);
        minesCoordinates = Arrays.asList("16", "32", "33", "37", "57", "61", "71", "82", "84", "86");

        initialField = new String[][]{{".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "X", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", "X", "X", ".", ".", ".", "X", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "X", "."},
                {".", "X", ".", ".", ".", ".", ".", ".", "."},
                {".", "X", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", "X", ".", "X", ".", "X", ".", "."},};

        game.setMinesField(initialField);
        game.setMinesCoordinates(minesCoordinates);
    }

    @Test
    void putMinesOnBoard() {
        assertArrayEquals(initialField, game.putMinesOnBoard(randoms));
    }

    @Test
    void readMinesCoordinates() {
        List<String> expected = minesCoordinates;
        List<String> current = game.readMinesCoordinates(initialField);

        assertEquals(10, current.size());
        assertArrayEquals(expected.toArray(), current.toArray());
    }

    @Test
    void makeMove() {
        String[][] expected = new String[][]{{".", ".", ".", ".", ".", ".", ".", "1", "/"},
                {".", ".", ".", ".", ".", ".", "X", "1", "/"},
                {".", ".", ".", ".", ".", ".", ".", "2", "1"},
                {".", ".", "X", "X", ".", ".", ".", "X", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", "X", "."},
                {".", "X", ".", ".", ".", ".", ".", ".", "."},
                {".", "X", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", "X", ".", "X", ".", "X", ".", "."},};

        game.makeMove(0, 8);

        assertArrayEquals(expected, game.getMinesField());
    }

    @Test
    void leftFieldsCoordinates() {
        String[][] field = new String[][]{{"/", "/", "/", "/", "/", "1", "1", "1", "/"},
                {"/", "/", "/", "/", "/", "1", "X", "1", "/"},
                {"/", "1", "2", "2", "1", "1", "2", "2", "1"},
                {"/", "1", "X", "X", "1", "/", "1", "X", "1"},
                {"/", "1", "2", "2", "1", "/", "2", "2", "2"},
                {"1", "1", "1", "/", "/", "/", "1", "X", "1"},
                {"2", "X", "1", "/", "/", "/", "1", "1", "1"},
                {"2", "X", "3", "2", "1", "2", "1", "1", "."},
                {"1", "2", "X", "2", "X", "2", "X", "1", "."},};

        game.setMinesField(field);

        List<String> expectedCoordinates = Arrays.asList("16", "32", "33", "37", "57", "61", "71", "82", "84", "86", "78", "88");
        Collections.sort(expectedCoordinates);

        assertEquals(12, game.leftFieldsCoordinates().size());
        assertArrayEquals(expectedCoordinates.toArray(), game.leftFieldsCoordinates().toArray());
    }
}