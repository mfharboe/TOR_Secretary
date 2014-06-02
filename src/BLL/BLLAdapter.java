/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.sql.Date;


/**
 *
 * @author Morten
 */
public class BLLAdapter {

    private static BLLAdapter m_instance;

    private BLLAdapter() {
    }

    public static BLLAdapter getInstance() {
        if (m_instance == null) {
            m_instance = new BLLAdapter();
        }
        return m_instance;
    }

    public Date convertDate(java.util.Date date) {
        java.sql.Date newDate = new java.sql.Date(date.getTime());
        return newDate;

    }
}
