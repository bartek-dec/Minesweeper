package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineGenerator {

    private Random random;

    public MineGenerator(Random random) {
        this.random = random;
    }

    public List<Integer> getMinesPositions(int quantity, String coordinate) {
        List<Integer> randoms = new ArrayList<>();
        String[] coordinates = coordinate.split("");
        int firstShoot = Integer.parseInt(coordinates[0]) * 9 + Integer.parseInt(coordinates[1]) + 1;
        int index = 0;

        while (index != quantity) {
            int num = random.nextInt(81) + 1;

            if (num == firstShoot) {
                continue;
            }

            if (!randoms.contains(num)) {
                randoms.add(num);
                index++;
            }
        }
        return randoms;
    }
}
