/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import java.io.File;
import java.util.Scanner;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import static edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Controller.spreadsheet;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author amayabalaguer
 */
public class VisualInterface {

    String userInput;
    Scanner scan = new Scanner(System.in);

    public void printMenu() throws UnknownOptionException {

        System.out.println("Welcome to miniExcel what do you want to do? ");
        System.out.println("(RF) Read commands from File  ");
        System.out.println("(C)  Create a New Spreadsheet ");
        System.out.println("(E)  Edit a cell (command E) ");
        System.out.println("(L)  Load a Spreadsheet from a file ");
        System.out.println("(S)  Save the Spreadsheet to a file ");
        String option = scan.nextLine();  // Read user input
        System.out.println("Your option  is: " + option);  // Output user input
        try {
            performAction(option);
        } catch (UnknownOptionException e) {
            System.out.println("Enter a valid option ");

        }

    }

    private void performAction(String option) throws UnknownOptionException {
        String[] parts = option.split("\\ ");

        switch (parts[0].toUpperCase()) {
            case "RF":
                ReadFromFileUsingScanner();
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

    private static void printSpreadsheet() {
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

    public void ReadFromFileUsingScanner() {
        // pass the path to the file as a parameter 
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        try {
            File file = new File("/Users/amayabalaguer/Documents/MEGAsync/MEGAsync/UPC/ARQSOFT/Spreadsheet/Spreadsheet_Project/commands.txt⁩");

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException es) {
            System.out.println("Empty file");
        }

    }
}
