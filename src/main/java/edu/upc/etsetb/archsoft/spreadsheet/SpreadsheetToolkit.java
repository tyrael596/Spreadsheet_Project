/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.CellReference;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Cell;

/**
 *
 * @author amayabalaguer
 */
public class SpreadsheetToolkit {

    public static final int TOKENOPEN = 1;
    public static final int TOKENCLOSE = 2;
    public static final int TOKENMINUS = 3;
    public static final int TOKENPLUS = 4;
    public static final int TOKENMIN = 5;
    public static final int TOKENMAX = 6;
    public static final int TOKENPROMEDIO = 7;
    public static final int TOKENSUMA = 8;
    public static final int TOKENMULT = 9;
    public static final int TOKENDIV = 10;
    public static final int TOKENNUM = 11;
    public static final int TOKENCELLREF = 13;
    public static final int TOKENCELLRANGE = 12;
    public static final int TOKENPUNCT = 14;
    public static final int MAXCOL = 10;
    public static final int MAXROW = 10;

    public SpreadsheetToolkit() {
    }
    //Obtiene el contenido de una referencia y lo retorna como un string

    public static String getContent(String reference, Cell[][] spreadsheet) {

        int[] coordinates = new int[2];
        coordinates = CellReference.getCoordinates(reference);
        String content = "";
        try {
            content = spreadsheet[coordinates[0]][coordinates[1]].content.getContent();
        } catch (NullPointerException e) {
            System.out.println("Please cretae or load an spreadsheet");
            throw new NullPointerException();
        }

        return content;
    }

}
