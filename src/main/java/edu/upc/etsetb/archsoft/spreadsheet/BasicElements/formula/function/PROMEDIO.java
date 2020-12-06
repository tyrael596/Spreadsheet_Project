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

public class PROMEDIO extends Function{
    public static void PROMEDIO() {
         PROMEDIO myObj = new PROMEDIO(); 
   
    }
    
    @Override
    public float Calculate(LinkedList list) {         
        float a = 0,aux = 0;
        int last = list.size();
        aux = (float) list.peekFirst();

       for (int i = 1; i <= last; i++) {
            aux = (float) list.pollFirst()+ a;
       } 
        a = aux/last;
       return a;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void getError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
