/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

/**
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class UnknownOptionException extends Exception {

    /**
     * Creates a new instance of <code>NotAValidOption</code> without detail
     * message.
     */
    public UnknownOptionException() {
    }

    /**
     * Constructs an instance of <code>NotAValidOption</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public UnknownOptionException(String msg) {
        super(msg);
    }
}
