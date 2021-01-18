/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.VisualInterface;

/**
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class Main {

    public static boolean exit = false;
    private static VisualInterface vi = new VisualInterface();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        while (!exit) {
            vi.printMenu();
        }

    }

}
