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
    public static LinkedList shuntingYardAlgorithm(LinkedList<FormulaElement> input, Cell[][] spreadsheet){ // Consideramos que la función es válida 
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
                System.out.println("soy un " + aux.getSequence()); 
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
                        if(aux.getToken() == 12){  
                               //System.out.println("He encontrado una referencia "); 
                                aux.changeToken(11);
                                aux.changeSequence(String.valueOf(getContent(aux.getSequence(), spreadsheet)));
                                numbersQueue.addLast(aux);
                                //numbersQueue.addLast(aux);
                            }else if(precedence != 4){
                                if(precedence == 3){
                                   //se trata de un parentesis de cierre
                                   //System.out.println("soy un cierre! "); 
                                    aux2 = operatorStack.pollFirst(); 
                                    if(aux2 != null){
                                        lastPrecedence = checkPrecedence(aux2.getSequence()); 
                                    while( lastPrecedence != 0){ // Hasta que llega al de apertura 
                                       numbersQueue.addLast(aux2); 
                                      aux2 = operatorStack.pollFirst(); 
                                      //System.out.println("He llegado al final "); 
                                       if(aux2 != null){
                                           lastPrecedence = checkPrecedence(aux2.getSequence()); 
                                       }
                                   } 

                                    aux2 = operatorStack.pollFirst(); 
                                    }

                                } else{if (precedence >= lastPrecedence){ 
                                        operatorStack.addFirst(aux);


                                    }else if(lastPrecedence > precedence){

                                        aux2 = operatorStack.pollFirst(); 
                                        numbersQueue.addLast(aux2); 
                                        operatorStack.addFirst(aux);


                                        }              
                                    }
                        }else{ //para las funciones
                            numbersQueue.addLast(aux);
                            auxiliarList = getFunctionArguments(input,spreadsheet);  
                            System.out.println("He vuelto "); 
                            while(!auxiliarList.isEmpty() && aux != null){
                                aux = auxiliarList.pop();
                                numbersQueue.addLast(aux);
                               System.out.println("Me dispongo a vaciar la lista " + aux.getSequence()); 
                                //numbersQueue.addLast(auxiliarList.pop());
                              
                             }
                            System.out.println("Lista Vaciada! " + aux.getSequence());
                        }
                    }else {
                        System.out.println("Entro en el else ");
                        if(checkPrecedence(aux.getSequence()) == 3){
                      
                            numbersQueue.addLast(aux);
                        }else{
                            operatorStack.addFirst(aux);
                        }
                        
                    } 
                } 
            }
            //llegados al final toca pasarlos todos 
            System.out.println("vacio operadores" + operatorStack.size());
            while(!operatorStack.isEmpty()){ 
                System.out.println("vacio operadores");
                System.out.println("el último es " + numbersQueue.peekLast().getSequence() );
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
                case "+": precedence = 1; // pasas a ser el 2
                break; 
                case "-": precedence = 1; //pasas a ser el 1
                break; 
                case "*": precedence = 2; // pasas a ser el 4
                break; 
                case "/": precedence = 2; // pasas a ser el 5
                break; 
                case ":": precedence = 5; // pasas a ser el 6
                break;
                case ";": precedence = 5; // pasas a ser el 7
                break;
                default:  precedence = 4;  // pasas a ser el 8
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
    static LinkedList getFunctionArguments(LinkedList<FormulaElement> input, Cell[][] spreadsheet){
   LinkedList<FormulaElement> arguments = new LinkedList(); 
        LinkedList<FormulaElement> auxiliarList = new LinkedList(); 
        int precedence = 0; 
        FormulaElement aux; 
        aux = input.poll(); 
        precedence = checkPrecedence(aux.getSequence()); 
         
        while(precedence != 3){ 
            //System.out.println(" printo cosas "); 
            
            if(precedence == 4 && aux.getToken() != 12){ //tengo una función 
                
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
             
            }if(aux.getToken() == 12){
                aux.changeSequence(getContent(aux.getSequence(), spreadsheet));
                aux.changeToken(11);
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
    
    
    //Obtiene el contenido de una referencia y lo retorna como un string
     static String getContent(String reference, Cell[][] spreadsheet) {
         StringBuffer letter = new StringBuffer();
         StringBuffer num = new StringBuffer();
         int row, col = 0, aux;
        
         
         for(int i = 0; i < reference.length(); i++){
               if (Character.isDigit(reference.charAt(i))) 
                num.append(reference.charAt(i)); 
            else if(Character.isAlphabetic(reference.charAt(i))) 
                letter.append(reference.charAt(i)); 
            
        } 
         for (int e = 0; e < letter.length(); e++){
             aux = (int)letter.charAt(e);
             col = col * 26 + (aux - 'A') + 1;
         }
       
        row = Integer.parseInt(num.toString());
        
        //col = Integer.parseInt(letter.toString());
        //System.out.println("number " + num); 
        //System.out.println("letter " + letter);
        //System.out.println(" Row " + row);
        //System.out.println("col " + col);
        //System.out.println("content " + spreadsheet[row][col].content.getContent());
         //System.out.println("convertedCol " + col); 
          return  spreadsheet[row][col].content.getContent();

}
     
  
}
