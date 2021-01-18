/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.FormulaElement;
import java.util.LinkedList;

/**
 * Abstract class for the 3 main possible types of cells, numeric, text or
 * formula
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public abstract class CellContent {

    public String content = " ";
    LinkedList<FormulaElement> list = new LinkedList(); // expresión
    LinkedList<String> dependentCells = new LinkedList();// Cells that use the value
    LinkedList<String> dependencies = new LinkedList();

    /**
     * Abstract method for the 3 types to return their content
     *
     * @return
     */
    public abstract String getContent();

    /**
     * Abstrat method for the 3 types to return their input ("="+string of
     * content)
     *
     * @return
     */
    public abstract String getInput();

    /**
     * Abstract method for the 3 types to set the content to current cell
     *
     * @param newContent content to replace current content
     */
    public abstract void setContent(String newContent);

    /**
     * Abstract method for the 3 types to set the content to current cell
     *
     * @param newContent content to replace current content
     * @param list content of formulaElements to replace current content
     */
    public abstract void setContent(String newContent, LinkedList<FormulaElement> list);

    /**
     * Abstract method for the 3 types to modify the current content
     *
     * @param newContent new content to add
     */
    public abstract void modifyContent(String newContent);

    /**
     * Abstract method for the 3 types to modify the current content
     *
     * @param newContent new content to add
     * @param list list of formulaelements to update
     */
    public abstract void modifyContent(String newContent, LinkedList<FormulaElement> list);

    public abstract LinkedList getFormula();

    /**
     * Modify the list of cells that this cell depends from
     *
     * @param newContent new dependencies
     */
    public void modifyDependentCells(String newContent) {
        this.dependentCells.push(newContent);
    }

    /**
     * return the list containing all cells that depend on this cell
     *
     * @return the list containing all cells that depend on this cell
     */
    public LinkedList<String> getDependentCells() {
        return this.dependentCells;
    }

    /**
     * get dependencies of this cell
     *
     * @return list of cells from which this cell depends
     */
    public LinkedList<String> getDependencies() {
        return this.dependencies;
    }

    /**
     * set dependencies of this cell to input new list of dependencies
     *
     * @param dependencies input list of new dependencies
     */
    public void setDependencies(LinkedList<String> dependencies) {
        this.dependencies = dependencies;
    }

    /**
     * clear "reference" dependency from cell
     *
     * @param reference dependency to be erased
     */
    public void deleteDependency(String reference) {
        this.dependentCells.remove(reference);
    }

    /**
     * set the dependenent cells of this cell to list of cells named "depens"
     *
     * @param depens list of dependant cells
     */
    public void setDependentCells(LinkedList<String> depens) {
        this.dependentCells = depens;
    }

}
