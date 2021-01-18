/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.CellReference;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.Numeric;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.Operador;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.Punctuation;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.function.Function;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownFunctionException;
import java.util.LinkedList;

/**
 * Visitor class created for the multiple functionalities required for the spreadsheet
 * @author Alex Eslava and Amaya Balaguer
 */
public class Visitador {

    private SpreadsheetFactory factory = new SpreadsheetFactory();
    LinkedList<Float> stack = new LinkedList();
    private FormulaElement aux;

    /**
     * Visitador of the Cell Reference object. It returns the value contained in
     * that cell reference
     *
     * @param aThis cell reference which we act upon
     * @param input linkedList to obtain the numeric float value from the cell
     * @return number added to the list is returned as float
     */
    public float visitaCellReference(CellReference aThis, LinkedList<FormulaElement> input) {
        aux = input.pop();
        float number = Float.parseFloat(aux.getSequence());
        stack.push(number);
        return number;

    }

    /**
     *Visitador of a  Numeric object. Returns contained numeric in the aThis cell reference
     * @param aThis cell reference which we act upon
     * @param input linkedlist upon which we act
     * @return numeric value we return
     */
    public float visitaNumeric(Numeric aThis, LinkedList<FormulaElement> input) {
        aux = input.pop();
        float number = Float.parseFloat(aux.getSequence());
        stack.push(number);
        return number;
    }

    /**
     *Visitador of operator object. Returns calculus of the operands in list and returns float calculated
     * @param aThis operador being used
     * @param input input linkedlist upon which we perform calculations
     * @return result of operation 
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
     *Visitador of a function object. Returns result of respective function calculus
     * @param aThis given function
     * @param input input list of formulaelements on which we're calculating the postfix
     * @return result of given function calculus
     * @throws UnknownFunctionException if function type is unknown 
     */
    public float visitaFunction(Function aThis, LinkedList<FormulaElement> input) throws UnknownFunctionException {
        aux = input.pop();
        float number = calculateFunction(input, aux.getSequence());
        stack.push(number);
        return number;
    }

    /**
     *Calculation of function depending on the type of function
     * @param input list of formulaElements that go into the calculus proccess
     * @param type type of function we're doing
     * @return result of the calculus performed
     * @throws UnknownFunctionException if function type is unknown 
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
     *Visitador for punctuation object
     * @param aThis punctuation object in case
     * @return 1 to postfixer to evaluate this
     */
    public float visitaPunctuation(Punctuation aThis, LinkedList input) {
        return 1;
    }

}
