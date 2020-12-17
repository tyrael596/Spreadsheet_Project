/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.CellReference;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.Numeric;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.Operador;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.Punctuation;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.Function;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.MAX;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.MIN;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.PROMEDIO;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.SUMA;

/**
 *
 * @author amayabalaguer
 */
public class SpreadsheetFactory {

    public Function createFunction(String functionName) throws UnknownFunctionException {
        //Switch que discrimina lo que viene
        switch (functionName.toLowerCase()) {

            case "min":
                return new MIN(functionName);
            case "max":
                return new MAX(functionName);

            case "promedio":
                return new PROMEDIO(functionName);

            case "suma":
                return new SUMA(functionName);
            default:
                
                throw new UnknownFunctionException("Function " + functionName + " is unknown");

        }

    }

    public Operador createOperador(String op) {

        return new Operador(op);
    }

    public FormulaElement createFormulaElement(int token, String tok) throws UnknownTypeException, UnknownFunctionException {
        switch (token) {
            case 13:
                return new CellReference(token, tok);
            case 11:
                return new Numeric(token, tok);
            case 3:
            case 4:
            case 9:
            case 10:
                return new Operador(token, tok);
            case 5:
            case 6:
            case 7:
            case 8:
                return this.createFunction(tok);
            case 1:
            case 2:
            case 14:
                return this.createPunctuation(token, tok);
            default:
                throw new UnknownTypeException();
        }
    }

    private FormulaElement createPunctuation(int token, String op) {
        return new Punctuation(token, op);
    }

}
