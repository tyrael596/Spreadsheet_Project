/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function;
import java.util.LinkedList;
/**
 *
 * @author Amaya
 */

public class SUMA extends Function{
    public static void SUMA() {
         SUMA myObj = new SUMA(); 
   
    }
    
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

    @Override
    void getError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
