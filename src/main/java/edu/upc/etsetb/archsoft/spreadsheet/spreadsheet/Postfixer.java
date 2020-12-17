/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.Numeric;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Amaya
 */
public class Postfixer {

    List formulaList;
    static SpreadsheetToolkit toolkit = new SpreadsheetToolkit();
//Habra que hacerlo static seguramente

    public static LinkedList shuntingYardAlgorithm(LinkedList<FormulaElement> input, Cell[][] spreadsheet) { // Consideramos que la función es válida 
        LinkedList<FormulaElement> operatorStack = new LinkedList();
        LinkedList<FormulaElement> numbersQueue = new LinkedList();
        LinkedList<FormulaElement> auxiliarList = new LinkedList();
        FormulaElement aux, aux2;

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
                    aux2 = new Numeric(11, String.valueOf(SpreadsheetToolkit.getContent(aux.getSequence(), spreadsheet)));
                    numbersQueue.addLast(aux2);
                } else {
                    aux2 = operatorStack.peekFirst();
                    if (aux2 != null) {
                        //si hay algo hay que mirar las precedencias
                        if (aux.getToken() >= aux2.getToken()) {
                            operatorStack.push(aux);
                        } else {
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
        while (!operatorStack.isEmpty()) {
            numbersQueue.addLast(operatorStack.pollFirst());
        }

        return numbersQueue;
    }

    //falta añadir el caso en el que tengo una función con paréntesis ahí en medio
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

}
