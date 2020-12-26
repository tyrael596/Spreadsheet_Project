/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.CellFormula;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.CellNumeric;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.CellText;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.Main;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
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
    static Spreadsheet spreadsheet = new Spreadsheet();

    public static void create() {

        spreadsheet.createSpreadsheet();

    }

    public static void editCell(String[] parts) {
        System.out.print("Enro aquí" + parts[2].charAt(0));
        
        int[] coordinates = SpreadsheetToolkit.getCoordinates(parts[1]);
        try {
            float number = Float.parseFloat(parts[2]);//miramos si es un numero
            System.out.print("Number");
            editNumeric(parts[2], coordinates);
        } catch (NumberFormatException e) {
            System.out.print("not a nmber");

            if (parts[2].charAt(0) == '='){
                
                System.out.print("parts" + parts[2]);
                editFormula(parts, coordinates);
            } else {
                System.out.print("String");
                editText(parts[2], coordinates);
            }

        }

    }

    public static void validCell(String input) throws UnknownReferenceException {
        int[] coordinates = new int[2];

        coordinates = SpreadsheetToolkit.getCoordinates(input);

        if (coordinates[1] > SpreadsheetToolkit.MAXCOL || coordinates[0] > SpreadsheetToolkit.MAXROW) {

            throw new UnknownReferenceException();
        }

    }

    private static void editFormula(String[] parts, int[] coordinates) {
        ExpressionCleaner exp = new ExpressionCleaner();
        PostfixEvaluator evaluator = new PostfixEvaluator();
        //comprobamos que las coordenadas son validas
        //realizamos el cálculo
        String formula = parts[2].substring(1);;
        

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

                spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content = new CellFormula();
                spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setContent(String.valueOf(output), postfix);

            } catch (UnknownFunctionException ex) {
                Logger.getLogger(VisualInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            //store the new value

        } catch (ExpressionCleaner.SyntaxErrorException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void editNumeric(String value, int[] coordinates) {
        spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content = new CellNumeric();
        spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setContent(value);

    }

    private static void editText(String value, int[] coordinates) {
        spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content = new CellText();
        spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setContent(value);

    }
}
