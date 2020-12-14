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
       //String jonasbrothers="1+A1*((SUMA(A2;H4;PROMEDIO(B6;B8);C1;27)/4)+(D6-D8))";
        String jonasbrothers="1+3*(4-2)";
        
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
        jonbonjovi.add("[a-zA-Z]{1,}[0-9]{1,}", 12); // CellReference
        jonbonjovi.add("[a-zA-Z]{1,}[0-9]{1,}:[a-zA-Z]{1,}[0-9]{1,}", 13); // CellRange
        jonbonjovi.add("[;]", 14); // function delimiter
        LinkedList<FormulaElement> joeburgerchallenge = new LinkedList<>();
        jonbonjovi.getTokens(jonasbrothers);
        joeburgerchallenge=jonbonjovi.tokens;
        /*System.out.print("Get First:");
        System.out.print(joeburgerchallenge.getFirst().getSequence());
        System.out.print(joeburgerchallenge.getFirst().getToken());
        System.out.print("Get Last:");
        System.out.print(joeburgerchallenge.getLast().getSequence());
        System.out.print(joeburgerchallenge.getLast().getToken());*/
        System.out.print("-->");
        System.out.print(joeburgerchallenge.getLast().getToken());
         System.out.print("<--");
       LinkedList<FormulaElement> auxiliar = new LinkedList<>(joeburgerchallenge);
       LinkedList<FormulaElement> auxiliar2 = new LinkedList<>(joeburgerchallenge);
 
        //TESTING ExpressionCleaner
        ExpressionCleaner sezarblue = new ExpressionCleaner();

        try {  
            sezarblue.check(joeburgerchallenge);
        } catch (ExpressionCleaner.SyntaxErrorException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("Te entra ");
          while(auxiliar2.isEmpty() == false){
            FormulaElement a = auxiliar2.pop();
            System.out.print(a.getSequence());
        } 
        LinkedList<FormulaElement> postfix = new LinkedList();
        postfix = Postfixer.shuntingYardAlgorithm(auxiliar,spreadsheet);
            LinkedList<FormulaElement> postfixaux = new LinkedList<>(postfix);
            System.out.println("Postfix ");
        while(postfixaux.isEmpty() == false){
            FormulaElement a = postfixaux.pop();
            System.out.print(a.getSequence());
        }    
        

              
       
        PostfixEvaluator evaluator = new PostfixEvaluator();
        evaluator.setFactory(factory);
        float output = evaluator.evaluate(postfix);
        System.out.println("output " + output);        
        /*
       //creamos lista
        FormulaElement a = new FormulaElement(1,"(");
        FormulaElement b = new FormulaElement(11,"5");
        FormulaElement c = new FormulaElement(10,"/");
        FormulaElement d = new FormulaElement(11,"2");
        FormulaElement e = new FormulaElement(2,")");
        FormulaElement f = new FormulaElement(4,"+");
        FormulaElement g = new FormulaElement(11,"3");
        FormulaElement h = new FormulaElement(9,"*");
        FormulaElement i = new FormulaElement(11,"6");
        FormulaElement j = new FormulaElement(3,"-");
        FormulaElement k = new FormulaElement(11,"5");
       
        FormulaElement a = new FormulaElement(11,"1");
        FormulaElement b = new FormulaElement(4,"+");
        FormulaElement c = new FormulaElement(7,"PROMEDIO");
        FormulaElement d = new FormulaElement(1,"(");
        FormulaElement e = new FormulaElement(11,"4");
        FormulaElement f = new FormulaElement(14,";");
        FormulaElement g = new FormulaElement(11,"3");
        FormulaElement h = new FormulaElement(14,";");
        FormulaElement i = new FormulaElement(6,"MAX");
        FormulaElement j = new FormulaElement(1,"(");
        FormulaElement k = new FormulaElement(11,"1");
        FormulaElement l = new FormulaElement(14,";");
        FormulaElement m = new FormulaElement(11,"2");
        FormulaElement n = new FormulaElement(2,")");
        FormulaElement o = new FormulaElement(2,")");
        LinkedList<FormulaElement> input = new LinkedList();
        input.add(a);        
        input.add(b);
        input.add(c);
        input.add(d);       
        input.add(e);
        input.add(f);
        input.add(g); 
        input.add(h);
        input.add(i);
        input.add(j);
        input.add(k);
       input.add(l);
        input.add(m);
        input.add(n);
        input.add(o);
         //input.add("1");
       //  input.add("MAX");
         //input.add("(");
         //input.add("3");
         //input.add(",");
         //input.add("6");
         //input.add(")");
              
         
      
         LinkedList<FormulaElement> postfix = new LinkedList();
          
        postfix = Postfixer.shuntingYardAlgorithm(input);

         while(postfix.isEmpty() == false){
            a = postfix.pop();
            System.out.println("postfix " + a.getSequence());
        }    
        float output = evaluator(postfix);
        System.out.println("output " + output);
        

        
         
       // System.out.println(postfix);
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
            cellFormula.add("[a-zA-Z]{1,}[0-9]{1,}:[a-zA-Z]{1,}[0-9]{1,}", 13); // CellRange
        Aqui falta RANGE OF cells A4:B5
        //faltan los : y ; les pongo 14 y 15
        
        */
            
    }

}
