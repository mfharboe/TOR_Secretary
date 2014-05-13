/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.BEFireman;
import BE.BESalary;
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
    ArrayList<BESalary> tblSalary = new ArrayList<>();
    ArrayList<BEFireman> firemen;

    private BLLSecretary() {
    }

    public static BLLSecretary getInstance() {
        if (m_instance == null) {
            m_instance = new BLLSecretary();
        }
        return m_instance;

    }

    public ArrayList<BESalary> BLLSearch(String from, String to) {

        try {
            tblSalary = DALRead.getInstance().readInfo(from, to);
        } catch (SQLException ex) {
            Logger.getLogger(BLLSecretary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tblSalary;

    }

    public ArrayList<BESalary> BLLSearchWithFiremen(BEFireman fireman, String from, String to) {
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
    
    public void sendToPdf(ArrayList<BESalary> be ){
        DALPDF.getInstance().printPDF(be);
    }
}
