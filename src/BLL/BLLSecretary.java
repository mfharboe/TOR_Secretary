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
            BLLError.getInstance().readSalaryError();
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
           BLLError.getInstance().readSalaryError();}
        return null;

    }

    public ArrayList<BEFireman> readAllFiremen() {
        if (firemen == null) {
            try {
                firemen = DALRead.getInstance().readFiremen();
            } catch (SQLException ex) {
                BLLError.getInstance().readFiremanError();
                return null;
            }
        }
        return firemen;
    }

    
    public ArrayList<BEIncident> readIncidents(){
        if(incidents == null){
            try {
                incidents = DALRead.getInstance().readIncidents();
            } catch (SQLException ex) {
                BLLError.getInstance().readIncidentError();
                return null;
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
               BLLError.getInstance().readUsageError();
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
               BLLError.getInstance().readIncidentDetailsError();
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
