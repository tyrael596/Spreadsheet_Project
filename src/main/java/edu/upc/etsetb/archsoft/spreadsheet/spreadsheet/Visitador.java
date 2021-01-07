/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.CellReference;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.Numeric;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.Operador;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.Punctuation;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.Function;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownFunctionException;
import java.util.LinkedList;

/**
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class Visitador {

    private SpreadsheetFactory factory = new SpreadsheetFactory();
    LinkedList<Float> stack = new LinkedList();
    private FormulaElement aux;
/**
 * Visitador of the Cell Reference object. It returns the value contained in that cell reference
 * @param aThis 
 * @param input
 * @return 
 */
    public float visitaCellReference(CellReference aThis, LinkedList<FormulaElement> input) {
        aux = input.pop();
        float number = Float.parseFloat(aux.getSequence());
        stack.push(number);
        return number;

    }
/**
 * 
 * @param aThis
 * @param input
 * @return 
 */
    public float visitaNumeric(Numeric aThis, LinkedList<FormulaElement> input) {
        aux = input.pop();
        float number = Float.parseFloat(aux.getSequence());
        stack.push(number);
        return number;
    }
/**
 * 
 * @param aThis
 * @param input
 * @return 
 */
    public float visitaOperador(Operador aThis, LinkedList<FormulaElement> input) {
        aux = input.pop();
        float output = 0;
        Operador operador = this.factory.createOperador(aux.getSequence());
        float operand2 = stack.pop();
        float operand1 = stack.pop();

        if (input.isEmpty() == false) {
            float number = operador.calculate(operand1, operand2);
            stack.push(number);
        } else {

            output = operador.calculate(operand1, operand2);
        }
        return output;
    }
/**
 * 
 * @param aThis
 * @param input
 * @return
 * @throws UnknownFunctionException 
 */
    public float visitaFunction(Function aThis, LinkedList<FormulaElement> input) throws UnknownFunctionException {
        aux = input.pop();
        float number = calculateFunction(input, aux.getSequence());
        stack.push(number);
        return number;
    }
/**
 * 
 * @param input
 * @param type
 * @return
 * @throws UnknownFunctionException 
 */
    private float calculateFunction(LinkedList<FormulaElement> input, String type) throws UnknownFunctionException {
        float output = 0;
        float num = 0;
        FormulaElement aux;
        input.pop();//fuera el primer parentesis
        aux = input.pop();
        LinkedList<Float> calculate = new LinkedList();

        while (aux.getToken() != SpreadsheetToolkit.TOKENCLOSE) {

            if (aux.getToken() == SpreadsheetToolkit.TOKENNUM) {
                calculate.addLast(Float.parseFloat(aux.getSequence()));
                aux = input.pop();
            } else if (aux.getToken() == SpreadsheetToolkit.TOKENOPEN) {//me encuentro un (                              
                calculate.addLast(num);
                aux = input.pop();
            } else if (aux.getToken() == SpreadsheetToolkit.TOKENPUNCT) {
                aux = input.pop();
            } else if (aux.getToken() == SpreadsheetToolkit.TOKENMIN | aux.getToken() == SpreadsheetToolkit.TOKENMAX | aux.getToken() == SpreadsheetToolkit.TOKENPROMEDIO | aux.getToken() == SpreadsheetToolkit.TOKENSUMA) {
                num = calculateFunction(input, aux.getSequence());
                calculate.addLast(num);
                aux = input.pop();
            }
        }
        if (aux.getToken() == SpreadsheetToolkit.TOKENCLOSE) {
            Function function = this.factory.createFunction(type);
            output = function.Calculate(calculate);
        }
        return output;
    }
/**
* 
* @param aThis
* @return 
 */
    public float visitaPunctuation(Punctuation aThis, LinkedList input) {
        return 1;
    }

}
