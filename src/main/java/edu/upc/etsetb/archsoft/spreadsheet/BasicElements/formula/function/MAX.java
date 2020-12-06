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


public class MAX extends Function{
    /**
     * MAX class method that calculates the final value
     * @params arguments: n is the number of parameters contained in the array
     * list: array of arguments
     */
    public static void MAX() {
         MAX myObj = new MAX(); 
   
    }
    
    
    @Override
    public float Calculate(LinkedList list) {
        float a,aux = 0;
        int last = list.size();
        a = (float) list.peekFirst();

       for (int i = 1; i <= last; i++) {
            aux = (float) list.pollFirst();
           if (aux > a){
               a = aux;
           }
       } 
       return a;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void getError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


   /* public void Calculate(){
>>>>>>> main:src/main/java/edu/upc/etsetb/archsoft/spreadsheet/BasicElements/formula/function/MAX.java
    }
    public void getError(){
    }    */

    
}
