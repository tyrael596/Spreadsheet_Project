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
 * Class in charge of performing the evaluation of the formula introduced by the user
 * @author Alex Eslava and Amaya Balaguer
 */
public class PostfixEvaluator {

    // Acceso a lista de cells afectadas en spreadsheet
    private SpreadsheetFactory factory = new SpreadsheetFactory();
    private static Visitador visitador = new Visitador();
/**
 * It sets the factory of the PostfixEvaluator object
 * @param factory SpreadsheetFactory object that gets assigned to the factory attribute
 */
    public void setFactory(SpreadsheetFactory factory) {
        this.factory = factory;
    }
/**
 * It sets the visitador of the PostfixEvaluator object
 * @param visitador SpreadsheetFactory object that gets assigned to the factory attribute
 */
    public void setVisitador(Visitador visitador) {
        this.visitador = visitador;
    }
/**
 * Function that evaluates the given token and uses the visitador to obtain the computed value. 
 * @param input list of tokens with the shunting yard algorithm format
 * @return result of the formula calculation. 
 * @throws UnknownFunctionException 
 */
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
