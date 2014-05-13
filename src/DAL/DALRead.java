/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.BEFireman;
import BE.BESalary;
import BE.BEZipcode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Morten
 */
public class DALRead {

    Connection m_connection;
    private static DALRead m_instance;

    private DALRead() {
        m_connection = DB_Connection.getInstance().getConnection();
    }

    public static DALRead getInstance() {
        if (m_instance == null) {
            m_instance = new DALRead();
        }
        return m_instance;
    }

    public ArrayList<BESalary> readInfo(String from, String to) throws SQLException {
        ArrayList<BESalary> res = new ArrayList<>();
        String sql = "Select Fireman.lastName, Fireman.firstName,Incident.incidentName, Incident.[date], IncidentType.[description] as 'incidentDescription', [Role].[description] as 'roleDescription', Salary.id \n"
                + "From [Role/Time] \n"
                + "inner join Fireman on Fireman.id = [Role/Time].firemanId \n"
                + "inner join Incident on [Role/Time].incidentId = Incident.id \n"
                + "inner join IncidentType on Incident.incidentTypeId = IncidentType.id\n"
                + "inner join [Role] on [Role/Time].roleId = [Role].id\n"
                + "inner join Salary on [Role/Time].salaryId = Salary.id\n"
                + "where Incident.date >= ? and Incident.date <= ?\n"
                + "order by lastname, firstname, date";

        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, from);
        ps.setString(2, to);
        ps.execute();
        ResultSet result = ps.getResultSet();
        while (result.next()) {
            String lastname = result.getString("lastName");
            String firstname = result.getString("firstName");
            String incidentname = result.getString("incidentName");
            Date date = result.getDate("date");
            String incidentdescription = result.getString("incidentDescription");
            String roledescription = result.getString("roleDescription");
            int salaryid = result.getInt("id");

            BESalary be = new BESalary(lastname, firstname, incidentname, date, incidentdescription, roledescription, salaryid);
            res.add(be);
        }
        return res;
    }
    
    public ArrayList<BEFireman> readFiremen() throws SQLException {
        ArrayList<BEFireman> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("select * from Fireman order by lastName, firstname");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String address = result.getString("address");
            int zip = result.getInt("zipCode");
            BEZipcode refZipCode = null;
            for (BEZipcode bezipcode : readZipcodes()) {
                if (bezipcode.getM_zipCode() == zip) {
                    refZipCode = bezipcode;
                }
            }
            int phone = result.getInt("phone");
            int paymentNumber = result.getInt("paymentNumber");
            boolean isTeamLeader = result.getBoolean("isTeamLeader");
            String photoPath = result.getString("photoPath");
            String cpr = result.getString("cpr");
            BEFireman be = new BEFireman(id, firstName, lastName, address, refZipCode, phone, paymentNumber, isTeamLeader, photoPath, cpr);
            res.add(be);
        }
        return res;
    }
    
    public ArrayList<BEZipcode> readZipcodes() throws SQLException {
        ArrayList<BEZipcode> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("select * from [zip/city]");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            int zipcode = result.getInt("zipcode");
            String city = result.getString("city");
            BEZipcode be = new BEZipcode(zipcode, city);
            res.add(be);
        }
        return res;
    }
}
