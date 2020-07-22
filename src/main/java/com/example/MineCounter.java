package com.example;

public class MineCounter {

    public int countNeighbours(String[][] field, int i, int j) {
        int counter = 0;

        try {
            String s = field[i - 1][j - 1];
            if (s.equals(NameSpace.X)) {
                counter++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            String s = field[i - 1][j];
            if (s.equals(NameSpace.X)) {
                counter++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            String s = field[i - 1][j + 1];
            if (s.equals(NameSpace.X)) {
                counter++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            String s = field[i][j - 1];
            if (s.equals(NameSpace.X)) {
                counter++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            String s = field[i][j + 1];
            if (s.equals(NameSpace.X)) {
                counter++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            String s = field[i + 1][j - 1];
            if (s.equals(NameSpace.X)) {
                counter++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            String s = field[i + 1][j];
            if (s.equals(NameSpace.X)) {
                counter++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        try {
            String s = field[i + 1][j + 1];
            if (s.equals(NameSpace.X)) {
                counter++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        return counter;
    }
}
