package com.example;

import java.util.*;

public class Processor {

//    private final String FREE = "free";
//    private final String MINE = "mine";
//    private final String DOT = ".";
//    private final String X = "X";
//    private final String STAR = "*";

    private Scanner scanner;
    private Printer printer;
    private Game game;
    private MineGenerator generator;
    private boolean isGameSet;

    public Processor(Game game, MineGenerator generator) {
        this.scanner = new Scanner(System.in);
        this.printer = new Printer();
        this.game = game;
        this.generator = generator;
        this.isGameSet = false;
    }

    public void run() {
        int mines = getMineQuantity();
        game.setFlags(mines);
        printer.printField();

        boolean isGameLost;
        boolean isGameFinished = false;
        do {
            isGameLost = getUserInput(mines);

            if (isGameLost) {
                game.uncoverMines();
                printer.printLostField(game.getMinesField());
                System.out.println("You stepped on a mine and failed!");
                return;
            } else {
                System.out.println("Remaining flags: " + game.getFlags());
                printer.printField(game.getMinesField());
            }

            if (game.getMinesCoordinates().equals(game.getUserCoordinates())) {
                isGameFinished = true;
            } else if (game.getMinesCoordinates().equals(game.leftFieldsCoordinates())) {
                isGameFinished = true;
            }

        } while (!isGameFinished);

        System.out.println("Congratulations! You found all mines!");

    }

    private boolean getUserInput(int mines) {
        boolean inputs = false;

        do {
            System.out.print("Set/unset mines marks or claim a cell as free (provide \"x\" \"y\" free/mine): ");
            try {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                String command = scanner.next().toLowerCase();

                if (x >= 1 && y >= 1 && x <= 9 && y <= 9 &&
                        (Objects.equals(command, NameSpace.FREE) || Objects.equals(command, NameSpace.MINE))) {

                    String coordinate = Integer.toString(y - 1) + (x - 1);

                    if (!isGameSet) {
                        List<Integer> minePositions = generator.getMinesPositions(mines, coordinate);
                        String[][] minesField = game.putMinesOnBoard(minePositions);

                        game.setMinesField(minesField);
                        game.makeMove(y - 1, x - 1);
                        game.setMinesCoordinates(game.readMinesCoordinates(minesField));
                        this.isGameSet = true;
                        return false;
                    }

                    String s = game.getMinesField()[y - 1][x - 1];

                    //player explores mine, game over
                    if (Objects.equals(s, NameSpace.X) && Objects.equals(command, NameSpace.FREE)) {
                        return true;
                        //player marks potential mine
                    } else if ((Objects.equals(s, NameSpace.DOT) ||
                            Objects.equals(s, NameSpace.X)) &&
                            Objects.equals(command, NameSpace.MINE)) {
                        game.getMinesField()[y - 1][x - 1] = NameSpace.STAR;
                        game.getUserCoordinates().add(coordinate);
                        game.takeFlag();
                        inputs = true;
                        //player removes flag of the potential mine
                    } else if (Objects.equals(s, NameSpace.STAR) && Objects.equals(command, NameSpace.MINE)) {
                        if (game.getMinesCoordinates().contains(coordinate)) {
                            game.getMinesField()[y - 1][x - 1] = NameSpace.X;
                        } else {
                            game.getMinesField()[y - 1][x - 1] = NameSpace.STAR;
                        }
                        game.getUserCoordinates().remove(coordinate);
                        game.giveFlagBack();
                        inputs = true;
                        //player makes ordinary move
                    } else if (Objects.equals(s, NameSpace.DOT) && Objects.equals(command, NameSpace.FREE)) {
                        game.makeMove(y - 1, x - 1);
                        inputs = true;
                    }

                } else {
                    System.out.println("Invalid coordinates");
                }
            } catch (InputMismatchException e) {
                System.out.println("Provide integer numbers");
            } //catch (NullPointerException e) {
                //System.out.println("Invalid command");
            //}
        } while (!inputs);
        return false;
    }

    private int getMineQuantity() {
        int quantity = -1;
        do {
            try {
                System.out.print("How many mines do you want on the field? ");
                quantity = Integer.parseInt(scanner.nextLine().trim());

                if (quantity < 1 || quantity > 64) {
                    System.out.println("Invalid number of mines");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number of mines");
            }
        } while (quantity < 1 || quantity > 64);

        return quantity;
    }
}
