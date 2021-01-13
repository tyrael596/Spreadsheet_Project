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
 *
 * @author Alex
 */
public class ContentFormula extends CellContent {

    String value = "";

    @Override
    public String getContent() {
        return this.value;
    }

    @Override
    public void setContent(String newContent) {

        this.value = newContent;

    }

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

    @Override
    public LinkedList getFormula() {
        return this.list;
    }

    public void modifyContent(String newContent, LinkedList<FormulaElement> list) {
        this.value = newContent;
        this.list = list;
    }

    @Override
    public void modifyContent(String newContent) {
        this.value = newContent;

    }

    @Override
    public void setContent(String newContent, LinkedList<FormulaElement> list) {

        this.value = newContent;
        this.list = list;

    }

}
