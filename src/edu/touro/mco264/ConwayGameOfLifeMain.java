package edu.touro.mco264;

import java.util.Scanner;

public class ConwayGameOfLifeMain {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);

        ConwayGameOfLife conwayGrid = new ConwayGameOfLife("Blinker");

        do {

            System.out.println(conwayGrid);

            conwayGrid.evolveGrid();

            System.out.print("\nHit ENTER to evolve grid: ");

        } while (keyboard.nextLine().equals(""));
    }
}
