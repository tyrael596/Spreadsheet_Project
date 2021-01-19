/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula;

import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Visitador;
import java.util.LinkedList;

/**
 * Class containing the Functions used when the Formula element is a
 * Punctuation.
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class Punctuation extends FormulaElement {

    /**
     *
     * Function that creates a new Punctuation object assigning the given token
     * and sequence.
     *
     * @param token: Integer containing the assigned token
     * @param sequence Sequence containing the punctuation sign
     */
    public Punctuation(int token, String sequence) {
        super(token, sequence);
    }

    /**
     * Accept visitador function it returns the visitador of this object
     *
     * @param visitador
     * @param input
     * @return This object's visitador
     */
    @Override
    public float acceptVisitor(Visitador visitador, LinkedList input) {
        return visitador.visitaPunctuation(this, input);
    }

}
