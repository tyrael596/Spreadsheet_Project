/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownFunctionException;
import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Visitador;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alex
 */
public abstract class Function extends FormulaElement {
    
    static String inside ="";
    static String range = "";
    public abstract float Calculate(LinkedList list);
    abstract void getError();

    @Override
   public float acceptVisitor (Visitador visitador,LinkedList input) {
        try {
            return visitador.visitaFunction(this,input);
        } catch (UnknownFunctionException ex) {
            Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
   }
}
