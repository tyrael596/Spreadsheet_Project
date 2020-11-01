/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.blocks;

/**
 *
 * @author Alex
 */
public interface content {
    String value;
    abstract void getContent();
    abstract void error();   
}
