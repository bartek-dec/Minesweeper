package com.example;

import java.util.Objects;

public class Printer {

    public void printField(String[][] field) {
        System.out.println("\n | 1 2 3 4 5 6 7 8 9 |");
        System.out.println("–|–––––––––––––––––––|");

        for (int i = 1; i <= 9; i++) {
            System.out.print(i + "| ");
            for (int j = 1; j <= 9; j++) {
                if (field == null) {
                    System.out.print(NameSpace.DOT + " ");
                } else if (Objects.equals(field[i - 1][j - 1], NameSpace.X)) {
                    System.out.print(NameSpace.DOT + " ");
                } else {
                    System.out.print(field[i - 1][j - 1] + " ");
                }
            }
            System.out.print("|\n");
        }

        System.out.println("–|–––––––––––––––––––|");
    }

    public void printField() {
        printField(null);
    }

    public void printLostField(String[][] field) {
        System.out.println("\n | 1 2 3 4 5 6 7 8 9 |");
        System.out.println("–|–––––––––––––––––––|");

        for (int i = 1; i <= 9; i++) {
            System.out.print(i + "| ");
            for (int j = 1; j <= 9; j++) {
                System.out.print(field[i - 1][j - 1] + " ");
            }
            System.out.print("|\n");
        }

        System.out.println("–|–––––––––––––––––––|");
    }
}
