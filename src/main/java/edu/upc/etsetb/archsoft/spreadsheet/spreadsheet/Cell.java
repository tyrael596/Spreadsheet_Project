/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.CellContent;
import java.util.LinkedList;

/**
 *
 * @author Alex
 */
public class Cell {

    public CellContent content;
    int row;
    int col;
    

    public Cell(CellContent content) {
        this.content = content;

    }

    public void modifyContent(String newContent) {
        this.content.setContent(newContent);
    }


}
