/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.FormulaElement;
import java.util.LinkedList;

/**
 * Content for cell of text type
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class ContentText extends CellContent {

    String value = "";

    /**
     * Return the String content of this cell
     *
     * @return
     */
    @Override
    public String getContent() {
        // System.out.println("getContent " + this.value);
        return this.value;

    }

    /**
     * Only formula type cell would want to call this method
     *
     * @return string of content, same as getcontent in this case
     */
    @Override
    public String getInput() {
        return this.value;
    }

    /**
     * set content of this cell to newContent
     *
     * @param newContent string to replace current content
     */
    @Override
    public void setContent(String newContent) {
        this.value = newContent;
        // System.out.println(this.value);
    }

    /**
     * modify current content to new content value
     *
     * @param newContent to be updating old content
     */
    @Override
    public void modifyContent(String newContent) {
        this.value = newContent;
    }

    /**
     * This will only be used by formula type cell and therefore does not apply
     * here
     *
     * @param newContent current string content
     * @param list list of FormulaElements, does not apply here
     */
    @Override
    public void modifyContent(String newContent, LinkedList<FormulaElement> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This will only be used by formula type cell and therefore does not apply
     * here
     *
     * @param newContent current string content
     * @param list list of FormulaElements, does not apply here
     */
    @Override
    public void setContent(String newContent, LinkedList<FormulaElement> list) {
        this.value = newContent;
    }

    /**
     * This will only be used by formula type cell and therefore does not apply
     * here
     *
     * @return does not apply, list would be empty if called
     */
    @Override
    public LinkedList getFormula() {
        return null;

    }

}
