/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import java.sql.Date;

/**
 *
 * @author Morten
 */
public class BEWage {
    
    private String m_lastName;
    private String m_firstName;
    private String m_incidentName;
    private Date m_date;
    private String m_incidentType;
    private String m_roleType;
    private int m_hours;
    private int m_salaryId;
    
    public BEWage(String lastname, String firstname, String incidentname, Date date
            , String incidenttype, String roletype,int hours, int salaryid){
        
        m_lastName = lastname;
        m_firstName = firstname;
        m_incidentName = incidentname;
        m_date = date;
        m_incidentType = incidenttype;
        m_roleType = roletype;
        m_hours = hours;
        m_salaryId = salaryid;
    }

    /**
     * @return the m_lastName
     */
    public String getM_lastName() {
        return m_lastName;
    }

    /**
     * @param m_lastName the m_lastName to set
     */
    public void setM_lastName(String m_lastName) {
        this.m_lastName = m_lastName;
    }

    /**
     * @return the m_firstName
     */
    public String getM_firstName() {
        return m_firstName;
    }

    /**
     * @param m_firstName the m_firstName to set
     */
    public void setM_firstName(String m_firstName) {
        this.m_firstName = m_firstName;
    }

    /**
     * @return the m_incidentName
     */
    public String getM_incidentName() {
        return m_incidentName;
    }

    /**
     * @param m_incidentName the m_incidentName to set
     */
    public void setM_incidentName(String m_incidentName) {
        this.m_incidentName = m_incidentName;
    }

    /**
     * @return the m_date
     */
    public Date getM_date() {
        return m_date;
    }

    /**
     * @param m_date the m_date to set
     */
    public void setM_date(Date m_date) {
        this.m_date = m_date;
    }

    /**
     * @return the m_incidentType
     */
    public String getM_incidentType() {
        return m_incidentType;
    }

    /**
     * @param m_incidentType the m_incidentType to set
     */
    public void setM_incidentType(String m_incidentType) {
        this.m_incidentType = m_incidentType;
    }

    /**
     * @return the m_roleType
     */
    public String getM_roleType() {
        return m_roleType;
    }

    /**
     * @param m_roleType the m_roleType to set
     */
    public void setM_roleType(String m_roleType) {
        this.m_roleType = m_roleType;
    }

    /**
     * @return the m_salaryId
     */
    public int getM_salaryId() {
        return m_salaryId;
    }

    /**
     * @param m_salaryId the m_salaryId to set
     */
    public void setM_salaryId(int m_salaryId) {
        this.m_salaryId = m_salaryId;
    }

    /**
     * @return the m_hours
     */
    public int getM_hours() {
        return m_hours;
    }

    /**
     * @param m_hours the m_hours to set
     */
    public void setM_hours(int m_hours) {
        this.m_hours = m_hours;
    }
    
    
}
