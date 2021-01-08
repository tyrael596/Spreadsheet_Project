/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula;

import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Visitador;
import java.util.LinkedList;

/**
 * Formula element abstract class that represents the different elements in a
 * formula.
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public abstract class FormulaElement {

    int token; //Token Variable int,string o otro?
    String sequence;
    boolean isFunction;

    /**
     * Returns a FormulaElement object with the given token and sequence assigned
     * @param token integer representing the formula element type
     * @param sequence string with the sequence associated to the formula element
     */
    public FormulaElement(int token, String sequence) { //Token Variable int,string o otro?
        super();
        this.token = token; //Token Variable int,string o otro?
        this.sequence = sequence;
    }

    /**
     * Returns an empty Formula Element Object
     */
    public FormulaElement() {

    }

    /**
     * the sequence getter for the Formula element
     * @return it returns a String containing the sequence of that element. 
     */
    public String getSequence() {
        return this.sequence;
    }

    /**
     *the token getter for the Formula element
     * @return it returns an int containing the token of that element.
     */

    public int getToken() {
        return this.token;
    }

    /**
     *the sequence setter for the Formula element
     * @param newSequence String containing the new sequence od the object
     */
    public void changeSequence(String newSequence) {
        this.sequence = newSequence;
        //System.out.println("changed Sequence: " +this.sequence);
    }

    /**
     *the token setter for the Formula element
     * @param newToken integer containing the new token of the object
     */
    public void changeToken(int newToken) {
        this.token = newToken;
    }

    /**
     * Function that returns true if the formula element is a function. False otherwise 
     * @return boolean containing the result of the beforesaid comparison. 
     */
    public boolean isfunction() {
        return this.isFunction;
    }

    /**
     * Setter of the function boolean. As it is only called when it is a function, it will be set to true.
     */
    public void setIsFunction() {
        this.isFunction = true;
    }

    /**
     *
     * @param visitador
     * @param input
     * @return
     */
    public abstract float acceptVisitor(Visitador visitador, LinkedList input);
}
