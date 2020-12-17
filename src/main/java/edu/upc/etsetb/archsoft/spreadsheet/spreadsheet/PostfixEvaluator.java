/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownFunctionException;
import java.util.LinkedList;

/**
 *
 * @author amayabalaguer
 */
public class PostfixEvaluator {

    // Acceso a lista de cells afectadas en spreadsheet
    private SpreadsheetFactory factory = new SpreadsheetFactory();
    private static Visitador visitador = new Visitador();

    public void setFactory(SpreadsheetFactory factory) {
        this.factory = factory;
    }

    public void setVisitador(Visitador visitador) {
        this.visitador = visitador;
    }

    public static float evaluate(LinkedList<FormulaElement> input) throws UnknownFunctionException {

         float  output = 0;
        LinkedList<Float> stack = new LinkedList();
        FormulaElement aux;

        //aux = input.pop(); 
        while (input.isEmpty() == false) {

            aux = input.peekFirst();

             output = aux.acceptVisitor(visitador, input);
        }

        return output;
    }

}
