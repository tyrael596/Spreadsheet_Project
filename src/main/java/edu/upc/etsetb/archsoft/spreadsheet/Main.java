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
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alex
 */
public class Main {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        SpreadsheetFactory factory = new SpreadsheetFactory();        
        Cell[][] spreadsheet = new Cell[43][43];
        for(int col = 0; col < 43; col ++){
            for (int row = 0;row < 43; row ++ ){
                spreadsheet[row][col] = new Cell();
                spreadsheet[row][col].content.setContent(String.valueOf(row + 3));
               
            }
        }
       // System.out.println("El spreadsheet es");
        
       /* for(int col = 0; col < 3; col ++){
            for (int row = 0;row < 3; row ++ ){
                
                System.out.println( row+col+" contengo" + spreadsheet[row][col].content.getContent());
            }
        }*/
        
        // TESTING TOKENIZER
        // MAX(2+B2:A1)*(5+-)
        // MAX ( 2 + B2:A1 ) * ( 5 + - )
       String jonasbrothers="1+A1*((SUMA(A2;H4;PROMEDIO(B6;B8);C1;27)/4)+(D6-D8))";
        //String jonasbrothers="1+A1*(4-3)";
        //String jonasbrothers="SUMA(A2;H4)";
        Tokenizer jonbonjovi=new Tokenizer();
        jonbonjovi.setFactory(factory);
        jonbonjovi.add("\\(", 1); // open bracket
        jonbonjovi.add("\\)", 2); // close bracket
        jonbonjovi.add("[-]", 3); 
        jonbonjovi.add("[+]", 4); 
        jonbonjovi.add("MIN", 5); 
        jonbonjovi.add("MAX", 6);
        jonbonjovi.add("PROMEDIO", 7); 
        jonbonjovi.add("SUMA", 8);
        jonbonjovi.add("[*]", 9); 
        jonbonjovi.add("[/]", 10); 
        jonbonjovi.add("[0-9]+",11); // integer number
        jonbonjovi.add("[a-zA-Z]{1,}[0-9]{1,}:[a-zA-Z]{1,}[0-9]{1,}", 12); // CellRange
        jonbonjovi.add("[a-zA-Z]{1,}[0-9]{1,}", 13); // CellReference       
        jonbonjovi.add("[;]", 14); // function delimiter
        LinkedList<FormulaElement> joeburgerchallenge = new LinkedList<>();
        jonbonjovi.getTokens(jonasbrothers);
        System.out.println(jonbonjovi.tokens.getFirst().getSequence());
        joeburgerchallenge=jonbonjovi.tokens;
        /*System.out.print("Get First:");
        System.out.print(joeburgerchallenge.getFirst().getSequence());
        System.out.print(joeburgerchallenge.getFirst().getToken());
        System.out.print("Get Last:");
        System.out.print(joeburgerchallenge.getLast().getSequence());
        System.out.print(joeburgerchallenge.getLast().getToken());*/
        //System.out.print("-->");
        //System.out.print(joeburgerchallenge.getLast().getToken());
        // System.out.print("<--");
       LinkedList<FormulaElement> auxiliar = new LinkedList<>(joeburgerchallenge);
       LinkedList<FormulaElement> auxiliar2 = new LinkedList<>(joeburgerchallenge);
 
        //TESTING ExpressionCleaner
        ExpressionCleaner sezarblue = new ExpressionCleaner();

        try {  
            sezarblue.check(joeburgerchallenge);
        } catch (ExpressionCleaner.SyntaxErrorException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*System.out.println("Te entra ");
          while(auxiliar2.isEmpty() == false){
            FormulaElement a = auxiliar2.pop();
            System.out.print(a.getSequence());
        } */
        LinkedList<FormulaElement> postfix = new LinkedList();
        postfix = Postfixer.shuntingYardAlgorithm(auxiliar,spreadsheet);
            LinkedList<FormulaElement> postfixaux = new LinkedList<>(postfix);
           // System.out.println("Postfix " + postfixaux.size());
            
       /* while(postfixaux.isEmpty() == false){
            FormulaElement a = postfixaux.pop();
                       // System.out.println("holi " + postfixaux.size());
            System.out.println(a.getSequence());
        } */   
        

              
       
        PostfixEvaluator evaluator = new PostfixEvaluator();
        evaluator.setFactory(factory);
        float output = evaluator.evaluate(postfix);
        System.out.println("output " + output);        


            
    }

}
