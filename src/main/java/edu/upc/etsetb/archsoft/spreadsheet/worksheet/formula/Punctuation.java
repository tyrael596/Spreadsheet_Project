/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula;

import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Visitador;
import java.util.LinkedList;

/**
 *
 * @author Alex
 */
public class Punctuation extends FormulaElement{


    public Punctuation(int token, String sequence) {
        super(token, sequence);
    }


  
        @Override
    public float acceptVisitor(Visitador visitador,LinkedList input) {
        return visitador.visitaPunctuation(this,input);
    }

}
