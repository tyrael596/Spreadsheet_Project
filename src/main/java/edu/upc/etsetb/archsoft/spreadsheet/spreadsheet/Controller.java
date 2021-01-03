/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.ContentFormula;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.ContentNumeric;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.ContentText;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.CellReference;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import edu.upc.etsetb.archsoft.spreadsheet.SyntaxErrorException;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownFunctionException;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownTypeException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

        if (Controller.spreadsheet.spreadsheet == null) {
            spreadsheet.createSpreadsheet();

        } else {
            char conf = VisualInterface.askConfirmation();

            if (Character.toUpperCase(conf) == 'Y') {
                System.out.println("voy a crear ");
                spreadsheet.createSpreadsheet();
            } else if (conf == 'x') {
                while (conf == 'x') {
                    System.out.println();
                    System.out.println("Plase enter a valid answer ");
                    conf = VisualInterface.askConfirmation();
                }
            }

        }
    }

    public static void load(String filename) {

        if (Controller.spreadsheet.spreadsheet == null) {
            try {
                GPIO input = new GPIO();
                spreadsheet.createSpreadsheet();
                input.loadSpreadsheet(spreadsheet,filename);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            char conf = VisualInterface.askConfirmation();

            if (Character.toUpperCase(conf) == 'Y') {
                try {
                    GPIO input = new GPIO();
                    spreadsheet.createSpreadsheet();
                    input.loadSpreadsheet(spreadsheet,filename);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (conf == 'x') {
                while (conf == 'x') {
                    System.out.println();
                    System.out.println("Plase enter a valid answer ");
                    conf = VisualInterface.askConfirmation();
                }
            }

        }
    }

    public static void readCommands(String file) throws UnknownOptionException {
        String command;
        LinkedList<String> commands = new LinkedList();
        GPIO.readTextfile(file, commands);
        command = commands.poll();
        while (command != null) {
            performAction(command);
            command = commands.poll();

        }
    }

    public static void editCell(String[] parts) throws UnknownReferenceException {
        int[] coordinates = null;
        try {
            coordinates = CellReference.getCoordinates(parts[1]);
        } catch (java.lang.NumberFormatException e) {

        }
        if (coordinates != null) {
            try {
                float number = Float.parseFloat(parts[2]);//miramos si es un numero

                editNumeric(parts[2], coordinates);
            } catch (NumberFormatException e) {

                if (parts[2].charAt(0) == '=') {

                    try {
                        editFormula(parts, coordinates);
                    } catch (SyntaxErrorException ex) {
                        //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Erroneous Formula Syntax");
                    }
                } else {

                    editText(parts[2], coordinates);
                }

            }
        } else {
            System.out.println("Enter a valid coordinate");
            throw new UnknownReferenceException();

        }

    }

    public static void validCell(String input) throws UnknownReferenceException {
        int[] coordinates = new int[2];
        try {
            coordinates = CellReference.getCoordinates(input);
        } catch (java.lang.NumberFormatException e) {

        }

        if (coordinates == null || ((coordinates[1] > SpreadsheetToolkit.MAXCOL || coordinates[0] > SpreadsheetToolkit.MAXROW) && coordinates[0] != 0 && coordinates[1] != 0)) {

            throw new UnknownReferenceException();
        }

    }

    private static void editFormula(String[] parts, int[] coordinates) throws SyntaxErrorException {
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
            //Logger.getLogger(VisualInterface.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Linea 90 for Controller");
        }
        exp.check(tokenList);
        tokenList = token.tokens;
        LinkedList<FormulaElement> auxTokens = new LinkedList<>(tokenList);
        LinkedList<FormulaElement> postfix = null;
        LinkedList<FormulaElement> contentList = tokenList;
        postfix = Postfixer.shuntingYardAlgorithm(auxTokens, spreadsheet.getSpreadsheet());

        evaluator.setFactory(factory);
        float output;
        try {
            output = evaluator.evaluate(postfix);
            System.out.println("output " + output);

            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content = new ContentFormula();
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setContent(String.valueOf(output), contentList);

            // System.out.println("cell " + spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.getContent());
        } catch (UnknownFunctionException ex2) {
            //Logger.getLogger(VisualInterface.class.getName()).log(Level.SEVERE, null, ex2);
            System.out.println("Linea 110 for Controller");
        }

    }

    private static void editNumeric(String value, int[] coordinates) {
        try {
            System.out.println("numero");
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content = new ContentNumeric();
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setContent(value);
        } catch (java.lang.NullPointerException e) {
            System.out.println("There is no spreadsheet in use");

        }

    }

    private static void editText(String value, int[] coordinates) {
        try {
            System.out.println("texto");
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content = new ContentText();
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setContent(value);
        } catch (java.lang.NullPointerException e) {
            System.out.println("There is no spreadsheet in use");

        }
    }

    public static void saveSpreadsheet(String filename) {
        GPIO output = new GPIO();
        output.exportSpreadsheet(spreadsheet, filename);

    }

    private static void performAction(String option) throws UnknownOptionException {
        String[] parts = option.split("\\ ");

        switch (parts[0].toUpperCase()) {
            case "C":
                spreadsheet.createSpreadsheet();
                break;
            case "E":
                if (parts.length < 3) {
                    throw new UnknownOptionException();
                } else {
                    // Falta comprobar que lo que me entran son coordenadas válidas pero supongamos que si
                    try {
                        Controller.validCell(parts[1]);
                        Controller.editCell(parts);
                    } catch (UnknownReferenceException e) {
                        System.out.println("Enter a valid cell Reference ");
                        break;
                    }
                }
                break;

            case "L":
                break;
            case "S":
                Controller.saveSpreadsheet(parts[1]);
                break;
            default:
                throw new UnknownOptionException();
        }

        try {
            VisualInterface.printSpreadsheet();
        } catch (java.lang.NullPointerException ex) {

        }

    }
}
