/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.CellReference;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.Numeric;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.Operador;
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.function.Function;
import edu.upc.etsetb.archsoft.spreadsheet.UnknownFunctionException;
import java.util.LinkedList;

/**
 *
 * @author amayabalaguer
 */
public class Visitador {

    LinkedList<Float> stack = new LinkedList();
    private FormulaElement aux;
    public float visitaCellReference(CellReference aThis, LinkedList<FormulaElement> input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public float visitaNumeric(Numeric aThis,LinkedList<FormulaElement> input) {
        aux = input.pop();
        float number = Float.parseFloat(aux.getSequence());
        stack.push(number);
        return number;
    }

    public float visitaOperador(Operador aThis,LinkedList<FormulaElement> input) {
        aux = input.pop();        
        float output = 0;
        Operador operador = this.factory.createOperador(aux.getSequence());
        float operand2 = stack.pop();
        float operand1 = stack.pop();

        if (input.isEmpty() == false) {
            float number = operador.calculate(operand1, operand2);
            stack.push(number);
        } else {

             output = operador.calculate(operand1, operand2);
        }
        return output;
    }

    public float visitaFunction(Function aThis,LinkedList<FormulaElement> input) throws UnknownFunctionException {
        aux = input.pop();
        float number = calculateFunction(input, aux.getSequence());
        stack.push(number);
        return number;
    }
    
    private float calculateFunction(LinkedList<FormulaElement> input, String type) throws UnknownFunctionException{
       
       
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
            num = evaluate(input);
            calculate.addLast(num);
            aux = input.pop();                       
         } else if(aux.getToken() == 14| aux.getToken() == 15){
             
             aux = input.pop(); 
         }//falta si son referencias pero a tanto ya no llego
         else if(aux.getToken() == 5| aux.getToken() == 6|aux.getToken() == 7| aux.getToken() == 8){
            num = calculateFunction(input, aux.getSequence());
            calculate.addLast(num);
            
            aux = input.pop(); 
            
        }
            
        } 
        
     if(aux.getToken()== 2){       
         Function function = this.factory.createFunction(type);
         output = function.Calculate(calculate);
     
        }
   return output;
}

}
