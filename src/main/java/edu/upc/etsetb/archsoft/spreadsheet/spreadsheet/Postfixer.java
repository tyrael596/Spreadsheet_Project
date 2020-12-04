/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class Postfixer {
      List formulaList; 
    

    static LinkedList shuntingYardAlgorithm(LinkedList<String> input){ // Consideramos que la función es válida 
            LinkedList<String> operatorStack = new LinkedList(); 
            LinkedList<String> numbersQueue = new LinkedList(); 
            String aux,aux2; 
            float number; 
            // si es un simbolo y tiene menos preferencia que el ultimo del stack entonces saco el del stack y lo meto con los numeros y pongo el de menor preferencia en el stack 
            int last = input.size(); 

            for (int i = 1; i <= last; i++) { 
                aux = input.poll(); 
                // intentamos convertirlo a un float 
                try{ 
                number = Float.parseFloat(aux); 
                numbersQueue.addLast(aux);//si es un numero lo pongo en la cola de numeros en la última posición 
                } catch( NumberFormatException e ){ 
                 //significa que no es un numero por lo que procedemos a identificar qué es 
                 //llamamos a la función que nos dice lo que es 
                    int precedence = checkPrecedence(aux); 
                    aux2 = operatorStack.peekFirst(); 
                    if(aux2 != null){//comprueba si la cola está vacia 
                        int lastPrecedence = checkPrecedence(aux2);  
                        if(precedence == 3){ 
                           //se trata de un parentesis de cierre 
                            operatorStack.removeFirst(); 
                            aux2 = operatorStack.pollFirst(); 
                            lastPrecedence = checkPrecedence(aux2); 
                            while( lastPrecedence != 0){ // Hasta que llega al de apertura 
                               numbersQueue.addLast(aux2); 
                               aux2 = operatorStack.pollFirst(); 
                               lastPrecedence = checkPrecedence(aux2); 
                           } 
                       } 
                       if (precedence > lastPrecedence){ 
                            operatorStack.addFirst(aux); 
                       }else{ 
                            aux2 = operatorStack.pollFirst(); 
                            numbersQueue.addLast(aux2); 
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
                default:  precedence = 2;  
                break; 
            } 
        return precedence; 
    } 
    static float evaluator(LinkedList<String> input){   
        // Codigo para hacer
        float result=0;
        return result;
    }
  
}
