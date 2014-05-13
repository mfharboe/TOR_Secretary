package BE;



public class BEFireman {

    private int m_id;
    private String m_firstName;
    private String m_lastName;
    private String m_address;
    private BEZipcode m_zipCode;
    private int m_phone;
    private int m_paymentNumber;
    private boolean m_isTeamLeader;
    private String m_photoPath;
    private String m_cpr;

    /**
     * Read Fireman
     *
     * @param id
     * @param firstname
     * @param lastname
     * @param address
     * @param zipcode
     * @param phone
     * @param paymentnumber
     * @param isteamleader
     * @param photopath
     * @param cpr
     */
    public BEFireman(int id, String firstname, String lastname, String address, BEZipcode zipcode,
            int phone, int paymentnumber, boolean isteamleader, String photopath, String cpr) {

        m_id = id;
        m_firstName = firstname;
        m_lastName = lastname;
        m_address = address;
        m_zipCode = zipcode;
        m_phone = phone;
        m_paymentNumber = paymentnumber;
        m_isTeamLeader = isteamleader;
        m_photoPath = photopath;
        m_cpr = cpr;

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
     * @return the m_address
     */
    public String getM_address() {
        return m_address;
    }

    /**
     * @param m_address the m_address to set
     */
    public void setM_address(String m_address) {
        this.m_address = m_address;
    }

    /**
     * @return the m_phone
     */
    public int getM_phone() {
        return m_phone;
    }

    /**
     * @param m_phone the m_phone to set
     */
    public void setM_phone(int m_phone) {
        this.m_phone = m_phone;
    }

    /**
     * @return the m_paymentNumber
     */
    public int getM_paymentNumber() {
        return m_paymentNumber;
    }

    /**
     * @param m_paymentNumber the m_paymentNumber to set
     */
    public void setM_paymentNumber(int m_paymentNumber) {
        this.m_paymentNumber = m_paymentNumber;
    }

    /**
     * @return the m_isTeamLeader
     */
    public boolean isM_isTeamLeader() {
        return m_isTeamLeader;
    }

    /**
     * @param m_isTeamLeader the m_isTeamLeader to set
     */
    public void setM_isTeamLeader(boolean m_isTeamLeader) {
        this.m_isTeamLeader = m_isTeamLeader;
    }

    /**
     * @return the m_photoPath
     */
    public String getM_photoPath() {
        return m_photoPath;
    }

    /**
     * @param m_photoPath the m_photoPath to set
     */
    public void setM_photoPath(String m_photoPath) {
        this.m_photoPath = m_photoPath;
    }

    /**
     * @return the m_zipCode
     */
    public BEZipcode getM_zipCode() {
        return m_zipCode;
    }

    /**
     * @param m_zipCode the m_zipCode to set
     */
    public void setM_zipCode(BEZipcode m_zipCode) {
        this.m_zipCode = m_zipCode;
    }

    /**
     * @return the m_cpr
     */
    public String getM_cpr() {
        return m_cpr;
    }

    /**
     * @param m_cpr the m_cpr to set
     */
    public void setM_cpr(String m_cpr) {
        this.m_cpr = m_cpr;
    }
    
    public String toString(){
        return getM_lastName() + ", " + getM_firstName();
    }


}
