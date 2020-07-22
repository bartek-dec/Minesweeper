package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Game {

    private String[][] minesField;
    private List<String> minesCoordinates;
    private List<String> userCoordinates;
    private MineCounter mineCounter;
    private int flags;

    public Game(MineCounter mineCounter) {
        userCoordinates = new ArrayList<>();
        this.mineCounter = mineCounter;
    }

    public String[][] getMinesField() {
        return minesField;
    }

    public void setMinesField(String[][] minesField) {
        this.minesField = minesField;
    }

    public List<String> getMinesCoordinates() {
        Collections.sort(minesCoordinates);
        return minesCoordinates;
    }

    public void setMinesCoordinates(List<String> minesCoordinates) {
        this.minesCoordinates = minesCoordinates;
    }

    public List<String> getUserCoordinates() {
        Collections.sort(userCoordinates);
        return userCoordinates;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getFlags() {
        return flags;
    }

    public void takeFlag() {
        this.flags--;
    }

    public void giveFlagBack() {
        this.flags++;
    }

    public String[][] putMinesOnBoard(List<Integer> randoms) {
        String[][] fields = new String[9][9];
        int counter = 1;
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (randoms.contains(counter)) {
                    fields[i - 1][j - 1] = NameSpace.X;
                } else {
                    fields[i - 1][j - 1] = NameSpace.DOT;
                }
                counter++;
            }
        }
        return fields;
    }

    public List<String> readMinesCoordinates(String[][] field) {
        return readCoordinates(field, NameSpace.X);
    }

    public List<String> leftFieldsCoordinates() {
        List<String> coordinates = new ArrayList<>(readCoordinates(minesField, NameSpace.DOT));
        coordinates.addAll(getMinesCoordinates());
        Collections.sort(coordinates);

        return coordinates;
    }

    private List<String> readCoordinates(String[][] minesField, String sign) {
        List<String> coordinates = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (minesField[i][j].equals(sign)) {
                    coordinates.add(i + "" + j);
                }
            }
        }
        return coordinates;
    }

    public void makeMove(int i, int j) {

        if (i < 0 || i > 8 || j < 0 || j > 8) {
            return;
        }

        if (!Objects.equals(minesField[i][j], NameSpace.DOT) &&
                !Objects.equals(minesField[i][j], NameSpace.STAR)) {
            return;
        }

        if (Objects.equals(minesField[i][j], NameSpace.X)) {
            return;
        }

        int neighbours = mineCounter.countNeighbours(minesField, i, j);
        if (neighbours > 0) {
            minesField[i][j] = Integer.toString(neighbours);
            return;
        }

        minesField[i][j] = "/";

        makeMove(i - 1, j - 1);
        makeMove(i - 1, j);
        makeMove(i - 1, j + 1);
        makeMove(i, j - 1);
        makeMove(i, j);
        makeMove(i, j + 1);
        makeMove(i + 1, j - 1);
        makeMove(i + 1, j);
        makeMove(i + 1, j + 1);
    }

    public void uncoverMines() {
        for (String s : minesCoordinates) {
            String[] coordinates = s.split("");
            int i = Integer.parseInt(coordinates[0]);
            int j = Integer.parseInt(coordinates[1]);

            minesField[i][j] = NameSpace.X;
        }
    }
}
