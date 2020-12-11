/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.CellFormula;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import static edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.PostfixEvaluator.evaluator;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Postfixer;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Tokenizer;
import edu.upc.*;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.ExpressionCleaner;
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
    public static void main(String[] args) {
        
        
        // TESTING TOKENIZER
        // MAX(2+B2:A1)*(5+-)
        // MAX ( 2 + B2:A1 ) * ( 5 + - )
        String jonasbrothers="1+A1*((SUMA(A2:H4;PROMEDIO(B6);C1;27)/4)+(D6-D8))";
        Tokenizer jonbonjovi=new Tokenizer();
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
        System.out.print("Get First:");
        System.out.print(joeburgerchallenge.getFirst().getSequence());
        System.out.print(joeburgerchallenge.getFirst().getToken());
        System.out.print("Get Last:");
        System.out.print(joeburgerchallenge.getLast().getSequence());
        System.out.print(joeburgerchallenge.getLast().getToken());
        
       
        //TESTING ExpressionCleaner
        ExpressionCleaner sezarblue = new ExpressionCleaner();

        try {  
            sezarblue.check(joeburgerchallenge);
        } catch (ExpressionCleaner.SyntaxErrorException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        System.out.print("hola");
        
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
