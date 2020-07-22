package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MineGeneratorTest {

    private Random random;
    private MineGenerator mineGenerator;

    @BeforeEach
    void setUp() {
        random = new Random();
        mineGenerator = new MineGenerator(random);
    }

    @Test
    void getMinesPositionsExpected_10_Coordinates() {
        assertEquals(10, mineGenerator.getMinesPositions(10, "55").size());
    }

    @Test
    void getMinePositionsDoesNotHave_41_WhenPass_44_AsFirstShot() {
        assertFalse(mineGenerator.getMinesPositions(10, "44").contains(41));
    }
}