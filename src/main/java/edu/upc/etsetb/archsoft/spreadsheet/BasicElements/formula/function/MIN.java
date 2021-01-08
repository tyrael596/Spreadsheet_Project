/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function;

import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import java.util.LinkedList;

/**
 *Class containing all the MIN Function's methods 
 * @author Alex Eslava and Amaya Balaguer
 */
public class MIN extends Function {


/**
 * Function that returns a MIN object. It assigns the correct token and the function sequence
 * @param sequence String that contains the parameters of that function
 */
    public MIN(String sequence) {
        super(SpreadsheetToolkit.TOKENMIN, sequence);
    }
/**
 * Function that calculates the final value of the MIN function introduced by the user from a list of floats.
 * @param list List of floats containing all the values to consider when computing a MIN function
 * @return float with the minimum float contained in the introduced list. 
 */
    @Override
    public float Calculate(LinkedList list) {
        float a, aux = 0;
        int last = list.size();
        a = (float) list.peekFirst();

        for (int i = 1; i <= last; i++) {
            aux = (float) list.pollFirst();
            if (aux < a) {
                a = aux;
            }
        }
        return a;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/**
 * Function that returns an error when a MIN function has been introduced incorrectly. 
 */
    @Override
    void getError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
