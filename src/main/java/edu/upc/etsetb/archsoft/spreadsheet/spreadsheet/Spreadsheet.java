/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import java.util.LinkedList;

/**
 *
 * @author Alex
 */
public class Spreadsheet {


    LinkedList<Cell> affectedCells = new LinkedList(); //Cells being used by references
    Cell[][] spreadsheet;

    public void exportSp() {
    }

    public void importSp() {
    }

    public void printSp() {
    }

    public void updateSp() {
    }

    public  void createSpreadsheet() {
        SpreadsheetFactory factory = new SpreadsheetFactory();
        Cell[][] spreadsheet = new Cell[43][43];
        for (int col = 0; col < 43; col++) {
            for (int row = 0; row < 43; row++) {
                spreadsheet[row][col] = new Cell();
                spreadsheet[row][col].content.setContent(String.valueOf(row + 3));

            }
        }
    }

    public Cell[][] getSpreadsheet() {
        return spreadsheet;
    }

    // Metodo ModifyCell +++ 
    // Tokenizer -> ParserCleaner (formato correcto)  -> Postfixer
}
