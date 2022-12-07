package DB;

import APPLICATION.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO { //klasse om te interageren met de database user table

    public static void createTables() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE users ("
                    + "userNumber int NOT NULL, "
                    + "firstName varchar(50) NOT NULL, "
                    + "lastName varchar(50) NOT NULL, "
                    + "dateOfBirth date NOT NULL, "
                    + "age int NOT NULL, "
                    + "email varchar(50) NOT NULL, "
                    + "phoneNumber varchar(50) NOT NULL, "
                    + "PRIMARY KEY (userNumber)" + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User getUser(int userNum) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT userNumber, firstName, lastName, dateOfBirth, age, email, phoneNumber "
                    + "FROM users "
                    + "WHERE userNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1, userNum);

            // let op de spatie na 'summary' en 'Students' in voorgaande SQL
            ResultSet srs = stmt.executeQuery();

            String firstName, lastName, email, phoneNumber;
            int userNumber, age;
            Date dateOfBirth;

            if (srs.next()) {
                userNumber = srs.getInt("userNumber");
                firstName = srs.getString("firstName");
                lastName = srs.getString("lastName");
                dateOfBirth = srs.getDate("dateOfbirth");
                age = srs.getInt("age");
                email = srs.getString("email");
                phoneNumber = srs.getString("phoneNumber");
            } else {// we verwachten slechts 1 rij...
                return null;
            }
            User user = new User(userNumber, firstName, lastName, dateOfBirth, age, email, phoneNumber);
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void saveUser(User u) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT userNumber "
                    + "FROM users "
                    + "WHERE userNumber = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, u.getUserNumber());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE users " +
                        "SET firstName = ? ," +
                        " lastName = ? ," +
                        " dateOfbirth = ?, " +
                        " age = ?, " +
                        " email = ?, " +
                        " phoneNumber = ? " +
                        "WHERE userNumber = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, u.getFirstName());
                stmt2.setString(2, u.getLastName());
                stmt2.setDate(3, (Date) u.getDateOfBirth());
                stmt2.setInt(4, u.getAge());
                stmt2.setString(5, u.getEmail());
                stmt2.setString(6, u.getPhoneNumber());
                stmt2.setInt(7, u.getUserNumber());
                stmt2.executeUpdate();
            } else {

                // INSERT
                String sqlInsert = "INSERT into users "
                        + "(userNumber, firstName, lastName, dateOfBirth, age, email, phoneNumber) "
                        + "VALUES (?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1, u.getUserNumber());
                insertStm.setString(2, u.getFirstName());
                insertStm.setString(3, u.getLastName());
                insertStm.setDate(4, (Date) u.getDateOfBirth());
                insertStm.setInt(5, u.getAge());
                insertStm.setString(6, u.getEmail());
                insertStm.setString(7, u.getPhoneNumber());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public static ArrayList<User> getUsers() {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT userNumber "
                    + "FROM users";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<User> users = new ArrayList<User>();
            while (srs.next())
                users.add(getUser(srs.getInt("userNumber")));
            return users;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public void deteUser(User u) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM users "
                    + "WHERE userNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, u.getUserNumber());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}