/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.function;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.CellReference;
import java.util.List;

/**
 * Object RangeOfCell that can transform into a list of CellReferences when parsed
 * @author Alex Eslava and Amaya Balaguer
 */
public class RangeOfCell {

    CellReference topLeft;
    CellReference topRight;
    List fullRange;
    /*  abstract void Calculate();
    abstract void getError(); */

}
