/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class GPIO {

    public void exportSpreadsheet(Spreadsheet excel, String filename) {

        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(filename));

            for (int i = 0; i < SpreadsheetToolkit.MAXROW; i++) {
                for (int j = 0; j < SpreadsheetToolkit.MAXCOL; j++) {
                    bw.write(excel.spreadsheet[i][j] + ((j == excel.spreadsheet[i].length - 1) ? "" : ","));
                }

                bw.newLine();
            }
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(GPIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadSpreadsheet(Spreadsheet excel) {

    }

    public static void readTextfile(String location, LinkedList<String> commands){
        // pass the path to the file as a parameter 

        try {
            FileReader reader = new FileReader(location);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                commands.addLast(line);
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {

            System.out.println("File not found");
        }

    }
}
