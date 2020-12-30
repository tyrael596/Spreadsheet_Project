/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.BasicElements;

 
import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.FormulaElement;
import java.util.LinkedList; 
 
/** 
 * 
 * @author Alex 
 */ 
public class CellFormula extends CellContent { 
   
    String value = "";
    LinkedList <FormulaElement> list; // expresión postfix

    @Override
    public String getContent()  {
        return this.value;
    }
    @Override
    public void setContent(String newContent)  {

        this.content = newContent;


    }

     public LinkedList getFormula (){
       return this.list;
   }

    public void modifyContent(String newContent,LinkedList <FormulaElement> list ) {
       this.value = newContent;
       this.list = list;
    }
        @Override
    public void modifyContent(String newContent) {
       this.value = newContent;

    }

    @Override
    public void setContent(String newContent, LinkedList<FormulaElement> list) {
        System.out.println("content " + newContent);
       this.value = newContent;
       this.list = list; 

    }

} 
