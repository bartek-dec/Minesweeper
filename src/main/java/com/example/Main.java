package com.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        MineCounter mineCounter = new MineCounter();
        Game game = new Game(mineCounter);
        MineGenerator generator = new MineGenerator(random);
        Processor processor = new Processor(game, generator);

        processor.run();
    }
}
