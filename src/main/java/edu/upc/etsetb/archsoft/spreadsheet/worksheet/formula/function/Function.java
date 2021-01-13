/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.function;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownFunctionException;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Visitador;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that represents a type of Formula Element containing a formula. 
 * @author Alex Eslava and Amaya Balaguer
 */
public abstract class Function extends FormulaElement {

    static String sequence = "";
    static String range = "";
/**
 * Function that calculates the result of the given formula
 * @param list list containing all the formula parameters. 
 * @return float representing the result of the given formula. 
 */
    public abstract float Calculate(LinkedList list);
/**
 * Void that indicates that an error was found in that formula. 
 */
    abstract void getError();
/**
 * function that returns a Function object containing the given token and string
 * @param token integer with the numeric representation of the given function type. 
 * @param sequence String containing all the function elements. 
 */
    public Function(int token, String sequence) {

        super(token, sequence);
        setIsFunction();
    }
/**
 * function implementing the Visitador function. 
 * @param visitador
 * @param input
 * @return 
 */
    @Override
    public float acceptVisitor(Visitador visitador, LinkedList input) {
        try {
            return visitador.visitaFunction(this, input);
        } catch (UnknownFunctionException ex) {
            Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
