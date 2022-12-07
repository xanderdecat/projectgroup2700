package DB;

import APPLICATION.Review;

import java.sql.*;
import java.util.ArrayList;

public class ReviewDAO {
    public static void createTables() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE reviews ("
                    + "ReviewNumber int NOT NULL, "
                    + "subject varchar(100) NOT NULL, "
                    + "ScoreOn10 int(10) NOT NULL, "
                    + "description varchar(100) NOT NULL, "
                    + "dateOfReview DATETIME NOT NULL, "
                    + "eventNumber int NOT NULL, "
                    + "PRIMARY KEY (ReviewNumber)" + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Review getReview(int reviewNum)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT ReviewNumber, subject, ScoreOn10, description, dateOfReview, EventNumber "
                    + "FROM reviews "
                    + "WHERE ReviewNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1,reviewNum);

            // let op de spatie na 'summary' en 'Students' in voorgaande SQL
            ResultSet srs = stmt.executeQuery();
            String subject, description;
            int ReviewNumber, scoreOn10, EventNumber;
            Date dateOfReview;

            if (srs.next()) {
                ReviewNumber = srs.getInt("ReviewNumber");
                subject = srs.getString("subject");
                scoreOn10 = srs.getInt("scoreOn10");
                description = srs.getString("description");
                dateOfReview = srs.getDate("dateOfReview");
                EventNumber = srs.getInt("EventNumber");
            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Review review = new Review(ReviewNumber, subject, scoreOn10, description, dateOfReview, EventNumber);
            return review;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }
    public void save(Review review)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT ReviewNumber "
                    + "FROM reviews "
                    + "WHERE ReviewNumber = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1,review.getReviewNumber());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE reviews " +
                        "SET " +
                        " subject = ? , " +
                        "scoreOn10 = ?, " +
                        "description = ?, " +
                        "dateOfReview = ?, " +
                        "EventNumber = ? " +
                        "WHERE ReviewNumber = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, review.getSubject());
                stmt2.setInt(2, review.getScoreOn10());
                stmt2.setString(3, review.getDescription());
                stmt2.setDate(4, (Date) review.getDateOfReviews());
                stmt2.setInt(5, review.getEventNumber());
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into reviews "
                        + "(ReviewNumber, subject, scoreOn10, description, dateOfReview, EventNumber) "
                        + "VALUES (?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1, review.getReviewNumber());
                insertStm.setString(2,review.getSubject());
                insertStm.setInt(3,review.getScoreOn10());
                insertStm.setString(4, review.getDescription());
                insertStm.setDate(5,(Date) review.getDateOfReviews());
                insertStm.setInt(6,review.getEventNumber());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
    public ArrayList<Review> getReviews()  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT ReviewNumber "
                    + "FROM reviews";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Review> reviews = new ArrayList<Review>();
            while (srs.next())
                reviews.add(getReview(srs.getInt("ReviewNumber")));
            return reviews;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }
    public void deleteReview(Review review)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql ="DELETE FROM reviews "
                    + "WHERE reviewNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,review.getReviewNumber());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
