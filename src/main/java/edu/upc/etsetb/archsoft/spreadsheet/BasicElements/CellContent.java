/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.BasicElements;

/**
 *
 * @author Alex
 */
public abstract class CellContent {
    public String content = ".";
    

    
   public abstract  String getContent();
   public abstract  void setContent(String newContent);
    //abstract void error();   
}
