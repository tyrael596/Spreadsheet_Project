/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

/**
 *
 * @author amayabalaguer
 */
public class CircularReferenceException extends Exception {

    /**
     * Creates a new instance of <code>CircularReferenceException</code> without
     * detail message.
     */
    public CircularReferenceException() {
        System.out.println("$ERROR A circular reference has been found");
    }

    /**
     * Constructs an instance of <code>CircularReferenceException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CircularReferenceException(String msg) {
        super(msg);
    }
}
