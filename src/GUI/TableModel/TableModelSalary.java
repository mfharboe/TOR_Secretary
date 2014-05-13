/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModel;

import BE.BESalary;
import java.awt.List;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Morten
 */
public class TableModelSalary extends AbstractTableModel {

    private ArrayList<BESalary> salary;
    private final String[] colNames = {"Efternavn",
        "Fornavn",
        "Indsats navn",
        "Dato",
        "Indsats type",
        "Funktion",
        "Lønkode"};
    private final Class[] classes = {String.class,
        String.class,
        String.class,
        Date.class,
        String.class,
        String.class,
        int.class};

    /**
     * Updates the model
     *
     * @param allSalaries
     */
    public TableModelSalary(ArrayList<BESalary> allSalaries) {
        salary = allSalaries;
        fireTableDataChanged();
    }

    /**
     *
     * @return the number of rows
     */
    @Override
    public int getRowCount() {
        return salary.size();
    }

    /**
     *
     * @return the number of columns
     */
    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    /**
     *
     * @param row
     * @param col
     * @return the selected row
     */
    @Override
    public Object getValueAt(int row, int col) {
        BESalary e = salary.get(row);
        switch (col) {
            case 0:
                return e.getM_lastName();
            case 1:
                return e.getM_firstName();
            case 2:
                return e.getM_incidentName();
            case 3:
                return e.getM_date();
            case 4:
                return e.getM_incidentType();
            case 5:
                return e.getM_roleType();
            case 6:
                return e.getM_salaryId();
        }

        return null;
    }

    /**
     *
     * @param col
     * @return the column name
     */
    @Override
    public String getColumnName(int col) {

        return colNames[col];
    }

    /**
     *
     * @param col
     * @return the column class
     */
    @Override
    public Class<?> getColumnClass(int col) {
        return classes[col];
    }

    /**
     *
     * @param row
     * @param col
     * @return true if the cells are editable
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * Sets the content of the table model to the given list of salaries
     *
     * @param SalaryList
     */
    public void setSalaryList(ArrayList<BESalary> salaryList) {
        salary = salaryList;
        fireTableDataChanged();
    }

    /**
     *
     * @param row
     * @return the salary set at the given row index
     */
    public BESalary getSalaryByRow(int row) {
        return salary.get(row);
    }
    
    public ArrayList<BESalary> getSalaryListByRow(int... rows){
        ArrayList<Integer> tmp = new ArrayList<>();
        ArrayList<BESalary> besalary = new ArrayList<>();
        for(int i = 0; i < rows.length; i++){
            tmp.add(rows[i]);
        }
        for(int u : tmp){
            besalary.add(salary.get(u));
            
        }
        return besalary;
        
    }
}
