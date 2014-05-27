/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.BEAlarm;
import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentType;
import BE.BEMaterial;
import BE.BEUsage;
import BE.BESalary;
import BE.BEZipcode;
import DAL.Interfaces.IDALRead;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author Morten
 */
public class DALRead implements IDALRead {

    Connection m_connection;
    private static DALRead m_instance;
    ArrayList<BEIncidentType> resIncidentType;
    ArrayList<BEAlarm> resAlarms;

    private DALRead() {
        m_connection = DB_Connection.getInstance().getConnection();
    }
/**
 * 
 * @return current instance of DALRead
 */
    public static DALRead getInstance() {
        if (m_instance == null) {
            m_instance = new DALRead();
        }
        return m_instance;
    }
/**
 * Read all Salary info from DB where incident is done
 * @param from
 * @param to
 * @return Arraylist of salaries
 * @throws SQLException 
 */
    @Override
    public ArrayList<BESalary> readInfo(String from, String to) throws SQLException {
        ArrayList<BESalary> res = new ArrayList<>();
        String sql = "Select Fireman.lastName, Fireman.firstName,Incident.incidentName, Incident.[date], IncidentType.incidentTypeDescription, [Role].roleDescription,[Role/Time].[hours] \n"
                + "From [Role/Time] \n"
                + "inner join Fireman on Fireman.id = [Role/Time].firemanId \n"
                + "inner join Incident on [Role/Time].incidentId = Incident.id \n"
                + "inner join IncidentType on Incident.incidentTypeId = IncidentType.id\n"
                + "inner join [Role] on [Role/Time].roleId = [Role].id\n"
                + "where Incident.date >= ? and Incident.date <= ? and Incident.isDone = 1\n"
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
            String incidentdescription = result.getString("incidentTypeDescription");
            String roledescription = result.getString("roleDescription");
            int hours = result.getInt("hours");


            BESalary be = new BESalary(lastname, firstname, incidentname, date, incidentdescription, roledescription, hours);
            res.add(be);
        }
        return res;
    }
/**
 * Read all firemen from DB
 * @return Arraylist of firemen
 * @throws SQLException 
 */
    @Override
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
            //String cpr = result.getString("cpr");
            BEFireman be = new BEFireman(id, firstName, lastName, address, refZipCode, phone, paymentNumber, isTeamLeader, photoPath);
            res.add(be);
        }
        return res;
    }
/**
 * Read all zipcodes from DB
 * @return Arraylist if zipcodes
 * @throws SQLException 
 */
    @Override
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
/**
 * Read all incident types from DB
 * @return Arraylists of incidentTypes
 * @throws SQLException 
 */
    @Override
    public ArrayList<BEIncidentType> readIncidentTypes() throws SQLException {
        if (resIncidentType == null) {
            resIncidentType = new ArrayList<>();
            Statement stm = m_connection.createStatement();
            stm.execute("select * from IncidentType");
            ResultSet result = stm.getResultSet();
            while (result.next()) {
                int id = result.getInt("id");
                String description = result.getString("incidentTypeDescription");
                BEIncidentType be = new BEIncidentType(id, description);
                resIncidentType.add(be);
            }
        }
        return resIncidentType;
    }
/**
 * Read all alarms from DB
 * @return Arraylist of alarms
 * @throws SQLException 
 */
    @Override
    public ArrayList<BEAlarm> readAlarms() throws SQLException {
        if (resAlarms == null) {
            resAlarms = new ArrayList<>();
            Statement stm = m_connection.createStatement();
            stm.execute("select * from Alarm");
            ResultSet result = stm.getResultSet();
            while (result.next()) {
                int id = result.getInt("id");
                String description = result.getString("alarmDescription");
                BEAlarm be = new BEAlarm(id, description);
                resAlarms.add(be);
            }
        }
        return resAlarms;
    }
