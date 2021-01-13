/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.function;
import edu.upc.etsetb.archsoft.spreadsheet.SpreadsheetToolkit;
import java.util.LinkedList;

/**
 * Class containing all the SUMA Function's methods 
 * @author Alex Eslava and Amaya Balaguer
 */
public class SUMA extends Function{

/**
 * Function that returns a SUMA object. It assigns the correct token and the function sequence
 * @param sequence String that contains the parameters of that function
 */
    public SUMA(String sequence) {
        super(SpreadsheetToolkit.TOKENSUMA, sequence);
    }
  /**
 * Function that calculates the final value of the SUMA function introduced by the user from a list of floats.
 * @param list List of floats containing all the values to consider when computing a SUMA function
 * @return float with the sum of all the floats contained in the introduced list. 
 */  
    @Override
    public float Calculate(LinkedList list) {         
        float a = 0;
        int last = list.size();
        a = (float) list.pop();
       
       while(!list.isEmpty()) {
            a = (float) list.pop()+ a;
            
       } 
        
       return a;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/**
 * Function that returns an error when a SUMA function has been introduced incorrectly. 
 */
    @Override
    void getError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
