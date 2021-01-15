/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.upc.etsetb.archsoft.spreadsheet.spreadsheet;

import edu.upc.etsetb.archsoft.spreadsheet.worksheet.formula.CellReference;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class containing all the functions needed to check circular references
 *
 * @author Alex Eslava and Amaya Balaguer
 */
public class CircularReferencer {

    public void checkCircularReference(LinkedList<String> dependencies, String cell, Spreadsheet excel) {
        int[] coordinates;
        LinkedList<String> auxDependencies = new LinkedList<>(dependencies);
        String reference;
        while (!auxDependencies.isEmpty()) {
            reference = auxDependencies.pop();
            try {
                Controller.validCell(reference);
                coordinates = CellReference.getCoordinates(reference);
                if (excel.spreadsheet[coordinates[0]][coordinates[1]].content.getDependentCells().contains(reference)) {

                }
            } catch (java.lang.NumberFormatException e) {

            } catch (UnknownReferenceException ex) {
                Logger.getLogger(CircularReferencer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Void that updates the list of references of each cell whenever its
     * formula contain a reference.
     *
     * @param dependencies list of dependencies
     * @param cell String containing the cell reference
     * @param excel current spreadsheet.
     */
    public static void updateReferences(LinkedList<String> dependencies, String cell, Spreadsheet excel) {
        int[] coordinates;
        LinkedList<String> auxDependencies = new LinkedList<>(dependencies);
        String reference;
        deleteReferences(cell, excel);
        coordinates = CellReference.getCoordinates(cell);

        excel.spreadsheet[coordinates[0]][coordinates[1]].content.setDependencies(dependencies);

        while (!auxDependencies.isEmpty()) {
            reference = auxDependencies.pop();
            try {
                Controller.validCell(reference);
                coordinates = CellReference.getCoordinates(reference);
                excel.spreadsheet[coordinates[0]][coordinates[1]].content.modifyDependentCells(cell);
            } catch (java.lang.NumberFormatException e) {

            } catch (UnknownReferenceException ex) {
                Logger.getLogger(CircularReferencer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * It deletes itself from the dependent cells list of other cells.
     *
     * @param cell cell to be deleted
     * @param excel current spreadsheet
     */
    public static void deleteReferences(String cell, Spreadsheet excel) {
        int[] coordinates;
        coordinates = CellReference.getCoordinates(cell);

        String reference;

        while (!excel.spreadsheet[coordinates[0]][coordinates[1]].content.getDependencies().isEmpty()) {

            reference = excel.spreadsheet[coordinates[0]][coordinates[1]].content.getDependencies().pop();

            coordinates = CellReference.getCoordinates(reference);
            excel.spreadsheet[coordinates[0]][coordinates[1]].content.deleteDependency(cell);

        }

    }

}
