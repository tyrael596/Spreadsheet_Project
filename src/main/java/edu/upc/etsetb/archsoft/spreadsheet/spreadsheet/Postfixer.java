/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Amaya
 */
public class Postfixer {
      List formulaList; 
    
//Habra que hacerlo static seguramente
    public static LinkedList shuntingYardAlgorithm(LinkedList<FormulaElement> input){ // Consideramos que la función es válida 
            LinkedList<FormulaElement> operatorStack = new LinkedList(); 
            LinkedList<FormulaElement> numbersQueue = new LinkedList(); 
            LinkedList<FormulaElement> auxiliarList = new LinkedList();
            FormulaElement aux,aux2; 
            int lastPrecedence = 0;
            float number; 
            // si es un simbolo y tiene menos preferencia que el ultimo del stack entonces saco el del stack y lo meto con los numeros y pongo el de menor preferencia en el stack 

            while (input.isEmpty() == false) { 
                aux = input.poll(); 
                // intentamos convertirlo a un float 
                try{ 
                    number = Float.parseFloat(aux.getSequence()); 
                    numbersQueue.addLast(aux);//si es un numero lo pongo en la cola de numeros en la última posición 
       
                } catch( NumberFormatException e ){ 
                 //significa que no es un numero por lo que procedemos a identificar qué es 
                 //llamamos a la función que nos dic"e lo que es 
                    int precedence = checkPrecedence(aux.getSequence()); 
                    
                    aux2 = operatorStack.peekFirst(); 
                    if(aux2 != null){//comprueba si la cola está vacia 
                        lastPrecedence = checkPrecedence(aux2.getSequence());  
                        if(precedence != 4){
                            if(precedence == 3){
                               //se trata de un parentesis de cierre
                                aux2 = operatorStack.pollFirst(); 
                                if(aux2 != null){
                                    lastPrecedence = checkPrecedence(aux2.getSequence()); 
                                while( lastPrecedence != 0){ // Hasta que llega al de apertura 
                                   numbersQueue.addLast(aux2); 
                                   aux2 = operatorStack.pollFirst(); 
                                   if(aux2 != null){
                                       lastPrecedence = checkPrecedence(aux2.getSequence()); 
                                   }
                               } 
                                             
                                aux2 = operatorStack.pollFirst(); 
                                }
                                
                            } 
                            else{if (precedence >= lastPrecedence){ 
                                    operatorStack.addFirst(aux);
                                    
                                    
                                }else if(lastPrecedence > precedence){
                                          
                                    aux2 = operatorStack.pollFirst(); 
                                    numbersQueue.addLast(aux2); 
                                    operatorStack.addFirst(aux);
              
                                   
                                    }              
                                }
                        }else{ //para las funciones
                            numbersQueue.addLast(aux);
                            auxiliarList = getFunctionArguments(input);   
                            while(!auxiliarList.isEmpty() && aux != null){
                                numbersQueue.addLast(auxiliarList.pop());
                               // aux = input.pop();
                             }

                        }
                    }else {
                        
                        if(checkPrecedence(aux.getSequence()) == 3){
                      
                            numbersQueue.addLast(aux);
                        }else{
                            operatorStack.addFirst(aux);
                        }
                        
                    } 
                } 
            }
            //llegados al final toca pasarlos todos 
            while(!operatorStack.isEmpty()){ 
                
                numbersQueue.addLast(operatorStack.pollFirst()); 
            } 



            return numbersQueue; 
        } 


        static int checkPrecedence(String symbol){ 
            int precedence = 0; 
            switch(symbol){ 
                case "(": precedence = 0; 
                break; 
                case ")": precedence = 3;//especial 
                break; 
                case "+": precedence = 1; 
                break; 
                case "-": precedence = 1; 
                break; 
                case "*": precedence = 2; 
                break; 
                case "/": precedence = 2; 
                break; 
                case ":": precedence = 5; 
                break;
                case ";": precedence = 5; 
                break;
                default:  precedence = 4;  
                break; 
            } 
        return precedence; 
    } 
    static float evaluator(LinkedList<String> input){   
        // Codigo para hacer
        float result=0;
        return result;
    }
    
    //falta añadir el caso en el que tengo una función con paréntesis ahí en medio
    static LinkedList getFunctionArguments(LinkedList<FormulaElement> input){
        LinkedList<FormulaElement> arguments = new LinkedList();
        LinkedList<FormulaElement> auxiliarList = new LinkedList();
        int precedence = 0;
        FormulaElement aux;
        aux = input.poll();
        precedence = checkPrecedence(aux.getSequence());
        
        while(precedence != 3){
            //System.out.println(" printo cosas ");
            if(precedence == 4){ //tengo una función
               
               //auxiliarList = getFunctionArguments(input);
               
               while(!auxiliarList.isEmpty()){
                    arguments.addLast(auxiliarList.pop());
                    input.pop();
                }
                //System.out.println("es una funciooon" + aux.getSequence());
                
                arguments.addLast(aux);//si es un numero lo pongo en la cola de numeros en la última posición 
                
                aux = input.poll();
                
                if(aux != null){
                    precedence = checkPrecedence(aux.getSequence());
                }
            
            }
            
            arguments.addLast(aux);//si es un numero lo pongo en la cola de numeros en la última posición 
            
            aux = input.poll();
            if(aux != null){
                    precedence = checkPrecedence(aux.getSequence());
             }
            
         }
            arguments.addLast(aux); 
            
       return arguments;     
    }
  
}
