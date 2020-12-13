/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.BasicElements;

 
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import java.util.List; 
import java.util.LinkedList; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
/** 
 * 
 * @author Alex 
 */ 
public class CellFormula extends CellContent{ 
   
    Number value;
    LinkedList <FormulaElement> list; // expresión postfix

    @Override
    public String getContent(){
        
        return this.content;
    }
    @Override
    public void setContent(String newContent){

        this.content = newContent;

    }
    
   public void setCellFormula(String content, LinkedList list){
       this.content = content;
       this.list = (LinkedList) list.clone();
   }
     public LinkedList getFormula (){
       return this.list;
   }

} 
