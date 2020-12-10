/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

/**
 *
 * @author Alex
 */
public class ExpressionCleaner {
    
    
    
    public List<FormulaElement> getTokens() {
       
        return null;
    }
    
    public void check(LinkedList<FormulaElement> tokenized) throws SyntaxErrorException {
        int ttok;
        int i = 0; //Word count
        int j = 0; //Detect formula without parenthesis afterwards
        int parenthesis=0; //Number of open parenthesises
        int operators=0; // +-*/ preceeding token?
        int form=0; //Function? 
        int nou=1; //Start of line or first term after parenthesis
        
        while (!tokenized.isEmpty()) {  
            
            ttok=tokenized.getFirst().getToken();
            String testering = tokenized.getFirst().getSequence();
            System.out.print("->Token:");
            System.out.print(testering);
            System.out.print("->Flags:");
            switch(ttok){
                case 1: // ( 
                    if (j>0){
                        j=0; 
                    }
                    nou=1;
                    parenthesis++;
                    break;
                case 2: // )
                    if(parenthesis>0){
                        parenthesis--;
                    }
                    else{ throw new SyntaxErrorException();}
                    break;
                case 3: // -
                    if (operators==0 && nou==0){
                        operators++;
                    }
                    else{ throw new SyntaxErrorException();}
                    break;
                case 4: // +
                    if (operators==0 && nou==0){
                        operators++;
                    }
                    else{ throw new SyntaxErrorException();}
                    break;
                case 5: // MIN
                    j++;
                    if (operators>0){
                        operators--;
                    }
                    break;       
                case 6: // MAX
                    j++;
                    if (operators>0){
                        operators--;
                    }
                    break;
                case 7: //PROMEDIO
                    j++;
                    if (operators>0){
                        operators--;
                    }
                    break;
                case 8: //SUMA
                    j++;
                    break;
                case 9: // *
                    if (operators==0 && nou==0){
                        operators++;
                    }
                    else{ throw new SyntaxErrorException();}
                    break;
                case 10:// /
                    if (operators==0 && nou==0){
                        operators++;
                    }
                    else{ throw new SyntaxErrorException();}
                    break;
                case 11:// num  
                    if(nou==1){nou--;}
                    else if(operators>0){
                        operators--;
                    }
                    else{
                        throw new SyntaxErrorException();
                    }
                    break;
                case 12:// CellRef
                    System.out.print("(Case 12)");
                    if(nou==1){nou--;}
                    else if(operators>0){
                        operators--;
                    }
                    else{
                        throw new SyntaxErrorException();
                    }
                    break;
                case 13:// CellRange
                    System.out.print("(Case 13)");
                    if(nou==1){nou--;}
                    else if(operators>0){
                        operators--;
                    }
                    else{
                        throw new SyntaxErrorException();
                    }
                    break;
                default: 
                    System.out.print("WEIRD Syntax Error");
                    break;
                }
            tokenized.removeFirst();
            System.out.print(nou);
            System.out.print(operators);
            System.out.print(parenthesis);
            System.out.print(j);
            
        }
        if (parenthesis > 0 || j==2 || operators>0) {
            throw new SyntaxErrorException();  
        } 
    }

    public  class SyntaxErrorException  extends RuntimeException {
           public SyntaxErrorException(String msg) {super(msg);
    }
        private SyntaxErrorException() {
            System.out.print("Syntax Error");
        }
        
   }

}

 


