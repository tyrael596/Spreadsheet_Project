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
    LinkedList<FormulaElement> list = new LinkedList(); // expresión
    LinkedList<String> dependencies = new LinkedList();

    /**
     *
     * @return
     */
    public abstract String getContent();

    /**
     *
     * @return
     */
    public abstract String getInput();

    /**
     *
     * @param newContent
     */
    public abstract void setContent(String newContent);

    /**
     *
     * @param newContent
     * @param list
     */
    public abstract void setContent(String newContent, LinkedList<FormulaElement> list);

    /**
     *
     * @param newContent
     */
    public abstract void modifyContent(String newContent);

    /**
     *
     * @param newContent
     * @param list
     */
    public abstract void modifyContent(String newContent, LinkedList<FormulaElement> list);

    public abstract LinkedList getFormula();

    public void modifyDependencies(String newContent) {
        this.dependencies.push(newContent);
    }

    public LinkedList<String> getDependencies() {
        return this.dependencies;
    }

}
