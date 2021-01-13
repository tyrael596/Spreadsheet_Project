/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula;

import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Visitador;
import java.util.LinkedList;

/**
 * Class representing the Cell Reference Formula Element. It contains all the functions related to this type of formula element. 
 * @author Alex Eslava and Amaya Balaguer
 */
public class CellReference extends FormulaElement {

    static String letter(int i) {
        return i < 0 ? "" : letter((i / 26) - 1) + (char) (65 + i % 26);
    }
/**
 * Function that given a cell reference in alphanumeric format, returns its numerical representation in a 2 position integer array.
 * @param reference cell reference in alphanumeric format
 * @return integer array containing the numerical representation of the coordinate. For example A1 would return a 1 1 as A is the first column and 1 the first row. 
 */
    public static int[] getCoordinates(String reference) {
        StringBuffer letter = new StringBuffer();
        StringBuffer num = new StringBuffer();
        int aux;
        int col = 0;
        int row = 0;
        int[] coordinates = new int[2];
        for (int i = 0; i < reference.length(); i++) {
            if (Character.isDigit(reference.charAt(i))) {
                num.append(reference.charAt(i));
            } else if (Character.isAlphabetic(reference.charAt(i))) {
                letter.append(reference.charAt(i));
            }
        }
        for (int e = 0; e < letter.length(); e++) {
            aux = (int) letter.charAt(e);
            col = col * 26 + (aux - 'A') + 1;
        }
        try {
            row = Integer.parseInt(num.toString());
            coordinates[0] = row;
            coordinates[1] = col;
        } catch (NumberFormatException e) {
            return null;
        }
        return coordinates;
    }
/**
 *  Function that returns the first col of the spreadsheet with the letters assigned to it. 
 * It will vary its length according to the maximum length of the spreadsheet. if Y is reached, it will keep counting with an AA.
 * @return  string containing the first row of the spreadhsheet
 */
    public static String[] getString() {
        int i;
        String[] content = new String[SpreadsheetToolkit.MAXCOL];
        for (i = 0; i < SpreadsheetToolkit.MAXCOL - 1; ++i) {
            content[i + 1] = letter(i);
        }
        return content;
    }
/**
 * Function that returns a CellReference object with the corresponding token and the given sequence. 
 * @param token integer with the numerical representation of the Cell Reference type
 * @param sequence Sequence containing the cell reference in alphanumeric format
 */
    public CellReference(int token, String sequence) {
        super(token, sequence);
    }
//%%%%%%%%%%%%%%%%%%%%%%%%%
    @Override
    public float acceptVisitor(Visitador visitador,LinkedList input) {
        return visitador.visitaCellReference(this,input);
    }

    
}
