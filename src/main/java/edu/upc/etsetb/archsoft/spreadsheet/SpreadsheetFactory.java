/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.CellReference;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.Numeric;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.Operador;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.Punctuation;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.Function;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.MAX;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.MIN;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.PROMEDIO;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.SUMA;
import static java.lang.Integer.parseInt;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author amayabalaguer
 */
public class SpreadsheetFactory {

    public Function createFunction(String functionName) throws UnknownFunctionException {
        //Switch que discrimina lo que viene
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

    public Operador createOperador(String op) {

        return new Operador(op);
    }

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

    private FormulaElement createPunctuation(int token, String op) {
        return new Punctuation(token, op);
    }

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
