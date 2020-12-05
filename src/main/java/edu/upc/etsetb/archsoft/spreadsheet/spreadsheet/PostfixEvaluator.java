/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import java.util.LinkedList;

/**
 *
 * @author Amaya
 */
public class PostfixEvaluator {
    // Acceso a lista de cells afectadas en spreadsheet
    
    public static float evaluator(LinkedList<FormulaElement> input){
        float output = 0;
        LinkedList<Float> stack = new LinkedList(); 
        FormulaElement aux, aux2;
        float number,operand1, operand2;
        
        while (input.isEmpty() == false) { 
                aux = input.pop(); 
                switch (aux.getToken()){
                    case 11: 
                        number = Float.parseFloat(aux.getSequence());
                        stack.push(number);
                        break;
                    case 9:
                        if(input.isEmpty() == false){

                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            number = operand1*operand2;
                            stack.push(number);                            
                        }else{
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            output = operand1*operand2;
                        }
                        break;
                    case 3:
                        if(input.isEmpty() == false){
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            number = operand1-operand2;
                            stack.push(number);                            
                        }else{
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            output = operand1-operand2;
                        }
                        break;
                    case 4:
                        if(input.isEmpty() == false){
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            number = operand1+operand2;
                            stack.push(number);                            
                        }else{
                            operand2 = stack.pop();
                            operand1 = stack.pop();
                            output = operand1+operand2;
                        }
                        break;
                    case 10:
                        if(input.isEmpty() == false){
                            operand2 = stack.pop();
                            operand1 = stack.pop();                           
                            number = operand1/operand2;
                            
                            stack.push(number);                            
                        }else{
                            operand2 = stack.pop();
                            operand1 = stack.pop();                        
                            output = operand1/operand2;
                        }
                        break;                       
                }
        }
        
        
        return output;
    }
}
