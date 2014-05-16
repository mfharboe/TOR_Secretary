/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.TableModel;

import BE.BEIncident;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Morten
 */
public class TableModelIncident extends AbstractTableModel{
    
     private ArrayList<BEIncident> incident;
    private final String[] colNames = {"Indsats Navn",
        "Dato",
        "Indsats Type",
        "Tidspunkt"};
    private final Class[] classes = {String.class,
        String.class,
        String.class,
        String.class
        
       };

    /**
     * Updates the model
     *
     * @param allincidents
     */
    public TableModelIncident(ArrayList<BEIncident> allIncidents) {
        incident = allIncidents;
        fireTableDataChanged();
    }

    /**
     *
     * @return the number of rows
     */
    @Override
    public int getRowCount() {
        return incident.size();
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
        BEIncident e = incident.get(row);
        switch (col) {
            case 0:
                return e.getM_incidentName();
            case 1:
                return e.getM_date();
            case 2:
                return e.getM_incidentType().getM_description();
            case 3:
                return e.getM_time();
           
            
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
     * Sets the content of the table model to the given list of incidents
     *
     * @param SalaryList
     */
    public void setSalaryList(ArrayList<BEIncident> incidentList) {
        incident = incidentList;
        fireTableDataChanged();
    }

    /**
     *
     * @param row
     * @return the incident set at the given row index
     */
    public BEIncident getIncidentByRow(int row) {
        return incident.get(row);
    }
    
    public ArrayList<BEIncident> getIncidentListByRow(int... rows){
        ArrayList<Integer> tmp = new ArrayList<>();
        ArrayList<BEIncident> beincident = new ArrayList<>();
        for(int i = 0; i < rows.length; i++){
            tmp.add(rows[i]);
        }
        for(int u : tmp){
            beincident.add(incident.get(u));
            
        }
        return beincident;
        
    }
    
}
