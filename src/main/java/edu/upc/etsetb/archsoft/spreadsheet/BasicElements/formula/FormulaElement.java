/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula;

import edu.upc.etsetb.archsoft.spreadsheet.spreadsheet.Visitador;
import java.util.LinkedList;

/**
 *
 * @author Alex
 */
public abstract class FormulaElement {
    int token; //Token Variable int,string o otro?
    String sequence;
    boolean isFunction;
    public FormulaElement(int token, String sequence) { //Token Variable int,string o otro?
        super();
        this.token = token; //Token Variable int,string o otro?
        this.sequence = sequence;
    }

    public FormulaElement() {
      
    }
    
    public String getSequence(){
        return this.sequence;
    }
    public int getToken(){
        return this.token;
    }
    public void changeSequence(String newSequence){
        this.sequence = newSequence;
        //System.out.println("changed Sequence: " +this.sequence);
    }
    public void changeToken(int newToken){
       this.token = newToken;
    }
       public boolean isfunction(){
       return this.isFunction;
   }
     public void setIsFunction(){
       this.isFunction = true;
   }
    
    public abstract float acceptVisitor (Visitador visitador,LinkedList input);
}

