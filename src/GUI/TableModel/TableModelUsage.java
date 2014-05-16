/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModel;

import BE.BEUsage;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Morten
 */
public class TableModelUsage extends AbstractTableModel{
    
    private ArrayList<BEUsage> usage;
    private final String[] colNames = {"Materiel",
        "Antal"};
    private final Class[] classes = {String.class,
        String.class,
        
       };

    /**
     * Updates the model
     *
     * @param allUsages
     */
    public TableModelUsage(ArrayList<BEUsage> allusages) {
        usage = allusages;
        fireTableDataChanged();
    }

    /**
     *
     * @return the number of rows
     */
    @Override
    public int getRowCount() {
        return usage.size();
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
        BEUsage e = usage.get(row);
        switch (col) {
            case 0:
                return e.getM_material().getM_description();
            case 1:
                return e.getM_amount();
           
            
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
     * Sets the content of the table model to the given list of usages
     *
     * @param usageList
     */
    public void setUsageList(ArrayList<BEUsage> usageList) {
        usage = usageList;
        fireTableDataChanged();
    }

    /**
     *
     * @param row
     * @return the usage set at the given row index
     */
    public BEUsage getUsageByRow(int row) {
        return usage.get(row);
    }
    
    public ArrayList<BEUsage> getUsageListByRow(int... rows){
        ArrayList<Integer> tmp = new ArrayList<>();
        ArrayList<BEUsage> beusage = new ArrayList<>();
        for(int i = 0; i < rows.length; i++){
            tmp.add(rows[i]);
        }
        for(int u : tmp){
            beusage.add(usage.get(u));
            
        }
        return beusage;
        
    }
    
}
