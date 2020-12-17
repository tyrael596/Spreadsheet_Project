/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

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
            // poner qué haacer si hay un error

        }

    }

    private void performAction(String option) throws UnknownOptionException {
        String[] parts = option.split("\\ ");
        switch (parts[0]) {
            case "RF":
                break;
            case "C":
                Controller.create();
                
                break;
            case "E":
                // Falta comprobar que lo que me entran son coordenadas válidas pero supongamos que si
                try{
                    Controller.validCell(parts[1]);
                }catch(UnknownReferenceException e){
                    System.out.println("Enter a valid cell Reference ");
                    break;
                }
                Controller.editCell(parts);

                break;

            case "L":
                break;
            case "S":
                break;
            default:
                throw new UnknownOptionException();
        }

    }
}
