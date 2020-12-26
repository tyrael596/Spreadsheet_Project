/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.CellNumeric;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.CellText;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
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

    public void createSpreadsheet() {
        SpreadsheetFactory factory = new SpreadsheetFactory();
        spreadsheet = new Cell[SpreadsheetToolkit.MAXROW][SpreadsheetToolkit.MAXCOL];
        for (int col = 0; col < SpreadsheetToolkit.MAXCOL; col++) {
            for (int row = 0; row < SpreadsheetToolkit.MAXROW; row++) {
                this.spreadsheet[row][col] = new Cell();
                if (col == 0) {
                    
                    this.spreadsheet[row][col].content = new CellText();
                    if (row == 0) {
                        this.spreadsheet[row][col].content.setContent(" ");
                    } else {
                        this.spreadsheet[row][col].content.setContent(String.valueOf(row));
                    }
                } else {
                    if (row == 0 && col != 0) {
                       //System.out.println("row " + row + " col " + col +(char) ('A' + col-1));
                        this.spreadsheet[row][col].content = new CellText();
                        this.spreadsheet[row][col].content.setContent(String.valueOf((char) ('A' + col-1)));
                    } else {
                        
                        this.spreadsheet[row][col].content = new CellNumeric();
                        this.spreadsheet[row][col].content.setContent(String.valueOf(row + 3));
                    }
                }

            }
        }
    }

    public Cell[][] getSpreadsheet() {
        return spreadsheet;
    }

    public void modifyCell(int row, int col, String content) {
        this.spreadsheet[row][col].content.modifyContent(content);
    }

    public void modifyCell(int row, int col, String content, LinkedList<FormulaElement> postfix) {
        this.spreadsheet[row][col].content.modifyContent(content, postfix);

    }

    // Metodo ModifyCell +++ 
    // Tokenizer -> ParserCleaner (formato correcto)  -> Postfixer
}
