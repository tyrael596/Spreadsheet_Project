/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownFunctionException;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownTypeException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alex
 */
public class Tokenizer {
    private LinkedList<TokenInfo> tokenInfos;
    public LinkedList<FormulaElement> tokens = new LinkedList<>();
    private SpreadsheetFactory factory;
 
    
    public void setFactory(SpreadsheetFactory factory){
        this.factory = factory;
    }    
    

 public Tokenizer() {
  tokenInfos = new LinkedList<TokenInfo>();
}    
    private class TokenInfo{
        public final Pattern regex;
        public final int token; //Token Variable int,string o otro?

        public TokenInfo(Pattern regex, int token){ //Token Variable int,string o otro?
            super();
            this.regex=regex;
            this.token=token;
        }
}
    
    public void add(String regex, int token) { //Token Variable int,string o otro?
            tokenInfos.add(new TokenInfo(Pattern.compile("^("+regex+")"), token));
    }
    
    public void tokenize(String str) {
    String s = new String(str);
    tokens.clear(); 
    }
    
    
    public void getTokens(String dataInput) throws UnknownTypeException, UnknownFunctionException{
        while (!dataInput.equals("")) {
            boolean match = false;  
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(dataInput);
                if (m.find()) {
                    match = true;

                    String tok = m.group().trim();
                    tokens.add(factory.createFormulaElement(info.token, tok));

                    dataInput = m.replaceFirst("");
                    break;
                }
            }
            if (!match) throw new RuntimeException("Unexpected character in input: "+dataInput); // There were no matches inside the given string
        } 
    }
     
   
}
