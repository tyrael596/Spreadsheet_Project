/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function;
import java.util.LinkedList;
/**
 *
 * @author Alex
 */
public abstract class Function {
    
    static String inside ="";
    static String range = "";
    public abstract float Calculate(LinkedList list);
    abstract void getError();

    
}
