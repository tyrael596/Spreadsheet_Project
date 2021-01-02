/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula;

import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Visitador;
import java.util.LinkedList;

/**
 *
 * @author Alex
 */
public class CellReference extends FormulaElement {

    static String letter(int i) {
        return i < 0 ? "" : letter((i / 26) - 1) + (char) (65 + i % 26);
    }

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

    public static String[] getString() {
        int i;
        String[] content = new String[SpreadsheetToolkit.MAXCOL];
        for (i = 0; i < SpreadsheetToolkit.MAXCOL - 1; ++i) {
            content[i + 1] = letter(i);
        }
        return content;
    }

    public CellReference(int token, String sequence) {
        super(token, sequence);
    }

    @Override
    public float acceptVisitor(Visitador visitador,LinkedList input) {
        return visitador.visitaCellReference(this,input);
    }

    
}
