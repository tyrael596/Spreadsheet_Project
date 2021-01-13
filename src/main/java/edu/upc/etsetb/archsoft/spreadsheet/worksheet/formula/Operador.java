/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula;

import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Visitador;
import java.util.LinkedList;

/**
 *
 * @author Alex
 */
public class Operador extends FormulaElement{


    public Operador(int token, String sequence) {
        super(token, sequence);
    }

    public Operador() {
    }
    
    public Operador(String op) {
        
        this.sequence = op;
    }
    
    public float calculate(float operand1, float operand2){
        
       switch(sequence){
           case "+":
                return operand1 + operand2;
           case "-":
               
                return operand1 - operand2; 
            case "*":
                return operand1 * operand2;  
            default: 
                return operand1 / operand2;  
       }
           
    }
    
        @Override
    public float acceptVisitor(Visitador visitador,LinkedList input) {
        return visitador.visitaOperador(this,input);
    }

}
