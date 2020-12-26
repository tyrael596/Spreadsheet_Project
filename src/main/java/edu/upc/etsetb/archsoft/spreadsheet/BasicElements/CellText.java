/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.BasicElements;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import java.util.LinkedList;

/**
 *
 * @author Alex
 */
public class CellText extends CellContent {

    String value = "";

    /*  public void getContent(){
    }     */
    @Override
    public String getContent() {
       // System.out.println("getContent " + this.value);
        return this.value;
        
    }

    @Override
    public void setContent(String newContent) {
        this.value = newContent;
       // System.out.println(this.value);
    }

    @Override
    public void modifyContent(String newContent) {
        this.value = newContent;
    }

    @Override
    public void modifyContent(String newContent, LinkedList<FormulaElement> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setContent(String newContent, LinkedList<FormulaElement> list) {
        this.value = newContent;
    }

}
