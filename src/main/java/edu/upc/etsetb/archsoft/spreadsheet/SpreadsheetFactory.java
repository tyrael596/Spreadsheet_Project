
package edu.upc.etsetb.archsoft.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.CellReference;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.Numeric;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.Operador;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.Punctuation;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.function.Function;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.function.MAX;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.function.MIN;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.function.PROMEDIO;
import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.function.SUMA;
import static java.lang.Integer.parseInt;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Factory class of the project. It Creates all the different elements in this project
 * @author Alex Eslava and Amaya Balaguer
 */
public class SpreadsheetFactory {
/**
 * Returns a Function of the given type. The type is defined by the String entered by the user. If the type does not exist, it throws the unknown type exception
 * @param functionName the name of the function type
 * @return Function object of the specified type.
 * @throws UnknownFunctionException 
 */
    public Function createFunction(String functionName) throws UnknownFunctionException {

        switch (functionName.toLowerCase()) {

            case "min":
                return new MIN(functionName);
            case "max":
                return new MAX(functionName);

            case "promedio":
                return new PROMEDIO(functionName);

            case "suma":
                return new SUMA(functionName);
            default:

                throw new UnknownFunctionException("Function " + functionName + " is unknown");

        }

    }
/**
 * Function that creates an Operador type object.
 * @param op String that contains the type of operand. 
 * @return an Operador object containing the operand type. 
 */
    public Operador createOperador(String op) {

        return new Operador(op);
    }
/**
 * Function that returns a FormulaElement object containing the given information. 
 * @param token numeric identifier corresponding to each type of possible token.
 * @param tok sequence from original input 
 * @param tokens list containing all tokens. Used by CellRange to break down into small CellReferences
 * @return an object (or objects) of type corresponding to given "token" with "tok" sequence content
 * @throws UnknownTypeException 
 * @throws UnknownFunctionException 
 */
    public FormulaElement createFormulaElement(int token, String tok, LinkedList tokens) throws UnknownTypeException, UnknownFunctionException {
        switch (token) {
            case SpreadsheetToolkit.TOKENCELLREF:
                return new CellReference(token, tok);
            case SpreadsheetToolkit.TOKENNUM:
                return new Numeric(token, tok);
            case SpreadsheetToolkit.TOKENDIV:
            case SpreadsheetToolkit.TOKENMINUS:
            case SpreadsheetToolkit.TOKENMULT:
            case SpreadsheetToolkit.TOKENPLUS:
                return new Operador(token, tok);
            case SpreadsheetToolkit.TOKENPROMEDIO:
            case SpreadsheetToolkit.TOKENSUMA:
            case SpreadsheetToolkit.TOKENMAX:
            case SpreadsheetToolkit.TOKENMIN:
                return this.createFunction(tok);
            case SpreadsheetToolkit.TOKENPUNCT:
            case SpreadsheetToolkit.TOKENCLOSE:
            case SpreadsheetToolkit.TOKENOPEN:
                return this.createPunctuation(token, tok);
            case SpreadsheetToolkit.TOKENCELLRANGE:
                return addCellRange(tok, tokens);

            default:
                throw new UnknownTypeException();
        }
    }
/**
 * Function that creates a Punctuation type object.
 * @param token integer containing the assigned token. 
 * @param op String that contains the type of punctuation sign. 
 * @return Punctuation type object containing said arguments. 
 */
    private FormulaElement createPunctuation(int token, String op) {
        return new Punctuation(token, op);
    }
/**
 * Here we break down a CellRange sequence into it's top-left cell reference and 
 * it's bottom-right cell reference. All the cells inbetween are then added into the
 * "tokens" linked list in an iterating loop across the dimensions of the CellRange
 * lastly the final cell reference is returned and added to the list on the function
 * that called this method
 * @param sequence sequence containing top-left cell : bottom-right cell for a cell range
 * @param tokens linked list of tokens where we add all cells within cell range extracted
 * @return 
 */
    private FormulaElement addCellRange(String sequence, LinkedList tokens) {
        Pattern p = Pattern.compile("[a-zA-Z]{1,}[0-9]{1,}");
        Matcher m = p.matcher(sequence);    
        m.find();
        int start = m.start(0);
        int end = m.end(0);
        FormulaElement first = new CellReference(SpreadsheetToolkit.TOKENCELLREF, sequence.substring(start, end));    
        m.find();
        start = m.start(0);
        end = m.end(0);
        FormulaElement last = new CellReference(SpreadsheetToolkit.TOKENCELLREF, sequence.substring(start, end));     
        p = Pattern.compile("[a-zA-Z]{1,}");        
        m = p.matcher(first.getSequence());
        m.find();
        start = m.start(0);
        end = m.end(0);
        String iteratingCell=first.getSequence().substring(start, end);    
        m = p.matcher(last.getSequence());
        m.find();
        start = m.start(0);
        end = m.end(0);
        String endCell=last.getSequence().substring(start, end);
        p = Pattern.compile("[0-9]{1,}");
        m = p.matcher(sequence);
        m.find();
        start = m.start(0);
        end = m.end(0);
        int n1 = parseInt(sequence.substring(start, end)); // GGGGGGGGGG
        m.find();
        start = m.start(0);
        end = m.end(0);
        int n2 = parseInt(sequence.substring(start, end));  
        while (!iteratingCell.equals(endCell)) {
            for (int x = n1; x <= n2; x++) {
                FormulaElement current = new CellReference(SpreadsheetToolkit.TOKENCELLREF, iteratingCell);
                tokens.add(current);
                tokens.add(new Punctuation(SpreadsheetToolkit.TOKENPUNCT, ";"));
            }
            iteratingCell = incrementCell(iteratingCell);
        }//La ultima columna entera menos el ultimo que va al return. Este codigo queda un poco feo pero tokenizer queda mas streamlined
        for (int x = n1; x <= n2 - 1; x++) {
            FormulaElement current = new CellReference(SpreadsheetToolkit.TOKENCELLREF, iteratingCell+x);
            tokens.add(current);
            tokens.add(new Punctuation(SpreadsheetToolkit.TOKENPUNCT, ";"));
        }
        return last;
    }
/**
 * This method does the alphabetical iteration for addCellRange 
 * @param str current alphanetical column that needs to be increased
 * @return nextmost alphabetical counting value 
 */
    String incrementCell(String str) {
        char[] digits = str.toCharArray();
        for (int i = str.length() - 1; i >= 0; --i) {
            if (digits[i] == 'Z') {
                digits[i] = 'A';
            } else {
                digits[i] += 1;
                break;
            }
        }
        return new String(digits);
    }

}
