/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.MAX;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.MIN;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.PROMEDIO;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.SUMA;
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
        LinkedList<FormulaElement> calculate = new LinkedList(); 
        FormulaElement aux, aux2;
        boolean end = false;
        float number,operand1, operand2;
        //aux = input.pop(); 
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
                    default:  
                        
                        number = calculateFunction(input,aux.getToken());
                        stack.push(number);
                               
                        break;
                }
        }
        
        
        return output;
    }
    
   public static float calculateFunction(LinkedList<FormulaElement> input, int type){
       
       
       float output= 0;
            float   num = 0;

       FormulaElement aux;
       input.pop();//fuera el primer parentesis
       aux = input.pop();
       LinkedList<Float> calculate = new LinkedList();
                        
       while ( aux.getToken() != 2){
                     
        if(aux.getToken() == 11){
           calculate.addLast(Float.parseFloat(aux.getSequence()));
           aux = input.pop();
        } else  if(aux.getToken()== 1){//me encuentro un (                              
            num = evaluator(input);
            calculate.addLast(num);
            aux = input.pop();                       
         } else if(aux.getToken() == 14| aux.getToken() == 15){
             
             aux = input.pop(); 
         }//falta si son referencias pero a tanto ya no llego
         else if(aux.getToken() == 5| aux.getToken() == 6|aux.getToken() == 7| aux.getToken() == 8){
            num = calculateFunction(input, aux.getToken());
            calculate.addLast(num);
            
            aux = input.pop(); 
            
        }
            
        } 
        
     if(aux.getToken()== 2){
         
        
        switch(type){

            case 5: 
                MIN min = new MIN();
                output = min.Calculate(calculate);
                

            break;

            case 6:
                MAX max = new MAX();
                output = max.Calculate(calculate);

            break;

            case 7:
                PROMEDIO promedio = new PROMEDIO();
                output = promedio.Calculate(calculate);

            break;

            case 8: 
                SUMA suma = new SUMA();
                output = suma.Calculate(calculate);
                
                

            break;
      }                   
       
   return output;
   }
   return output;
}
}
