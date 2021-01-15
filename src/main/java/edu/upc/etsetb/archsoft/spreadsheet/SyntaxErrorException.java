/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

/**
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class SyntaxErrorException extends Exception {

    /**
     * This exception is called when we detect an user input has a syntax error
     * such errors can be from invalid token or from syntactically nonsensical
     * input
     */

    public SyntaxErrorException(String msg) {
        super(msg);
    }

    public SyntaxErrorException() {

    }

}
