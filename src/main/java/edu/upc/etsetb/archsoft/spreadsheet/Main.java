/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.CellFormula;

/**
 *
 * @author Alex
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
            CellFormula cellFormula = new CellFormula();
            
            cellFormula.add("\\(", 1); // open bracket
            cellFormula.add("\\)", 2); // close bracket
            cellFormula.add("[-]", 3); 
            cellFormula.add("[+]", 4); 
            cellFormula.add("MIN", 5); 
            cellFormula.add("MAX", 6);
            cellFormula.add("PROMEDIO", 7); 
            cellFormula.add("SUMA", 8);
            cellFormula.add("[*]", 9); 
            cellFormula.add("[/]", 10); 
            cellFormula.add("[0-9]+",11); // integer number
            cellFormula.add("[a-zA-Z]{1,}[0-9]{1,}", 12); // CellReference
            
    }

}
