/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.CellContent;

/**
 * Class representing a cell object. It is the unit of the spreadsheet and has a
 * content, a row and a col assigned.
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class Cell {

    public CellContent content;
    int row;
    int col;

    /**
     * function that creates a new Cell object and assigns a content to it. 
     * @param content: CellContent containing the type of content that has to be assigned to the cell. 
     */
    public Cell(CellContent content) {
        this.content = content;

    }

    /**
     * Function that allows to modify the content of the cell. It receives a String containing the new content that will be assigned.
     * @param newContent: String containing the new content.
     */
    public void modifyContent(String newContent) {
        this.content.setContent(newContent);
    }

}
