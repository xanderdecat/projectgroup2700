package DB;

import APPLICATION.NonProfitOrganisation;

import java.sql.*;
import java.util.ArrayList;

public class NonProfitOrganisationDAO {

    public static void createTables() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE nonprofitorganisations ("
                    + "nonPONnumber int NOT NULL, "
                    + "NPOName varchar(50) NOT NULL, "
                    + "description varchar(1000) NOT NULL, "
                    + "accountNumber varchar(50) NOT NULL, "
                    + "causeOfNPO ENUM('Education', 'AnimalRights', 'ClimateChange', 'HumanRights', 'Poverty', 'Research', 'Healthcare', 'Other') NOT NULL, "
                    + "PRIMARY KEY (nonPONnumber)" + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public NonProfitOrganisation getNPO(int nPONum)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT nonPONnumber, NPOName, description, accountNumber, causeofNPO "
                    + "FROM nonprofitorganisations "
                    + "WHERE nonPONumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1,nPONum);

            // let op de spatie na 'summary' en 'Students' in voorgaande SQL
            ResultSet srs = stmt.executeQuery();
            String NPOName, description, accountNumber;
            int nonPONumber;
            NonProfitOrganisation.cause causeOfNPO;

            if (srs.next()) {
                nonPONumber = srs.getInt("nonPONumber");
                NPOName = srs.getString("NPOName");

                description = srs.getString("description");
                accountNumber = srs.getString("accountNumber");
                causeOfNPO = (NonProfitOrganisation.cause.valueOf(srs.getString("summary")));
            } else {// we verwachten slechts 1 rij...
                return null;
            }
            NonProfitOrganisation nonProfitOrganisation = new NonProfitOrganisation(nonPONumber, NPOName, description, accountNumber, causeOfNPO);
            return nonProfitOrganisation;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }
    public void save(NonProfitOrganisation nonProfitOrganisation)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT nonPONumber "
                    + "FROM nonprofitorganisations "
                    + "WHERE nonPONumber = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1,nonProfitOrganisation.getNonPONumber());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE nonprofitorganisations " +
                        "SET NPOName = ? ," +
                        " description = ? , " +
                        " accountNumber = ?," +
                        " causeOfNPO = ? " +
                        "WHERE nonPONumber = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, nonProfitOrganisation.getNPOName());
                stmt2.setString(2, nonProfitOrganisation.getDescription());
                stmt2.setString(3, nonProfitOrganisation.getAccountNumber());
                stmt2.setString(4, String.valueOf(nonProfitOrganisation.getCauseOfNPO()));
                stmt2.setInt(5, nonProfitOrganisation.getNonPONumber());
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into nonprofitorganisations "
                        + "(nonPONumber, NPOName, description, accountNumber, causeOfNPO) "
                        + "VALUES (?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1, nonProfitOrganisation.getNonPONumber());
                insertStm.setString(2, nonProfitOrganisation.getNPOName());
                insertStm.setString(3, nonProfitOrganisation.getDescription());
                insertStm.setString(4, nonProfitOrganisation.getAccountNumber());
                insertStm.setString(5, String.valueOf(nonProfitOrganisation.getCauseOfNPO()));
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
    public ArrayList<NonProfitOrganisation> getNonProfitOrganisations()  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT nonPONumber "
                    + "FROM nonprofitorganisations";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<NonProfitOrganisation> nonProfitOrganisations = new ArrayList<NonProfitOrganisation>();
            while (srs.next())
                nonProfitOrganisations.add(getNPO(srs.getInt("nonPONumber")));
            return nonProfitOrganisations;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
    public void deleteNPO(NonProfitOrganisation nonProfitOrganisation)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql ="DELETE FROM nonprofitorganisations "
                    + "WHERE nonPONumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,nonProfitOrganisation.getNonPONumber());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }


}
