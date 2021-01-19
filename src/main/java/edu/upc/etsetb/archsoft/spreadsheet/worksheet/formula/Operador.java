/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula;

import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Visitador;
import java.util.LinkedList;

/**
 * Class containing the Functions used when the Formula element is an operator.
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class Operador extends FormulaElement {

    /**
     * *
     * Function that creates a new Operador object assigning the given token and
     * sequence.
     *
     * @param token: Integer containing the assigned token
     * @param sequence Sequence containing the operator
     */
    public Operador(int token, String sequence) {
        super(token, sequence);
    }

    /**
     * *
     * Function that creates an empty Operador object.
     */
    public Operador() {
    }

    /**
     * *
     * Function that creates a new Operador object without the token
     *
     *
     * @param op string containing the operator itself
     */
    public Operador(String op) {

        this.sequence = op;
    }

    /**
     * Function that performs the operation assigned to every operator. 
     * It returns a float containing the operation's result. 
     * @param operand1: float containing the first operand
     * @param operand2 float containing the second operand
     * @return float with the operation's result 
     */
    public float calculate(float operand1, float operand2) {

        switch (sequence) {
            case "+":
                return operand1 + operand2;
            case "-":

                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            default:
                return operand1 / operand2;
        }

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
        return visitador.visitaOperador(this, input);
    }

}
