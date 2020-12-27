/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

/**
 *
 * @author Usuario
 */
public class SyntaxErrorException extends Exception {

    public SyntaxErrorException(String msg) {
        super(msg);
    }

    public SyntaxErrorException() {

    }

}
