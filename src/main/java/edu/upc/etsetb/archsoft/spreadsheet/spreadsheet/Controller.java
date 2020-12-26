/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.CellFormula;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.Main;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownFunctionException;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownTypeException;
import static java.lang.Integer.parseInt;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amayabalaguer
 */
public class Controller {

    static SpreadsheetFactory factory = new SpreadsheetFactory();
    static Spreadsheet spreadsheet = new Spreadsheet();

    public static void create() {
        spreadsheet.createSpreadsheet();

    }

    public static void editCell(String[] parts) {
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

        } catch (UnknownTypeException | UnknownFunctionException ex) {
            Logger.getLogger(VisualInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                int[] coordinates = SpreadsheetToolkit.getCoordinates(parts[1]);
                spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content = new CellFormula(); 
                spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setContent(String.valueOf(output),postfix);        
                
            } catch (UnknownFunctionException ex) {
                Logger.getLogger(VisualInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            //store the new value

        } catch (ExpressionCleaner.SyntaxErrorException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void validCell(String input) throws UnknownReferenceException {
        int[] coordinates = new int[2];

        coordinates = SpreadsheetToolkit.getCoordinates(input);

        if (coordinates[1] > SpreadsheetToolkit.MAXCOL || coordinates[0] > SpreadsheetToolkit.MAXROW) {

            throw new UnknownReferenceException();
        }

    }
}