/**
 * Read incidents from DB that is done
 * @return Arraylist of incidents
 * @throws SQLException 
 */
    @Override
    public ArrayList<BEIncident> readIncidents() throws SQLException {
        ArrayList<BEIncident> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("select * from Incident where Incident.isDone = 1");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            String incidentName = result.getString("incidentName");
            Date date = result.getDate("date");
            Time time = result.getTime("startTime");
            int incidentTypeId = result.getInt("incidentTypeId");
            BEIncidentType refIncidentType = null;
            for (BEIncidentType beincidenttype : readIncidentTypes()) {
                if (beincidenttype.getM_id() == incidentTypeId) {
                    refIncidentType = beincidenttype;
                }
            }
            boolean isDone = result.getBoolean("isDone");
            BEIncident be = new BEIncident(id, incidentName, date, time, refIncidentType, isDone);
            res.add(be);
        }
        return res;
    }
/**
 * Read all incidents Detaisl where incident is done
 * @return Arraylist of incidentDetails
 * @throws SQLException 
 */
    @Override
    public ArrayList<BEIncidentDetails> readIncidentDetails() throws SQLException {
        ArrayList<BEIncidentDetails> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("Select IncidentDetails.incidentLeader, "
                + "IncidentDetails.evaNumber, "
                + "IncidentDetails.fireReport, "
                + "IncidentDetails.incidentid, "
                + "IncidentDetails.involvedName, "
                + "IncidentDetails.involvedAddress, "
                + "IncidentDetails.remark, "
                + "IncidentDetails.alarmId,"
                + "IncidentDetails.detectorNumber,"
                + "IncidentDetails.groupNumber "
                + "from IncidentDetails inner join Incident "
                + "on IncidentDetails.incidentId = incident.id "
                + "where Incident.isDone = 1 ");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            String incidentLeader = result.getString("incidentLeader");
            String evaNumber = result.getString("evaNumber");
            String fireReport = result.getString("fireReport");
            int incidentId = result.getInt("incidentId");
            BEIncident refIncident = null;
            for (BEIncident be : readIncidents()) {
                if (be.getM_id() == incidentId) {
                    refIncident = be;
                    break;
                }
            }
            String involvedName = result.getString("involvedName");
            String involvedAddress = result.getString("involvedAddress");
            String remark = result.getString("remark");
            int alarmId = result.getInt("alarmId");
            BEAlarm refAlarm = null;
            for (BEAlarm be : readAlarms()) {
                if (be.getM_id() == alarmId) {
                    refAlarm = be;
                    break;
                }
            }
            String detectorNumber = result.getString("detectorNumber");
            String groupNumber = result.getString("groupNumber");

            BEIncidentDetails be = new BEIncidentDetails(incidentLeader,
                    evaNumber,
                    fireReport,
                    
                    refIncident,
                    involvedName,
                    involvedAddress,
                    remark,
                    refAlarm,
                    detectorNumber,
                    groupNumber);

            res.add(be);
        }

        return res;

    }
/**
 * Read all usages from DB
 * @return Arraylist of usages
 * @throws SQLException 
 */
    @Override
    public ArrayList<BEUsage> readUsage() throws SQLException {
        ArrayList<BEUsage> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("select * from Usage "
                + "inner join Incident "
                + "on Usage.incidentId = Incident.id ");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            int materialId = result.getInt("materialId");
            BEMaterial refMaterial = null;
            for (BEMaterial be : readMaterial()) {
                if (be.getM_id() == materialId) {
                    refMaterial = be;
                }
            }
            int amount = result.getInt("amount");
            int incidentId = result.getInt("incidentId");
            BEIncident refIncident = null;
            for (BEIncident be : readIncidents()) {
                if (be.getM_id() == incidentId) {
                    refIncident = be;
                }
            }
            BEUsage be = new BEUsage(id, refMaterial, amount, refIncident);
            res.add(be);
        }

        return res;

    }

    /**
     * Read all material from DB
     *
     * @return ArrayList of Material
     * @throws SQLException
     */
    @Override
    public ArrayList<BEMaterial> readMaterial() throws SQLException {
        ArrayList<BEMaterial> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("select * from Material order by materialDescription");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            String description = result.getString("materialDescription");
            BEMaterial be = new BEMaterial(id, description);
            res.add(be);
        }
        return res;

    }
}
