package DB;

import APPLICATION.Event;

import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventDAO {

    public static void createTables() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE events ("
                    + "eventNumber int NOT NULL, "
                    + "eventUserNumber int NOT NULL, "
                    + "eventName varchar(50) NOT NULL, "
                    + "streetName varchar(50), "
                    + "houseNumber int, "
                    + "ZIP int, "
                    + "city varchar(50), "
                    + "country varchar(50), "
                    + "startDate datetime NOT NULL, "
                    + "confirmationDate datetime NOT NULL, "
                    + "endDate datetime NOT NULL, "
                    + "eventDuration double NOT NULL, "
                    + "description varchar(100) NOT NULL, "
                    + "linkToPage varchar(50), "
                    + "PRIMARY KEY (eventNumber)" + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Event getEvent(int eventNum) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT eventNumber, eventUserNumber, eventName, streetName, houseNumber, ZIP, city, country, startDate, confirmationDate, endDate, eventDuration, description, linkToPage "
                    + "FROM events "
                    + "WHERE eventNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1, eventNum);

            // let op de spatie na 'summary' en 'Students' in voorgaande SQL
            ResultSet srs = stmt.executeQuery();

            String eventName, streetName, city, country, description;
            int eventNumber, eventUserNumber, houseNumber, ZIP;
            LocalDateTime startDate, confirmationDate, endDate;
            double eventDuration;
            URL linkToPage;

            if (srs.next()) {
                eventNumber = srs.getInt("eventNumber");
                eventUserNumber = srs.getInt("eventUserNumber");
                eventName = srs.getString("eventName");
                streetName = srs.getString("streetName");
                houseNumber = srs.getInt("houseNumber");
                ZIP = srs.getInt("ZIP");
                city = srs.getString("city");
                country = srs.getString("country");
                startDate = srs.getTimestamp("startDate").toLocalDateTime();
                confirmationDate = srs.getTimestamp("confirmationDate").toLocalDateTime();
                endDate = srs.getTimestamp("endDate").toLocalDateTime();
                eventDuration = srs.getDouble("eventDuration");
                description = srs.getString("description");
                linkToPage = srs.getURL("linkToPage");

            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Event event = new Event(eventNumber, eventUserNumber, eventName, streetName, houseNumber, ZIP, city, country, startDate, confirmationDate, endDate, eventDuration, description, linkToPage);
            return event;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void saveEvent(Event e) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT eventNumber "
                    + "FROM events "
                    + "WHERE eventNumber = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, e.getEventNumber());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE events " +
                        "SET eventUserNumber = ? ," +
                        " eventName = ? ," +
                        " streetName = ?, " +
                        " houseNumber = ?, " +
                        " ZIP = ?, " +
                        " city = ?, " +
                        " country = ?, " +
                        " startDate = ?, " +
                        " confirmationDate = ?, " +
                        " endDate = ?, " +
                        " eventDuration = ?, " +
                        " description = ?, " +
                        " linkToPage = ? " +
                        "WHERE eventNumber = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setInt(1, e.getEventUserNumber());
                stmt2.setString(2, e.getEventName());
                stmt2.setString(3, e.getStreetName());
                stmt2.setInt(4, e.getHouseNumber());
                stmt2.setInt(5, e.getZIP());
                stmt2.setString(6, e.getCity());
                stmt2.setString(7, e.getCountry());
                stmt2.setTimestamp(8, Timestamp.valueOf(e.getStartDate()));
                stmt2.setTimestamp(9, Timestamp.valueOf(e.getConfirmationDate()));
                stmt2.setTimestamp(10, Timestamp.valueOf(e.getEndDate()));
                stmt2.setDouble(11, e.getEventDuration());
                stmt2.setString(12, e.getDescription());
                stmt2.setURL(13, e.getLinkToPage());
                stmt2.setInt(14, e.getEventNumber());
                stmt2.executeUpdate();
            } else {

                // INSERT
                String sqlInsert = "INSERT into events "
                        + "(eventNumber, eventUserNumber, eventName, streetName, houseNumber, ZIP, city, country, startDate, confirmationDate, endDate, eventDuration, description, linkToPage) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1, e.getEventNumber());
                insertStm.setInt(2, e.getEventUserNumber());
                insertStm.setString(3, e.getEventName());
                insertStm.setString(4, e.getStreetName());
                insertStm.setInt(5, e.getHouseNumber());
                insertStm.setInt(6, e.getZIP());
                insertStm.setString(7, e.getCity());
                insertStm.setString(8, e.getCountry());
                insertStm.setTimestamp(9, Timestamp.valueOf(e.getStartDate()));
                insertStm.setTimestamp(10, Timestamp.valueOf(e.getConfirmationDate()));
                insertStm.setTimestamp(11, Timestamp.valueOf(e.getEndDate()));
                insertStm.setDouble(12, e.getEventDuration());
                insertStm.setString(13, e.getDescription());
                insertStm.setURL(14, e.getLinkToPage());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static ArrayList<Event> getEvents() {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT eventNumber "
                    + "FROM events";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Event> events = new ArrayList<Event>();
            while (srs.next())
                events.add(getEvent(srs.getInt("eventNumber")));
            return events;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public void deleteEvent(Event e) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM events "
                    + "WHERE eventNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, e.getEventNumber());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
