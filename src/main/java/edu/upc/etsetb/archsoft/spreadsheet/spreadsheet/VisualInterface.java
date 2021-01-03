/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import java.io.File;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import static edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Controller.spreadsheet;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author amayabalaguer
 */
public class VisualInterface {

    String userInput;
    Scanner scan = new Scanner(System.in);

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
