package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MineCounterTest {

    private MineCounter mineCounter;

    @BeforeEach
    void setUp() {
        mineCounter = new MineCounter();
    }

    @Test
    void countNeighboursIndexInTheTopRightCorner() {
        String[][] field = {{".", "X", "."},
                            {".", ".", "."},
                            {"X", ".", "X"}};

        assertEquals(1, mineCounter.countNeighbours(field, 0, 2));
    }

    @Test
    void countNeighboursIndexInTheTopRightCornerThanZeroExpected() {
        String[][] field = {{".", ".", "."},
                            {".", ".", "."},
                            {"X", ".", "X"}};

        assertEquals(0, mineCounter.countNeighbours(field, 0, 2));
    }
}