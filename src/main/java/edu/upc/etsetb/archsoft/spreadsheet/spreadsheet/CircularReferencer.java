/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.BasicElements.formula.CellReference;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class containing all the functions needed to check circular references
 * @author Alex Eslava and Amaya Balaguer
 */
public class CircularReferencer {

    public void checkCircularReference() {

    }
/**
 * Void that updates the list of references of each cell whenever its formula contain a reference. 
 * @param dependencies list of dependencies 
 * @param cell String containing the cell reference 
 * @param excel current spreadsheet.
 */
    public static void updateReferences(LinkedList<String> dependencies, String cell, Spreadsheet excel) {
        int[] coordinates;
        LinkedList<String> auxDependencies = new LinkedList<>(dependencies);
        String reference;
        while (!auxDependencies.isEmpty()) {
            reference = auxDependencies.pop();
            try {
                Controller.validCell(reference);
                coordinates = CellReference.getCoordinates(reference);
                excel.spreadsheet[coordinates[0]][coordinates[1]].content.modifyDependencies(cell);
            } catch (java.lang.NumberFormatException e) {

            } catch (UnknownReferenceException ex) {
                Logger.getLogger(CircularReferencer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
