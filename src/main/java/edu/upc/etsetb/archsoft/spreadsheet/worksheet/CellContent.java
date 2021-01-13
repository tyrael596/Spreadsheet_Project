/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.FormulaElement;
import java.util.LinkedList;

/**
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public abstract class CellContent {

    public String content = " ";
    LinkedList<FormulaElement> list = new LinkedList(); // expresión
    LinkedList<String> dependentCells = new LinkedList();// Cells that use the value
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

    public void modifyDependentCells(String newContent) {
        this.dependentCells.push(newContent);
    }

    public LinkedList<String> getDependentCells() {
        return this.dependentCells;
    }

    public LinkedList<String> getDependencies() {
        return this.dependencies;
    }

    public void setDependencies(LinkedList<String> dependencies) {
         this.dependencies = dependencies;
    }

    public void deleteDependency(String reference) {
        this.dependentCells.remove(reference);
    }

    public void setDependentCells(LinkedList<String> depens) {
        this.dependentCells = depens;
    }

}
