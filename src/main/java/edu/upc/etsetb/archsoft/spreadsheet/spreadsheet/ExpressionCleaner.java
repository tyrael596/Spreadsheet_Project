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
        int aircoma=0;
        int fpar=0;
        while (!tokenized.isEmpty()) {  
            
            ttok=tokenized.getFirst().getToken();
            if(fpar==1 && ttok!=1){
                throw new SyntaxErrorException();
            }
            fpar=0;
            switch(ttok){
                case 1: //.............................. ( 
                    nou=1;
                    parenthesis++;
                    break;
                case 2: //.............................. )
                    if (j>0){
                        j--; 
                    }
                    if(parenthesis>0){
                        parenthesis--;
                    }
                    else{ throw new SyntaxErrorException();}
                    break;
                case 3: //............................... -
                    if(aircoma==1){
                        throw new SyntaxErrorException();
                    }
                    if (operators==0 && nou==0){
                        operators++;
                    }
                    else{ throw new SyntaxErrorException();}
                    break;
                case 4: //................................ +
                    if(aircoma==1){
                        throw new SyntaxErrorException();
                    }
                    if (operators==0 && nou==0){
                        operators++;
                    }
                    else{ throw new SyntaxErrorException();}
                    break;
                case 5: //............................... MIN
                    if(aircoma==1){aircoma=0;}
                    j++;
                    fpar=1;
                    if (operators>0){
                        operators--;
                    }
                    break;       
                case 6: //............................... MAX
                    if(aircoma==1){aircoma=0;}
                    j++;
                    fpar=1;
                    if (operators>0){
                        operators--;
                    }
                    break;
                case 7: //................................PROMEDIO
                    if(aircoma==1){aircoma=0;}
                    j++;
                    fpar=1;
                    if (operators>0){
                        operators--;
                    }
                    break;
                case 8: //.................................SUMA
                    if(aircoma==1){aircoma=0;}
                    j++;
                    fpar=1;
                    if (operators>0){
                        operators--;
                    }
                    break;
                case 9: //................................. *
                    if(aircoma==1){
                        throw new SyntaxErrorException();
                    }
                    if (operators==0 && nou==0){
                        operators++;
                    }
                    else{ throw new SyntaxErrorException();}
                    break;
                case 10://.................................. /
                    if(aircoma==1){
                        throw new SyntaxErrorException();
                    }
                    if (operators==0 && nou==0){
                        operators++;
                    }
                    else{ throw new SyntaxErrorException();}
                    break;
                case 11://................................. num
                    if(aircoma==1){aircoma=0;}
                    if(nou==1){
                        nou--;
                        if(operators>0){
                            operators--;
                        }
                    }
                    else if(operators>0){
                        operators--;
                    }
                    else if(j>0){
                        
                    }
                    else{
                        throw new SyntaxErrorException();
                    }
                    break;
                case 12://.................................. CellRef
                    if(aircoma==1){aircoma=0;}
                    if(nou==1){
                        nou--;
                        if(operators>0){
                            operators--;
                        }
                    }
                    else if(operators>0){
                        operators--;
                    }
                    else if(j>0){
                        
                    }
                    else{
                        throw new SyntaxErrorException();
                    }
                    break;
                case 13://.................................. CellRange
                    if(aircoma==1){aircoma=0;}
                    
                    if(nou==1){
                        if(operators>0){
                            operators--;
                        }
                        nou--;
                    }
                    else if(operators>0 || j>0){
                        operators--;
                    }
                    else if(j>0){
                        
                    }
                    else{
                        throw new SyntaxErrorException();
                    }
                    break;
                case 14: //................................... ; 
                    if(operators>0){
                        throw new SyntaxErrorException();
                    }
                    if(j>0 && nou==0){
                        if(aircoma==0){
                            aircoma=1; }
                        else{throw new SyntaxErrorException();}
                    }
                    
                    else{throw new SyntaxErrorException();}
                    break;
                default: 
                    System.out.print("WEIRD Syntax Error");
                    break;
                }

            System.out.print(tokenized.getFirst().getSequence());
            tokenized.removeFirst();
            
            
        }
        if (parenthesis > 0 || j>0 || operators>0 || aircoma>0) {
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

 


