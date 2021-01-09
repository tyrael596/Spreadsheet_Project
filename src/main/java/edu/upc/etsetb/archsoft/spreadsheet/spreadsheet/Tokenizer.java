/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetFactory;
import edu.upc.etsetb.archsoft.spreadsheet.SyntaxErrorException;
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

    /**
     * We will store inside the LinkedList of formula elements the tokens that 
     * we find within the user input
     */
    public LinkedList<FormulaElement> tokens = new LinkedList<>();
    private SpreadsheetFactory factory;

    /**
     *Set factory for current tokenizer object
     * @param factory
     */
    public void setFactory(SpreadsheetFactory factory) {
        this.factory = factory;
    }

    /**
     *Constructor for Tokenizer. Initiates a tokenInfos
     */
    public Tokenizer() {
        tokenInfos = new LinkedList<TokenInfo>();
    }
/**
 * List of tokens with regex for each of the possible tokens to be found in input
 */
    private class TokenInfo {

        public final Pattern regex;
        public final int token; //Token Variable int,string o otro?

        public TokenInfo(Pattern regex, int token) { //Token Variable int,string o otro?
            super();
            this.regex = regex;
            this.token = token;
        }
    }
/**
 * Generic method for adding TokenInfos to list of possible tokens to be found in input
 * @param regex regular expression corresponding to each possible token
 * @param token numerical indicator for each token
 */
    public void add(String regex, int token) { //Token Variable int,string o otro?
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token));
    }

/**
 * Transform user input string into a LinkedList of Tokens stored into Tokenizer's FormulaElements "tokens" List
 * @param dataInput user input string that needs to be broken down into strings
 * @throws UnknownTypeException
 * @throws UnknownFunctionException
 * @throws SyntaxErrorException 
 */
    public void getTokens(String dataInput) throws UnknownTypeException, UnknownFunctionException, SyntaxErrorException {
        while (!dataInput.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
                Matcher m = info.regex.matcher(dataInput);
                if (m.find()) {
                    match = true;

                    String tok = m.group().trim();
                    tokens.add(factory.createFormulaElement(info.token, tok, tokens));

                    dataInput = m.replaceFirst("");
                    break;
                }
            }
            if (!match) {
                throw new SyntaxErrorException("Unexpected character in input: " + dataInput); // There were no matches inside the given string
            }
        }
    }
/**
 * Add all tokeninfo possible tokens to be found to TokenInfos list 
 * @param tokenizer the object itself 
 */
    public void addTokens(Tokenizer tokenizer) {
        tokenizer.add("\\(", 1); // open bracket
        tokenizer.add("\\)", 2); // close bracket
        tokenizer.add("[-]", 3);
        tokenizer.add("[+]", 4);
        tokenizer.add("MIN", 5);
        tokenizer.add("MAX", 6);
        tokenizer.add("PROMEDIO", 7);
        tokenizer.add("SUMA", 8);
        tokenizer.add("[*]", 9);
        tokenizer.add("[/]", 10);
        tokenizer.add("[0-9]\\d{0,9}(\\.\\d{1,})?%?", 11);
        tokenizer.add("[a-zA-Z]{1,}[0-9]{1,}:[a-zA-Z]{1,}[0-9]{1,}", 12); // CellRange
        tokenizer.add("[a-zA-Z]{1,}[0-9]{1,}", 13); // CellReference       
        tokenizer.add("[;]", 14); // function delimiter
    }

}
