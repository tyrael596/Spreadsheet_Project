/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.function;

import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import java.util.LinkedList;

/**
 ** Class containing all the PPROMEDIO Function's methods
 *
 * @author Alex Eslava and Amaya Balaguer
 */

public class PROMEDIO extends Function {

    /**
     * Function that returns a PROMEDIO object. It assigns the correct token and
     * the function sequence
     *
     * @param sequence String that contains the parameters of that function
     */
    public PROMEDIO(String sequence) {
        super(SpreadsheetToolkit.TOKENPROMEDIO, sequence);
    }

    /**
     * Function that calculates the final value of the PROMEDIO function
     * introduced by the user from a list of floats.
     *
     * @param list List of floats containing all the values to consider when
     * computing a PROMEDIO function
     * @return float with the average of all the floats contained in the list.
     */
    @Override
    public float Calculate(LinkedList list) {
        float a = 0, aux = 0;
        int last = list.size();
        //aux = (float) list.peekFirst();

        for (int i = 1; i <= last; i++) {
            aux = (float) list.pollFirst() + aux;

        }
        a = aux / last;

        return a;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Function that returns an error when a PROMEDIO function has been
     * introduced incorrectly.
     */

    @Override
    void getError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
