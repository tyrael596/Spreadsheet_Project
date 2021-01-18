/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class FileActor {

    public void exportSpreadsheet(Spreadsheet excel, String filename) {

        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(filename));

            for (int i = 1; i < SpreadsheetToolkit.MAXROW; i++) {
                for (int j = 1; j < SpreadsheetToolkit.MAXCOL; j++) {
                    bw.write(excel.spreadsheet[i][j].content.getInput() + ((j == excel.spreadsheet[i].length - 1) ? "" : ";"));
                }

                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(FileActor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void loadSpreadsheet(Spreadsheet excel, String filename) throws FileNotFoundException {
        Path path = Paths.get(filename);

        try ( BufferedReader br = Files.newBufferedReader(path)) {
            for (int i = 1; i < SpreadsheetToolkit.MAXROW; i++) {
                String line = br.readLine();
                String[] content = line.split(";");
                for (int j = 1; j < SpreadsheetToolkit.MAXCOL; j++) {
                    excel.spreadsheet[i][j].content.setContent(content[j - 1]);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void readTextfile(String location, LinkedList<String> commands) {
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
