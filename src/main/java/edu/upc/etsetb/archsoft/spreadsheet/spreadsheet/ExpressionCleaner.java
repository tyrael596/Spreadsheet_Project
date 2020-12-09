/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import static edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Tokenizer.tokenInfos;
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
    
    public void check(LinkedList<FormulaElement> Tokenized) throws SyntaxErrorException, Exception {
        int ttok;
        int i = 0; //Word count
        int j = 0; //Detect formula without parenthesis afterwards
        int parenthesis=0; //Number of open parenthesises
        int operators=0; // +-*/ preceeding token?
        int form=0; //Function? 
        int nou=1; //Start of line or first term after parenthesis
        while (!Tokenized.isEmpty()) {  
            ttok=Tokenized.getFirst().getToken();
            switch(ttok){
                case 1: // (
                    if (j>0){
                        j=0;
                    }
                    nou++;
                    parenthesis++;
                case 2: // )
                    if(parenthesis>0){
                        parenthesis--;
                    }
                    else{ throw SyntaxErrorException();}
                case 3: // -
                    if (operators==0 && nou==0){
                        operators++;
                    }
                    else{ throw SyntaxErrorException();}
                case 4: // +
                    if (operators==0 && nou==0){
                        operators++;
                    }
                    else{ throw SyntaxErrorException();}
                case 5: // MIN
                    j=1;
                case 6: // MAX
                    j=1;
                case 7: //PROMEDIO
                    j=1;
                case 8: //SUMA
                    j=1;
                case 9: // *
                    if (operators==0 && nou==0){
                        operators++;
                    }
                    else{ throw SyntaxErrorException();}
                case 10:// /
                    if (operators==0 && nou==0){
                        operators++;
                    }
                    else{ throw SyntaxErrorException();}
                case 11:// num
                    if(nou==1){nou--;}
                    else if(operators>0){
                        operators--;
                    }
                    else{
                        throw SyntaxErrorException();
                    }
                case 12:// CellRef
                    if(nou==1){nou--;}
                    else if(operators>0){
                        operators--;
                    }
                    else{
                        throw SyntaxErrorException();
                    }
                case 13:// CellRange
                    if(nou==1){nou--;}
                    else if(operators>0){
                        operators--;
                    }
                    else{
                        throw SyntaxErrorException();
                    }
            }
            if(j==1){j++;}
            i++;
            Tokenized.removeFirst();
            
        } 
        if (parenthesis > 0 || j==2 || operators>0) {
            throw SyntaxErrorException();
        }
        
        
    }

    private Exception SyntaxErrorException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    private static class SyntaxErrorException extends Exception {

        public SyntaxErrorException() {
        }
    }
}
