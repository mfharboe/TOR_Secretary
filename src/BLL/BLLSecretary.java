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
import DAL.DALPDF;
import DAL.DALRead;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morten
 */
public class BLLSecretary {

    private static BLLSecretary m_instance;
    ArrayList<BESalary> salaries = new ArrayList<>();
    ArrayList<BEFireman> firemen;
    ArrayList<BEIncidentDetails> incidentDetails;
    ArrayList<BEUsage> usage;
    ArrayList<BEIncident> incidents;

    private BLLSecretary() {
    }

    public static BLLSecretary getInstance() {
        if (m_instance == null) {
            m_instance = new BLLSecretary();
        }
        return m_instance;

    }

    public ArrayList<BESalary> readSalariesInTimePeriod(String from, String to) {

        try {
            salaries = DALRead.getInstance().readInfo(from, to);
        } catch (SQLException ex) {
            Logger.getLogger(BLLSecretary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return salaries;

    }

    public ArrayList<BESalary> readSalariesInTimePeriodWithFiremen(BEFireman fireman, String from, String to) {
        try {
            ArrayList<BESalary> besalary = new ArrayList<>();
            ArrayList<BESalary> info = new ArrayList<>();

            info = DALRead.getInstance().readInfo(from, to);
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
            Logger.getLogger(BLLSecretary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<BEFireman> readAllFiremen() {
        if (firemen == null) {
            try {
                firemen = DALRead.getInstance().readFiremen();
            } catch (SQLException ex) {
                return null;
            }
        }
        return firemen;
    }

    public void sendToPdfFireman(ArrayList<BESalary> be, BEFireman befireman, String from, String to) {

        DALPDF.getInstance().printPDFFireman(be, befireman, from, to);
    }

    public void sendToPdfRooster(ArrayList<BESalary> be, String from, String to) {
        DALPDF.getInstance().printPDFRooster(be, from, to);
    }
    public void sendToPdfUsage(ArrayList<BEUsage> beusage, BEIncident beincident) {
       DALPDF.getInstance().printPdfUsage(sortIncidentDetails(beincident), beincident, beusage);
    }
    
    public ArrayList<BEIncident> readIncidents(){
        if(incidents == null){
            try {
                incidents = DALRead.getInstance().readIncidents();
            } catch (SQLException ex) {
                Logger.getLogger(BLLSecretary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return incidents;
        
    }

    public ArrayList<BEIncident> sortIncidents(Date from, Date to) {
        ArrayList<BEIncident> incidentsSorted = new ArrayList<>();
       
            for (BEIncident beincident : readIncidents()) {
                if (beincident.getM_date().after(from) && beincident.getM_date().before(to)) {
                    incidentsSorted.add(beincident);

                }
            }
      
        return incidentsSorted;
    }

    public ArrayList<BEUsage> readUsages() {
        if (usage == null) {
            try {
                usage = DALRead.getInstance().readUsage();
            } catch (SQLException ex) {
                Logger.getLogger(BLLSecretary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usage;
    }

    public ArrayList<BEUsage> sortUsages(BEIncident beincident) {
        ArrayList<BEUsage> usagesorted = new ArrayList<>();

        for (BEUsage usage : readUsages()) {
            if (usage.getM_incident().getM_id() == beincident.getM_id()) {
                usagesorted.add(usage);
            }
        }

        return usagesorted;

    }

    public ArrayList<BEIncidentDetails> readIncidentDetails() {
        if (incidentDetails == null) {
            try {
                incidentDetails = DALRead.getInstance().readIncidentDetails();
            } catch (SQLException ex) {
                Logger.getLogger(BLLSecretary.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return incidentDetails;
    }

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
