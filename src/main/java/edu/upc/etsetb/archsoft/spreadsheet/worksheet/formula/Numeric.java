/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula;

import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Visitador;
import java.util.LinkedList;

/**
 * Class containing the Functions used when the Formula element is a number.
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class Numeric extends FormulaElement {

    /**
     * Accept visitador function it returns the visitador of this object
     *
     * @param visitador
     * @param input
     * @return This object's visitador
     */
    @Override
    public float acceptVisitor(Visitador visitador, LinkedList input) {
        return visitador.visitaNumeric(this, input);
    }

    /**
     * *
     * Function that creates a new Numeric object assigning the given token and
     * sequence.
     *
     * @param token: Integer containing the assigned token
     * @param sequence Sequence containing the number itself
     */

    public Numeric(int token, String sequence) {
        super(token, sequence);
    }

}
