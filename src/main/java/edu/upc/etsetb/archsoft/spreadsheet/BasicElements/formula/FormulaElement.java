/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula;

/**
 *
 * @author Alex
 */
public class FormulaElement {
    int token; //Token Variable int,string o otro?
    String sequence;

    public FormulaElement(int token, String sequence) { //Token Variable int,string o otro?
        super();
        this.token = token; //Token Variable int,string o otro?
        this.sequence = sequence;
    }

    public FormulaElement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String getSequence(){
        return this.sequence;
    }
    public int getToken(){
        return this.token;
    }
    public void changeSequence(String newSequence){
        this.sequence = newSequence;
        System.out.println("changed Sequence: " +this.sequence);
    }
    public void changeToken(int newToken){
       this.token = newToken;
    }
    
}
