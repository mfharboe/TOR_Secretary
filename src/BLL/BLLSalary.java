/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEUsage;

import BE.BESalary;
import DAL.DALRead;
import DAL.Interfaces.IDALRead;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morten
 */
public class BLLSalary {

    private static BLLSalary m_instance;
    ArrayList<BESalary> salaries = new ArrayList<>();
    ArrayList<BEFireman> firemen;
    ArrayList<BEIncidentDetails> incidentDetails;
    ArrayList<BEUsage> usage;
    ArrayList<BEIncident> incidents;
    IDALRead dalRead;
/**
 * Creates a private form of BLLSalary
 */
    private BLLSalary() {
    }
/**
 * 
 * @return Current instance of BLLSalary 
 */
    public static BLLSalary getInstance() {
        if (m_instance == null) {
            m_instance = new BLLSalary();
        }
        return m_instance;

    }
    /**
     * Sets which interface should be used
     * @param d 
     */
    public void setDAL(IDALRead d){
        dalRead = d;
        
    }
/**
 * Read salaries in time a specific time period 
 * @param from
 * @param to
 * @return 
 */
    public ArrayList<BESalary> readSalariesInTimePeriod(String from, String to) {

        try {
            salaries = dalRead.readInfo(from, to);
        } catch (SQLException ex) {
            BLLError.getInstance().readSalaryError();
        }
        return salaries;

    }
/**
 *  Read salaries in time a specific time period with a specific fireman
 * @param fireman
 * @param from
 * @param to
 * @return 
 */
    public ArrayList<BESalary> readSalariesInTimePeriodWithFiremen(BEFireman fireman, String from, String to) {
        try {
            ArrayList<BESalary> besalary = new ArrayList<>();
            ArrayList<BESalary> info = new ArrayList<>();

            info = dalRead.readInfo(from, to);
            for (BESalary be : info) {
                if (fireman != null
                        && fireman.getM_lastName().equals(be.getM_lastName())
                        && fireman.getM_firstName().equals(be.getM_firstName())) {
                    besalary.add(be);
                } else if (fireman == null && fireman.getM_lastName().equals(be.getM_lastName())
                        && fireman.getM_firstName().equals(be.getM_firstName())) {
                    return info;

                }
            }
            return besalary;
        } catch (SQLException ex) {
           BLLError.getInstance().readSalaryError();}
        return null;

    }
/**
 * Reads all firemen
 * @return Arraylist firemen
 */
    public ArrayList<BEFireman> readAllFiremen() {
        if (firemen == null) {
            try {
                firemen = dalRead.readFiremen();
            } catch (SQLException ex) {
                BLLError.getInstance().readFiremanError();
                return null;
            }
        }
        return firemen;
    }

    /**
     * Reads all incidents
     * @return Arraylists incidents
     */
    public ArrayList<BEIncident> readIncidents(){
        if(incidents == null){
            try {
                incidents = dalRead.readIncidents();
            } catch (SQLException ex) {
                BLLError.getInstance().readIncidentError();
                return null;
            }
        }
        return incidents;
        
    }
/**
 * Sort incidents by dates
 * @param from
 * @param to
 * @return ArrayList incidentSorted
 */
    public ArrayList<BEIncident> sortIncidents(Date from, Date to) {
        ArrayList<BEIncident> incidentsSorted = new ArrayList<>();
       
            for (BEIncident beincident : readIncidents()) {
                if (beincident.getM_date().after(from) && beincident.getM_date().before(to)) {
                    incidentsSorted.add(beincident);

                }
            }
      
        return incidentsSorted;
    }
/**
 * Read usages
 * @return Arraylists usage
 */
    public ArrayList<BEUsage> readUsages() {
        if (usage == null) {
            try {
                usage = dalRead.readUsage();
            } catch (SQLException ex) {
               BLLError.getInstance().readUsageError();
            }
        }
        return usage;
    }
/**
 * Sort usages by incident
 * @param beincident
 * @return Arraylist usagesorted
 */
    public ArrayList<BEUsage> sortUsages(BEIncident beincident) {
        ArrayList<BEUsage> usagesorted = new ArrayList<>();

        for (BEUsage usage : readUsages()) {
            if (usage.getM_incident().getM_id() == beincident.getM_id()) {
                usagesorted.add(usage);
            }
        }

        return usagesorted;

    }
/**
 * Read all incident details
 * @return Arraylist incidentDetails
 */
    public ArrayList<BEIncidentDetails> readIncidentDetails() {
        if (incidentDetails == null) {
            try {
                incidentDetails = dalRead.readIncidentDetails();
            } catch (SQLException ex) {
               BLLError.getInstance().readIncidentDetailsError();
            }
        }
        return incidentDetails;
    }
/**
 * Sort incident details by incident
 * @param beincident
 * @return sortedIncidentDetails
 */
    public ArrayList<BEIncidentDetails> sortIncidentDetails(BEIncident beincident) {
        ArrayList<BEIncidentDetails> sortedIncidentDetails = new ArrayList<>();
        for (BEIncidentDetails beincidentDetails : readIncidentDetails()) {
            if (beincidentDetails.getM_incident().getM_id() == beincident.getM_id()) {
                sortedIncidentDetails.add(beincidentDetails);
            }

        }

        return sortedIncidentDetails;
    }
}
