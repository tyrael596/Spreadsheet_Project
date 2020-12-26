/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.CellContent;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.CellFormula;


/**
 *
 * @author Alex
 */
public class Cell{
    public CellContent content;
    static int row;
    static int col;
   // public void getType(){
    //}
    
    public Cell(){
        content = new CellFormula();
        
    }
    public void modifyContent(String newContent){
        this.content.setContent(newContent);
    }
    
}
