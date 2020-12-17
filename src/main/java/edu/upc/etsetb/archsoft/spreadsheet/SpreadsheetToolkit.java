/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

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

    public SpreadsheetToolkit() {
    }
    //Obtiene el contenido de una referencia y lo retorna como un string

    public static String getContent(String reference, Cell[][] spreadsheet) {
        
        StringBuffer letter = new StringBuffer();
        StringBuffer num = new StringBuffer();
        int row, col = 0, aux;

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

        row = Integer.parseInt(num.toString());

        //col = Integer.parseInt(letter.toString());
        //System.out.println("number " + num); 
        //System.out.println("letter " + letter);
       // System.out.println(" Row " + row);
        //System.out.println("col " + col);
       // System.out.println("content " + spreadsheet[row][col].content.getContent());
        //System.out.println("convertedCol " + col); 
        return spreadsheet[row][col].content.getContent();

    }
}
