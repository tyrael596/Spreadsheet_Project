/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.Main;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownFunctionException;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownTypeException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amayabalaguer
 */
public class Controller {

   static SpreadsheetFactory factory = new SpreadsheetFactory();
   static Spreadsheet spreadsheet;

   
   public static void create(){
       spreadsheet.createSpreadsheet();
       
   }
   
    public static void editCell(String[] parts ) {
        ExpressionCleaner exp = new ExpressionCleaner();   
        PostfixEvaluator evaluator = new PostfixEvaluator();
        //comprobamos que las coordenadas son validas
        //realizamos el cálculo
        String formula = parts[2];

        Tokenizer token = new Tokenizer();
        token.setFactory(factory);
        token.addTokens(token);
        LinkedList<FormulaElement> tokenList = new LinkedList<>();
        try {
            token.getTokens(formula);
            try {
                exp.check(tokenList);
                tokenList = token.tokens;
                LinkedList<FormulaElement> auxTokens = new LinkedList<>(tokenList);
                LinkedList<FormulaElement> postfix = null;
                

                
                postfix = Postfixer.shuntingYardAlgorithm(auxTokens, spreadsheet.getSpreadsheet());
                                
                
                evaluator.setFactory(factory);
                float output;
                try {
                    output = evaluator.evaluate(postfix);
                    System.out.println("output " + output);
                    
                } catch (UnknownFunctionException ex) {
                    Logger.getLogger(VisualInterface.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (ExpressionCleaner.SyntaxErrorException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (UnknownTypeException | UnknownFunctionException ex) {
            Logger.getLogger(VisualInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
