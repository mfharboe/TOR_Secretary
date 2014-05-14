/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Morten
 */
public class BESalary {
    
    private int m_id;
    private String m_salaryDescription;
    
    public BESalary(int id, String salaryDescription){
        
        id = m_id;
        salaryDescription = m_salaryDescription;
        
    }

    /**
     * @return the m_id
     */
    public int getM_id() {
        return m_id;
    }

    /**
     * @param m_id the m_id to set
     */
    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    /**
     * @return the m_salaryDescription
     */
    public String getM_salaryDescription() {
        return m_salaryDescription;
    }

    /**
     * @param m_salaryDescription the m_salaryDescription to set
     */
    public void setM_salaryDescription(String m_salaryDescription) {
        this.m_salaryDescription = m_salaryDescription;
    }
    
}
