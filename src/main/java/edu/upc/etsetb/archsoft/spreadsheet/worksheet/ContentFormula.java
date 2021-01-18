/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.FormulaElement;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Content for Formula type cell
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class ContentFormula extends CellContent {

    String value = "";

    /**
     * get current content from this cell
     *
     * @return current cell content
     */
    @Override
    public String getContent() {
        return this.value;
    }

    /**
     * set the content of this cell to newcontent
     *
     * @param newContent the new content of the cell
     */
    @Override
    public void setContent(String newContent) {

        this.value = newContent;

    }

    /**
     * iterate through list of formulaelements from this cell and return it as a
     * string with an added "="+ at the beggining of it
     *
     * @return full string of content such as ("=1+A2")
     */
    @Override
    public String getInput() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("=");
        Iterator<FormulaElement> it = this.list.listIterator();
        while (it.hasNext()) {
            stringBuilder.append(it.next().getSequence());
        }
        String formula = stringBuilder.toString();
        return formula;
    }

    /**
     * get the list of formulaElements fount in this cell
     *
     * @return said list of formulaelements
     */
    @Override
    public LinkedList getFormula() {
        return this.list;
    }

    /**
     * modify content of this cell + it's list of formulaElement list to input
     *
     * @param newContent new string content of the cell
     * @param list new list of formulaelements for this list
     */
    public void modifyContent(String newContent, LinkedList<FormulaElement> list) {
        this.value = newContent;
        this.list = list;
    }

    /**
     * modify string content of this cell
     *
     * @param newContent to replace current content
     */
    @Override
    public void modifyContent(String newContent) {
        this.value = newContent;

    }

    /**
     * set content of this cell to new values.
     *
     * @param newContent new string of content to be set
     * @param list list of formulaelements to be set
 *
     */
    @Override
    public void setContent(String newContent, LinkedList<FormulaElement> list) {

        this.value = newContent;
        this.list = list;

    }

}
