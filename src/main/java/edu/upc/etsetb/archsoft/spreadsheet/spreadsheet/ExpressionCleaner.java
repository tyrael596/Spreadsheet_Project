/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import edu.upc.etsetb.archsoft.spreadsheet.SyntaxErrorException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class ExpressionCleaner {

    /**
     *
     * @return
     */
    public List<FormulaElement> getTokens() {
        return null;
    }

    /**
     * Check if the input string is syntactically correct all params are
     * commented across the function
     */
    public void check(LinkedList<FormulaElement> tokenized) throws SyntaxErrorException {
        int ttok;
        int j = 0; //Detect formula without parenthesis afterwards
        int parenthesis = 0; //Number of open parenthesises in total
        int operators = 0; // +-*/ preceeding token?
        int nou = 1; //Start of line or first term after parenthesis
        int aircoma = 0; //Coma left hanging?
        int fpar = 0; //Check function parenthesis are correctly paired 
        while (!tokenized.isEmpty()) {

            ttok = tokenized.getFirst().getToken();
            if (fpar == 1 && ttok != 1) {
                throw new SyntaxErrorException();
            }
            fpar = 0;
            switch (ttok) {
                case SpreadsheetToolkit.TOKENOPEN: //.............................. ( 
                    nou = 1;
                    parenthesis++;
                    break;
                case SpreadsheetToolkit.TOKENCLOSE: //.............................. )
                    if (j > 0) {
                        j--;
                    }
                    if (parenthesis > 0) {
                        parenthesis--;
                    } else {
                        throw new SyntaxErrorException();
                    }
                    break;
                case SpreadsheetToolkit.TOKENMINUS: //............................... -
                    if (aircoma == 1) {
                        throw new SyntaxErrorException();
                    }
                    if (operators == 0 && nou == 0) {
                        operators++;
                    } else {
                        throw new SyntaxErrorException();
                    }
                    break;
                case SpreadsheetToolkit.TOKENPLUS: //................................ +
                    if (aircoma == 1) {
                        throw new SyntaxErrorException();
                    }
                    if (operators == 0 && nou == 0) {
                        operators++;
                    } else {
                        throw new SyntaxErrorException();
                    }
                    break;
                case SpreadsheetToolkit.TOKENMIN: //............................... MIN
                    if (aircoma == 1) {
                        aircoma = 0;
                    }
                    j++;
                    fpar = 1;
                    if (operators > 0) {
                        operators--;
                    }
                    break;
                case SpreadsheetToolkit.TOKENMAX: //............................... MAX
                    if (aircoma == 1) {
                        aircoma = 0;
                    }
                    j++;
                    fpar = 1;
                    if (operators > 0) {
                        operators--;
                    }
                    break;
                case SpreadsheetToolkit.TOKENPROMEDIO: //................................PROMEDIO
                    if (aircoma == 1) {
                        aircoma = 0;
                    }
                    j++;
                    fpar = 1;
                    if (operators > 0) {
                        operators--;
                    }
                    break;
                case SpreadsheetToolkit.TOKENSUMA: //.................................SUMA
                    if (aircoma == 1) {
                        aircoma = 0;
                    }
                    j++;
                    fpar = 1;
                    if (operators > 0) {
                        operators--;
                    }
                    break;
                case SpreadsheetToolkit.TOKENMULT: //................................. *
                    if (aircoma == 1) {
                        throw new SyntaxErrorException();
                    }
                    if (operators == 0 && nou == 0) {
                        operators++;
                    } else {
                        throw new SyntaxErrorException();
                    }
                    break;
                case SpreadsheetToolkit.TOKENDIV://.................................. /
                    if (aircoma == 1) {
                        throw new SyntaxErrorException();
                    }
                    if (operators == 0 && nou == 0) {
                        operators++;
                    } else {
                        throw new SyntaxErrorException();
                    }
                    break;
                case SpreadsheetToolkit.TOKENNUM://................................. num
                    if (aircoma == 1) {
                        aircoma = 0;
                    }
                    if (nou == 1) {
                        nou--;
                        if (operators > 0) {
                            operators--;
                        }
                    } else if (operators > 0) {
                        operators--;
                    } else if (j > 0) {

                    } else {
                        throw new SyntaxErrorException();
                    }
                    break;
                case SpreadsheetToolkit.TOKENCELLREF://.................................. CellRef
                    if (aircoma == 1) {
                        aircoma = 0;
                    }
                    if (nou == 1) {
                        nou--;
                        if (operators > 0) {
                            operators--;
                        }
                    } else if (operators > 0) {
                        operators--;
                    } else if (j > 0) {

                    } else {
                        throw new SyntaxErrorException();
                    }
                    break;
                case SpreadsheetToolkit.TOKENCELLRANGE://.................................. CellRange
                    if (aircoma == 1) {
                        aircoma = 0;
                    }

                    if (nou == 1) {
                        if (operators > 0) {
                            operators--;
                        }
                        nou--;
                    } else if (operators > 0 || j > 0) {
                        operators--;
                    } else if (j > 0) {

                    } else {
                        throw new SyntaxErrorException();
                    }
                    break;
                case SpreadsheetToolkit.TOKENPUNCT: //................................... ; 
                    if (operators > 0) {
                        throw new SyntaxErrorException();
                    }
                    if (j > 0 && nou == 0) {
                        if (aircoma == 0) {
                            aircoma = 1;
                        } else {
                            throw new SyntaxErrorException();
                        }
                    } else {
                        throw new SyntaxErrorException();
                    }
                    break;
                default:
                    System.out.print("WEIRD Syntax Error");
                    break;
            }

            System.out.print(tokenized.getFirst().getSequence());
            tokenized.removeFirst();

        }
        if (parenthesis > 0 || j > 0 || operators > 0 || aircoma > 0) {
            throw new SyntaxErrorException("Unexpected character in input: " + tokenized.getFirst().getSequence());
        }
    }

}
