/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Postfixer;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Tokenizer;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Cell;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.ExpressionCleaner;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.PostfixEvaluator;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.VisualInterface;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alex
 */
public class Main {
 public static boolean exit = false;
   private static  VisualInterface vi = new VisualInterface();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        while(!exit){
            vi.printMenu(); 
        }


 

              
         


            
    }

}
