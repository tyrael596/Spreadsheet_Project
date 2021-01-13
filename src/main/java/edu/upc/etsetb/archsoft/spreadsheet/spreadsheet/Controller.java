/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.ContentFormula;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.ContentNumeric;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.ContentText;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.CellReference;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import edu.upc.etsetb.archsoft.spreadsheet.SyntaxErrorException;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownFunctionException;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownTypeException;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Postfixer.dependentCells;

/**
 * Class that controls the general behavior of the system. It is in charge of
 * calling the right functions at every step
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class Controller {

    static SpreadsheetFactory factory = new SpreadsheetFactory();
    static Spreadsheet spreadsheet = new Spreadsheet();
    static Postfixer postfixer = new Postfixer();

    /**
     * Function called when the user wants to create a new spreadsheet. When
     * called, it checks if a spreadsheet is already in use before asking or not
     * for confirmation.
     */
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

    /**
     * Function that loads a new spreadsheet
     *
     * @param filename String that contains the path of the to-be-loaded file
     */
    public static void load(String filename) {

        if (Controller.spreadsheet.spreadsheet == null) {
            try {
                FileActor input = new FileActor();
                spreadsheet.createSpreadsheet();
                input.loadSpreadsheet(spreadsheet, filename);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            char conf = VisualInterface.askConfirmation();

            if (Character.toUpperCase(conf) == 'Y') {
                try {
                    FileActor input = new FileActor();
                    spreadsheet.createSpreadsheet();
                    input.loadSpreadsheet(spreadsheet, filename);
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

    /**
     * Function called when the user wants to read all the following commands
     * from a text file. This function reads the file that contains the commands
     * and sends them to the PerformAction function one by one.
     *
     * @param file String containing the path of the file containing the
     * commands to execute.
     * @throws UnknownOptionException Exception thrown whenever there is an
     * unknown command in the file.
     */
    public static void readCommands(String file) throws UnknownOptionException {
        String command;
        LinkedList<String> commands = new LinkedList();
        FileActor.readTextfile(file, commands);
        command = commands.poll();
        while (command != null) {
            performAction(command);
            command = commands.poll();

        }
    }

    /**
     * Function checks if the coordinates are valid and edits the content of the
     * cell according to it's new type.
     *
     * @param parts String containing the whole user's input. This string
     * contains the cell reference as well as the new value or formula.
     * @throws UnknownReferenceException
     */
    public static void editCell(String[] parts) throws UnknownReferenceException {
        int[] coordinates = null;
        try {
            coordinates = CellReference.getCoordinates(parts[1]);
        } catch (java.lang.NumberFormatException e) {

        }
        if (coordinates != null) {
            try {
                float number = Float.parseFloat(parts[2]);//miramos si es un numero

                editNumeric(parts[2], parts[1]);
            } catch (NumberFormatException e) {

                if (parts[2].charAt(0) == '=') {

                    try {
                        editFormula(parts, parts[1]);
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

    /**
     * Function call to check if the coordinates are valid.
     *
     * @param input Coordinate inputed by the user
     * @throws UnknownReferenceException
     */
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

    /**
     * Void that manages the process of computing a formula. It first checks if
     * it is correct, then calls the tokenizer and the functions needed to
     * perform the calculations.
     *
     * @param value String that contains the to-be-stored value
     * @param coordinates Array that contains the cell coordinates
     * @throws SyntaxErrorException Exception thrown when there is an error in
     * the formula.
     */
    private static void editFormula(String[] parts, String cellReference) throws SyntaxErrorException {
        ExpressionCleaner exp = new ExpressionCleaner();
        PostfixEvaluator evaluator = new PostfixEvaluator();
        System.out.println(cellReference);
        int[] coordinates = CellReference.getCoordinates(cellReference);
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
        LinkedList<String> dependencies = null;
        LinkedList<FormulaElement> contentList = tokenList;
        postfix = postfixer.shuntingYardAlgorithm(auxTokens, spreadsheet.getSpreadsheet());
        CircularReferencer.updateReferences(postfixer.getDependencies(), cellReference, spreadsheet);
        // Aqui comprobamos si hay alguna referencia circular
        evaluator.setFactory(factory);
        float output;
        try {

            output = evaluator.evaluate(postfix);
            dependentCells = new LinkedList();
            dependencies = new LinkedList();
            dependentCells=spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.getDependentCells();
            dependencies=spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.getDependencies();
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content = new ContentFormula();
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setContent(String.valueOf(output), contentList);
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setDependentCells(dependentCells);
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setDependencies(dependencies);
            //Update de todas las demas celdas
           
            updateDependentCells(spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.getDependentCells(), coordinates[0], coordinates[1]);
            // System.out.println("cell " + spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.getContent());
        } catch (UnknownFunctionException ex2) {
            //Logger.getLogger(VisualInterface.class.getName()).log(Level.SEVERE, null, ex2);
            System.out.println("Linea 110 for Controller");
        }

    }

    /**
     * Function called whenever the user wants to input a number in a cell. It
     * edits the cell content type to be CotentNumeric and stores the indicated
     * value in it.
     *
     * @param value String that contains the to-be-stored value
     * @param coordinates Array that contains the cell coordinates
     */
    private static void editNumeric(String value, String cell) {
        try {
             int[] coordinates = CellReference.getCoordinates(cell);
     
            CircularReferencer.deleteReferences( cell,  spreadsheet);
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content = new ContentNumeric();
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setContent(value);
        } catch (java.lang.NullPointerException e) {
            System.out.println("There is no spreadsheet in use");

        }

    }

    /**
     * Function called whenever the user wants to input text in a cell. It edits
     * the cell content type to be CotentText and stores the indicated value in
     * it.
     *
     * @param value String that contains the to-be-stored value
     * @param coordinates Array that contains the cell coordinates
     */
    private static void editText(String value, int[] coordinates) {
        try {
            System.out.println("texto");
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content = new ContentText();
            spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setContent(value);
        } catch (java.lang.NullPointerException e) {
            System.out.println("There is no spreadsheet in use");

        }
    }

    /**
     * Void that saves the current spreadsheet
     *
     * @param filename String containing the file name given by the user/
     */
    public static void saveSpreadsheet(String filename) {
        FileActor output = new FileActor();
        output.exportSpreadsheet(spreadsheet, filename);

    }

    /**
     * Void in charge of calling the right function according to the action read
     * on the file.
     *
     * @param option action read on the file
     * @throws UnknownOptionException exception thrown when the action is
     * unknown
     */
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
                Controller.load(parts[1]);
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
/**
 * Void that checks if the edited cell has any dependency (another cell that includes the current one in its formula) and recalculates its new value with the modification
 * @param dependencies list of dependent cells
 * @param row integer containing the cell's row
 * @param col  integer containing the cell's column
 */
    private static void updateDependentCells(LinkedList<String> dependencies, int row, int col) {
        Postfixer newPostfixer = new Postfixer();
        
        LinkedList<String> auxDependencies = new LinkedList<>(dependencies);
        int[] coordinates;
        float output;
        LinkedList<FormulaElement> postfix = null;
        System.out.println("no se que estoy haciendo" + dependencies);
        while (!auxDependencies.isEmpty()) {

            String cell = auxDependencies.pop();
            coordinates = CellReference.getCoordinates(cell);
            newPostfixer = new Postfixer();
            
            if (spreadsheet.getSpreadsheet()[row][col].content.getFormula()!= null) {
                
                output = 0; 
                LinkedList<FormulaElement> aux = new LinkedList<>(spreadsheet.getSpreadsheet()[row][col].content.getFormula());
                postfix = newPostfixer.shuntingYardAlgorithm(aux, spreadsheet.getSpreadsheet());
                try {
                    output = PostfixEvaluator.evaluate(postfix); //Logger.getLogger(VisualInterface.class.getName()).log(Level.SEVERE, null, ex2);
    
                    
                } catch (UnknownFunctionException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.setContent(String.valueOf(output));
              
                if (!spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.getDependentCells().isEmpty()) {
 
                    updateDependentCells(spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.getDependentCells(), coordinates[0], coordinates[1]);
                }
            }

            // System.out.println("cell " + spreadsheet.spreadsheet[coordinates[0]][coordinates[1]].content.getContent());
        }
    }
}
