/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

/**
 * Used when we can't find matching function to given input
 * @author Alex Eslava and Amaya Balaguer
 */
public class UnknownFunctionException extends Exception {

    /**
     * Creates a new instance of <code>UnknownFunctionException</code> without
     * detail message.
     */
    public UnknownFunctionException() {
    }

    /**
     * Constructs an instance of <code>UnknownFunctionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnknownFunctionException(String msg) {
        super(msg);
    }
}
