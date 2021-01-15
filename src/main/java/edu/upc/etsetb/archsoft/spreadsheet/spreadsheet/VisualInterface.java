/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import static edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Controller.spreadsheet;
import java.util.Scanner;

/**
 * Class that contains all the information and processes of the Visual Interface
 *
 * @author Alex Eslava y Amaya Balaguer
 */
public class VisualInterface {

    String userInput;
    Scanner scan = new Scanner(System.in);

    /**
     * Void that prints the menu and gets the user's input
     *
     * @throws UnknownOptionException when there is a problem with the action
     * introduced by the user.
     */
    public void printMenu() throws UnknownOptionException {
        System.out.println();
        System.out.println("Welcome to miniExcel what do you want to do? ");
        System.out.println("(RF) Read commands from File  ");
        System.out.println("(C)  Create a New Spreadsheet ");
        System.out.println("(E)  Edit a cell (command E) ");
        System.out.println("(L)  Load a Spreadsheet from a file ");
        System.out.println("(S)  Save the Spreadsheet to a file ");
        String option = scan.nextLine();  // Read user input
        System.out.println("Your option  is: " + option);  // Output user input
        System.out.println();
        try {
            performAction(option);
        } catch (UnknownOptionException e) {
            System.out.println("Enter a valid option ");

        }

    }

    /**
     * It asks the user for confirmation to perform a certain action
     *
     * @return returns the value inputed by the user
     */
    public static char askConfirmation() {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("There is a file currently in use if you create a new one you will loose the unsaved work");
        System.out.println("Are you sure you want to continue with this option? ");
        System.out.println("(Y)  Yes ");
        System.out.println("(N)  No ");
        System.out.println();
        String option = scan.next();
        System.out.println("Your option  is: " + option.charAt(0));
        if (option.length() <= 1 && (Character.toUpperCase(option.charAt(0)) == 'Y' || Character.toUpperCase(option.charAt(0)) == 'N')) {

            return option.charAt(0);

        } else {
            return 'x';
        }

    }

    /**
     * Void in charge of calling the right function according to the action
     * inputed by the user.
     *
     * @param option It contains the string written by the user
     * @throws UnknownOptionException Thowed whenever the user enters an unknown
     * option.
     */
    public void performAction(String option) throws UnknownOptionException {
        String[] parts = option.split("\\ ");

        switch (parts[0].toUpperCase()) {
            case "RF":
                System.out.println("Working Directory = " + System.getProperty("user.dir"));
                if (parts.length < 2) {
                    throw new UnknownOptionException();
                } else {
                    Controller.readCommands(parts[1]);

                }
                break;
            case "C":
                Controller.create();

                break;
            case "E":
                if (parts.length < 3) {
                    throw new UnknownOptionException();
                } else {
                    // Falta comprobar que lo que me entran son coordenadas válidas pero supongamos que si
                    try {
                        Controller.validCell(parts[1]);
                        Controller.editCell(parts);
                    } catch (UnknownReferenceException e) {
                        System.out.println("Enter a valid cell Reference ");
                        break;
                    }
                }
                break;

            case "L":
                Controller.load(parts[1]);
                break;
            case "S":
                Controller.saveSpreadsheet(parts[1]);
                break;
            default:
                throw new UnknownOptionException();
        }

        try {
            printSpreadsheet();
        } catch (java.lang.NullPointerException ex) {

        }

    }

    /**
     * Void that prints the complete spreadsheet.
     */
    public static void printSpreadsheet() {
        System.out.println();

        System.out.println("------------------------------------");

        System.out.println();
        for (int i = 0; i < SpreadsheetToolkit.MAXROW; i++) {
            for (int j = 0; j < SpreadsheetToolkit.MAXCOL; j++) {
                System.out.print(spreadsheet.spreadsheet[i][j].content.getContent() + "    ");

            }
            System.out.println();

        }
        System.out.println();

        System.out.println("------------------------------------");
        System.out.println();
    }

}
