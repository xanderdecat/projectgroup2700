package DB;

import APPLICATION.Provider;
import APPLICATION.User;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

public class ProviderDAO extends UserDAO {

    // test
    // ok
    public static void createTables() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE providers ("
                    + "providerNumber int NOT NULL, "
                    + "userNumber int NOT NULL, "
                    + "VATNumber varchar(50) UNIQUE, "
                    + "accountNumber varchar(50) NOT NULL UNIQUE, "
                    + "streetName varchar(50) NOT NULL, "
                    + "houseNumber int NOT NULL, "
                    + "ZIP int NOT NULL, "
                    + "city varchar(50) NOT NULL, "
                    + "country varchar(50) NOT NULL, "
                    + "artistName varchar(50) NOT NULL, "
                    + "genre ENUM('Techno', 'Rock', 'Pop', 'Dance', 'Blues', 'Jazz', 'Soul', 'Party', 'Hiphop', 'Acoustic', 'Disco', 'Funk', 'Classic', 'Background', 'Nineties', 'Eighties', 'Seventies', 'Sixties', 'Latin', 'Lounge') NOT NULL, "
                    + "activityDate date NOT NULL, "
                    + "priceHour double NOT NULL, "
                    + "minHours double NOT NULL, "
                    + "maxHours double NOT NULL, "
                    + "conditions varchar(100), "
                    + "description varchar(100) NOT NULL, "
                    + "teaserSet varchar(50) NOT NULL, "
                    + "linkToPage varchar(50) UNIQUE, "
                    + "PRIMARY KEY (providerNumber)" + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Provider getProvider(int provNum) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT providerNumber, userNumber, VATNumber, accountNumber, streetName, houseNumber, ZIP, city, country, artistName, genre, activityDate, priceHour, minHours, maxHours, conditions, description, teaserSet, linkToPage "
                    + "FROM providers "
                    + "WHERE providerNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1, provNum);

            // let op de spatie na 'summary' en 'Students' in voorgaande SQL
            ResultSet srs = stmt.executeQuery();

            String VATNumber, accountNumber, streetName, city, country, artistName, conditions, description;
            int providerNumber, userNumber, houseNumber, ZIP;
            Provider.genres genre;
            Date activityDate;
            double priceHour, minHours, maxHours;
            URL teaserSet, linkToPage;

            if (srs.next()) {
                providerNumber = srs.getInt("providerNumber");
                userNumber = srs.getInt("userNumber");
                VATNumber = srs.getString("VATNumber");
                accountNumber = srs.getString("accountNumber");
                streetName = srs.getString("streetName");
                houseNumber = srs.getInt("houseNumber");
                ZIP = srs.getInt("ZIP");
                city = srs.getString("city");
                country = srs.getString("country");
                artistName = srs.getString("artistName");
                genre = Provider.genres.valueOf(srs.getString("genre"));
                activityDate = srs.getDate("activityDate");
                priceHour = srs.getDouble("priceHour");
                minHours = srs.getDouble("minHours");
                maxHours = srs.getDouble("maxHours");
                conditions = srs.getString("conditions");
                description = srs.getString("description");
                teaserSet = srs.getURL("teaserSet");
                linkToPage = srs.getURL("linkToPage");
            } else {// we verwachten slechts 1 rij...
                return null;
            }

            User user = null;
            Provider provider = null;
            for (User u : getUsers())
                if (u.getUserNumber() == userNumber)
                    user = getUser(userNumber);

            if (user != null)
                provider = new Provider(user.getUserNumber(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getAge(), user.getEmail(), user.getPhoneNumber(), providerNumber, VATNumber, accountNumber, streetName, houseNumber, ZIP, city, country, artistName, genre, activityDate, priceHour, minHours, maxHours, conditions, description, teaserSet, linkToPage);
            return provider;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void saveProvider(Provider p) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT providerNumber "
                    + "FROM providers "
                    + "WHERE providerNumber = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, p.getProviderNumber());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE providers " +
                        "SET userNumber = ? ," +
                        " VATNumber = ? ," +
                        " accountNumber = ? ," +
                        " streetName = ? ," +
                        " houseNumber = ? ," +
                        " ZIP = ? ," +
                        " city = ? ," +
                        " country = ? ," +
                        " artistName = ? ," +
                        " genre = ? ," +
                        " activityDate = ? ," +
                        " priceHour = ? ," +
                        " minHours = ? ," +
                        " maxHours = ? ," +
                        " conditions = ? ," +
                        " description = ? ," +
                        " teaserSet = ? ," +
                        " linkToPage = ? " +
                        "WHERE providerNumber = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setInt(1, p.getUserNumber());
                stmt2.setString(2, p.getVATNumber());
                stmt2.setString(3, p.getAccountNumber());
                stmt2.setString(4, p.getStreetName());
                stmt2.setInt(5, p.getHouseNumber());
                stmt2.setInt(6, p.getZIP());
                stmt2.setString(7, p.getCity());
                stmt2.setString(8, p.getCountry());
                stmt2.setString(9, p.getArtistName());
                stmt2.setString(10, String.valueOf(p.getGenre()));
                stmt2.setDate(11, (Date) p.getActivityDate());
                stmt2.setDouble(12, p.getPriceHour());
                stmt2.setDouble(13, p.getMinHours());
                stmt2.setDouble(14, p.getMaxHours());
                stmt2.setString(15, p.getConditions());
                stmt2.setString(16, p.getDescription());
                stmt2.setURL(17, p.getTeaserSet());
                stmt2.setURL(18, p.getLinkToPage());
                stmt2.setInt(19, p.getProviderNumber());
                stmt2.executeUpdate();
            } else {

                // INSERT
                String sqlInsert = "INSERT into providers "
                        + "(providerNumber, userNumber, VATNumber, accountNumber, streetName, houseNumber, ZIP, city, country, artistName, genre, activityDate, priceHour, minHours, maxHours, conditions, description, teaserSet, linkToPage) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1, p.getProviderNumber());
                insertStm.setInt(2, p.getUserNumber());
                insertStm.setString(3, p.getVATNumber());
                insertStm.setString(4, p.getAccountNumber());
                insertStm.setString(5, p.getStreetName());
                insertStm.setInt(6, p.getHouseNumber());
                insertStm.setInt(7, p.getZIP());
                insertStm.setString(8, p.getCity());
                insertStm.setString(9, p.getCountry());
                insertStm.setString(10, p.getArtistName());
                insertStm.setString(11, String.valueOf(p.getGenre()));
                insertStm.setDate(12, (Date) p.getActivityDate());
                insertStm.setDouble(13, p.getPriceHour());
                insertStm.setDouble(14, p.getMinHours());
                insertStm.setDouble(15, p.getMaxHours());
                insertStm.setString(16, p.getConditions());
                insertStm.setString(17, p.getDescription());
                insertStm.setURL(18, p.getTeaserSet());
                insertStm.setURL(19, p.getLinkToPage());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public ArrayList<Provider> getProviders() {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT providerNumber "
                    + "FROM providers";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Provider> providers = new ArrayList<Provider>();
            while (srs.next())
                providers.add(getProvider(srs.getInt("providerNumber")));
            return providers;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public void deteleProvider(Provider p) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM providers "
                    + "WHERE providerNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getProviderNumber());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    // enkel providers met een specifiek gekozen genre
    public ArrayList<Provider> getProvidersWtihGenre(Provider.genres gnr) throws DBException {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT number "
                    + "FROM Students "
                    + "WHERE genre=" + gnr.toString();
            ResultSet srs = stmt.executeQuery(sql);

            ArrayList<Provider> providers = new ArrayList<Provider>();
            while (srs.next())
                providers.add(getProvider(srs.getInt("providerNumber")));
            return providers;
        } catch (DBException dbe) {
            dbe.printStackTrace();
            throw dbe;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DBException(ex);
        }

    }

}
