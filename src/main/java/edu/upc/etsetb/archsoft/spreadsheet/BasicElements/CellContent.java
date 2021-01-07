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
 * @author Alex Eslava and Amaya Balaguer
 */
public abstract class CellContent {

    public String content = " ";

    public abstract String getContent();
    
    public abstract String getInput();

    public abstract void setContent(String newContent);

    public abstract void setContent(String newContent, LinkedList<FormulaElement> list);

    public abstract void modifyContent(String newContent);

    public abstract void modifyContent(String newContent, LinkedList<FormulaElement> list);
    //abstract void error();   
}
