/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.CellFormula;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Postfixer;
import java.util.LinkedList;
/**
 *
 * @author Alex
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //creamos lista
       
        LinkedList<String> input = new LinkedList();
         input.add("(");
         input.add("5");
         input.add("*");
         input.add("4");
         input.add("+");
         input.add("3");
         input.add("*");
         input.add(")");
         input.add("-");
         input.add("1");
       //  input.add("MAX");
         //input.add("(");
         //input.add("3");
         //input.add(",");
         //input.add("6");
         //input.add(")");
         System.out.println(input);
         
         System.out.println(input.size());
         LinkedList<String> postfix = new LinkedList();
         
        postfix = Postfixer.shuntingYardAlgorithm(input);
         
        System.out.println(postfix);
        // TODO code application logic here
        
    /*        CellFormula cellFormula = new CellFormula();
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
            cellFormula.add("[a-zA-Z]{1,}[0-9]{1,}", 13); // CellReference
        Aqui falta RANGE OF cells A4:B5
        
        */
            
    }

}
