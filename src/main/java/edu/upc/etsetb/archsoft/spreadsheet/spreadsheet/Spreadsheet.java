/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.ContentNumeric;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.ContentText;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.CellReference;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import java.util.LinkedList;

/**
 * Class containing the main functions of the spreadhseet itself.It includes:
 * creating a spreadsheet, modifying the cell and a, spreadhseet getter.
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class Spreadsheet {

    Cell[][] spreadsheet;

    /**
     * Function that creates an empty spreadsheet. The cells have a text type
     * content by default and contain a blank space. The first row of the
     * Spreadsheet contains the Letters indicating the column and the first row
     * it's corresponding number.
     */
    public void createSpreadsheet() {
        SpreadsheetFactory factory = new SpreadsheetFactory();
        spreadsheet = new Cell[SpreadsheetToolkit.MAXROW][SpreadsheetToolkit.MAXCOL];
        String[] a = CellReference.getString();

        for (int col = 0; col < SpreadsheetToolkit.MAXCOL; col++) {
            for (int row = 0; row < SpreadsheetToolkit.MAXROW; row++) {
                this.spreadsheet[row][col] = new Cell(new ContentNumeric());
                if (col == 0) {

                    this.spreadsheet[row][col].content = new ContentText();
                    if (row == 0) {
                        this.spreadsheet[row][col].content.setContent(" ");
                    } else {
                        this.spreadsheet[row][col].content.setContent(String.valueOf(row));
                    }
                } else {
                    if (row == 0 && col != 0) {
                        this.spreadsheet[row][col].content = new ContentText();
                        this.spreadsheet[row][col].content.setContent(a[col]);

                    } else {

                        this.spreadsheet[row][col].content = new ContentNumeric();
                        this.spreadsheet[row][col].content.setContent(" ");
                    }
                }

            }
        }
    }

    /**
     * Spreadsheet getter. It returns the current spreadsheet (cell matrix)
     * @return
     */
    public Cell[][] getSpreadsheet() {
        return spreadsheet;
    }

    /**
     * Functions that modifies the content of any given cell. 
     * @param row: integer containing the numeric representation of the row
     * @param col integer containing the numeric representation of the column
     * @param content String containing the new content of the cell.
     */
    public void modifyCell(int row, int col, String content) {
        this.spreadsheet[row][col].content.modifyContent(content);
    }

    /**
     * Functions that modifies the content of a ContentFormula type content.
     * @param row: integer containing the numeric representation of the row
     * @param col integer containing the numeric representation of the column
     * @param content String containing the new content of the cell.
     * @param postfix Linked List containing the elements of the formula introduced by the user. 
     */
    public void modifyCell(int row, int col, String content, LinkedList<FormulaElement> postfix) {
        this.spreadsheet[row][col].content.modifyContent(content, postfix);

    }

}
