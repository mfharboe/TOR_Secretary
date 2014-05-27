/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Interfaces;

import BE.BEAlarm;
import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentType;
import BE.BEMaterial;
import BE.BESalary;
import BE.BEUsage;
import BE.BEZipcode;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Morten
 */
public interface IDALRead {
/**
 * Read all Alarms from DB
 * @return
 * @throws SQLException 
 */
    ArrayList<BEAlarm> readAlarms() throws SQLException;
/**
 * Read all firemen from DB
 * @return
 * @throws SQLException 
 */
    ArrayList<BEFireman> readFiremen() throws SQLException;
/**
 * Read incident Details from DB where Incident is done
 * @return
 * @throws SQLException 
 */
    ArrayList<BEIncidentDetails> readIncidentDetails() throws SQLException;
/**
 * Read all incident types from DB
 * @return
 * @throws SQLException 
 */
    ArrayList<BEIncidentType> readIncidentTypes() throws SQLException;
/**
 * Read incidents from DB where incidents is done
 * @return
 * @throws SQLException 
 */
    ArrayList<BEIncident> readIncidents() throws SQLException;
/**
 * Read all Salary info from DB where incident is done
 * @param from
 * @param to
 * @return
 * @throws SQLException 
 */
    ArrayList<BESalary> readInfo(String from, String to) throws SQLException;

    /**
     * Read all MAterial info from DB
     *
     * @return ArrayList of Material
     * @throws SQLException
     */
    ArrayList<BEMaterial> readMaterial() throws SQLException;
/**
 * Read all usages from DB
 * @return
 * @throws SQLException 
 */
    ArrayList<BEUsage> readUsage() throws SQLException;
/**
 * Read all zipcodes from DB
 * @return
 * @throws SQLException 
 */
    ArrayList<BEZipcode> readZipcodes() throws SQLException;
    
}
