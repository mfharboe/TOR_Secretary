/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.BEFireman;
import BE.BESalary;
import BE.BEWage;
import DAL.DALPDF;
import DAL.DALRead;
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
    ArrayList<BEWage> tblSalary = new ArrayList<>();
    ArrayList<BEFireman> firemen;
    ArrayList<BESalary> salary;

    private BLLSecretary() {
    }

    public static BLLSecretary getInstance() {
        if (m_instance == null) {
            m_instance = new BLLSecretary();
        }
        return m_instance;

    }

    public ArrayList<BEWage> BLLSearch(String from, String to) {

        try {
            tblSalary = DALRead.getInstance().readInfo(from, to);
        } catch (SQLException ex) {
            Logger.getLogger(BLLSecretary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tblSalary;

    }

    public ArrayList<BEWage> BLLSearchWithFiremen(BEFireman fireman, String from, String to) {
        try {
            ArrayList<BEWage> besalary = new ArrayList<>();
            ArrayList<BEWage> info = new ArrayList<>();

            info = DALRead.getInstance().readInfo(from, to);
            for (BEWage be : info) {
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
    
    public ArrayList<BESalary> readAllSalaries(){
        if(salary == null)
            try {
            salary = DALRead.getInstance().readAllSalaries();
        } catch (SQLException ex) {
            Logger.getLogger(BLLSecretary.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return salary;
    }
    
    public void sendToPdfFireman(ArrayList<BEWage> be,BEFireman befireman, String from, String to ){
        DALPDF.getInstance().printPDFFireman(be,befireman, from, to, readAllSalaries());
    }
    public void sendToPdfRooster(ArrayList<BEWage> be, String from, String to ){
        DALPDF.getInstance().printPDFRooster(be, from, to);
    }
}
