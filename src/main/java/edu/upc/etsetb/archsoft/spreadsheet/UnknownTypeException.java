/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

/**
 *
 * @author amayabalaguer
 */
public class UnknownTypeException extends Exception {

    /**
     * Creates a new instance of <code>UnknownTypeException</code> without
     * detail message.
     */
    public UnknownTypeException() {
    }

    /**
     * Constructs an instance of <code>UnknownTypeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnknownTypeException(String msg) {
        super(msg);
    }
}
