/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.Numeric;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import java.util.LinkedList;
import java.util.List;

/**
 * Class in charge of performing the postfixer of the formula introduced by the
 * user
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class Postfixer {

    List formulaList;
    static SpreadsheetToolkit toolkit = new SpreadsheetToolkit();
    static LinkedList<String> dependentCells;

    /**
     * This functions implements the Shunting Yard Algorithm to return a list
     * containing all the different elements in the proper order to be
     * calculated.
     *
     * @param input Linked List containing all the tokens of the formula to be
     * computed.
     * @param spreadsheet Cell object matrix containing the current Spreadsheet.
     * @return Linked list with the tokens rearranged according to the Shunting
     * Yard Algorithm.
     */
    public static LinkedList shuntingYardAlgorithm(LinkedList<FormulaElement> input, Cell[][] spreadsheet) { // Consideramos que la función es válida 
        LinkedList<FormulaElement> operatorStack = new LinkedList();
        LinkedList<FormulaElement> numbersQueue = new LinkedList();
        LinkedList<FormulaElement> auxiliarList = new LinkedList();
        FormulaElement aux;
        FormulaElement aux2;
        dependentCells = new LinkedList();
        float number;
        // si es un simbolo y tiene menos preferencia que el ultimo del stack entonces saco el del stack y lo meto con los numeros y pongo el de menor preferencia en el stack 
        while (input.isEmpty() == false) {

            aux = input.poll();
            // intentamos convertirlo a un float 

            try {
                number = Float.parseFloat(aux.getSequence());
                numbersQueue.addLast(aux);//si es un numero lo pongo en la cola de numeros en la última posición 

            } catch (NumberFormatException e) {
                //primero miro si se trata de una función
                if (aux.isfunction()) {
                    numbersQueue.addLast(aux);
                    auxiliarList = getFunctionArguments(input, spreadsheet);
                    while (!auxiliarList.isEmpty()) {
                        aux = auxiliarList.pop();
                        numbersQueue.addLast(aux);
                    }
                } else if (aux.getToken() == SpreadsheetToolkit.TOKENCLOSE) {
                    aux2 = operatorStack.pop();
                    while (aux2 != null && aux2.getToken() != SpreadsheetToolkit.TOKENOPEN) {
                        numbersQueue.addLast(aux2);
                        aux2 = operatorStack.pop();
                    }
                } else if (aux.getToken() == SpreadsheetToolkit.TOKENCELLREF) {
                    try {
                        dependentCells.push(aux.getSequence());
                        aux2 = new Numeric(11, String.valueOf(SpreadsheetToolkit.getContent(aux.getSequence(), spreadsheet)));
                        numbersQueue.addLast(aux2);
                    } catch (NullPointerException ex ) {
                        System.out.println("Please create or load an spreadsheet");
                    }
 

            }else {
                    aux2 = operatorStack.peekFirst();
                    if (aux2 != null) {
                        //si hay algo hay que mirar las precedencias
                        if (aux.getToken() >= aux2.getToken() || aux.getToken() == SpreadsheetToolkit.TOKENOPEN) {

                            operatorStack.push(aux);

                        } else if (aux.getToken() < aux2.getToken()) {

                            aux2 = operatorStack.pop();
                            numbersQueue.addLast(aux2);
                            operatorStack.push(aux);
                        }
                    } else {

                        operatorStack.push(aux);
                    }
                }

        }
    }
    //llegados al final toca pasarlos todos 
    //  System.out.println("vacio operadores" + operatorStack.size());

    while (!operatorStack.isEmpty () 
        ) {
            numbersQueue.addLast(operatorStack.pollFirst());
    }

    return numbersQueue ;
}

/**
 * Function called whenever a function is found and that returns a list of all
 * the arguments of that function. If a reference is found, the value of the
 * cell content is returned in that list.
 *
 * @param input Linked list of the tokens of the formula inputed by the user.
 * @param spreadsheetCell object matrix containing the current Spreadsheet.
 * @return list of all the arguments of that function
 */
static LinkedList getFunctionArguments(LinkedList<FormulaElement> input, Cell[][] spreadsheet) {
        LinkedList<FormulaElement> arguments = new LinkedList();
        LinkedList<FormulaElement> auxiliarList = new LinkedList();
        int precedence = 0;
        FormulaElement aux;
        aux = input.poll();
        precedence = aux.getToken();

        while (precedence != SpreadsheetToolkit.TOKENCLOSE) {
            if (aux.isfunction()) { //tengo una función 
                arguments.addLast(aux);
                auxiliarList = getFunctionArguments(input, spreadsheet);

                while (!auxiliarList.isEmpty()) {
                    arguments.addLast(auxiliarList.pop());
                }
                aux = input.poll();
                if (aux != null) {
                    precedence = aux.getToken();
                }
            }
            if (aux.getToken() == SpreadsheetToolkit.TOKENCELLREF) {
                dependentCells.push(aux.getSequence());
                aux = new Numeric(11, String.valueOf(SpreadsheetToolkit.getContent(aux.getSequence(), spreadsheet)));
            }
            arguments.addLast(aux);//si es un numero lo pongo en la cola de numeros en la última posición  
            aux = input.poll();
            if (aux != null) {
                precedence = aux.getToken();
            }

        }
        arguments.addLast(aux);
        return arguments;
    }

    /**
     * getter for the dependentCells list
     *
     * @return dependentCells list
     */
    public static LinkedList getDependencies() {
        return dependentCells;
    }

}
