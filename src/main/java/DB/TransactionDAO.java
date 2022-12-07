package DB;

import APPLICATION.Provider;
import APPLICATION.Transaction;
import APPLICATION.User;

import java.sql.*;
import java.util.ArrayList;

public class TransactionDAO { //klasse om te interageren met de database user table

    public static void createTables() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE Transactions ("
                    + "TransactionNumber int NOT NULL, "
                    + "beneficiaryPersonProviderNumber INT NOT NULL, "
                    + "indebtedPersonUserNumber INT NOT NULL, "
                    + "accountNumber int NOT NULL, "
                    + "confirmationDate DATETIME NOT NULL, "
                    + "statement varchar(50) NOT NULL, "
                    + "amountToPay DECIMAL(2,0) NOT NULL, "
                    + "amountPayed DECIMAL(2,0) NOT NULL, "
                    + "amountProvider DECIMAL(2,0) NOT NULL, "
                    + "amountNPO DECIMAL(2,0) NOT NULL, "
                    + "amountDiscount DECIMAL(2,0) NOT NULL, "
                    + "EventNumber int NOT NULL, "
                    + "PRIMARY KEY (TransactionNumber)" + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transaction getTransaction(int transactionNum) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT TransactionNumber, beneficiaryPersonProviderNumber, indebtedPersonUserNumber, accountNumber, confirmationDate, statement, amountToPay, amountPayed, amountProvider, amountNPO, amountDiscount, EventNumber "
                    + "FROM Transactions "
                    + "WHERE TransactionNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1, transactionNum);

            // let op de spatie na 'summary' en 'Students' in voorgaande SQL
            ResultSet srs = stmt.executeQuery();
            String statement;
            int TransactionNumber, accountNumber, EventNumber;
            Date confirmationDate;
            long amountToPay, amountPayed, amountProvider, amountNPO, amountDiscount;
            Provider beneficiaryPerson;
            User indebtedPerson;
            if (srs.next()) {
                TransactionNumber = srs.getInt("TransactionNumber");
                beneficiaryPerson = ProviderDAO.getProvider(srs.getInt("beneficiaryPerson"));
                indebtedPerson = (User) UserDAO.getUser(srs.getInt("indebtedPerson"));
                confirmationDate = srs.getDate("confirmationDate");
                accountNumber = srs.getInt("accountNumber");
                statement = srs.getString("statement");
                amountToPay = srs.getLong("amountToPay");
                amountPayed = srs.getLong("amountPayed");
                amountProvider = srs.getLong("amountProvider");
                amountNPO = srs.getLong("amountNPO");
                amountDiscount = srs.getLong("amountDiscount");
                EventNumber = srs.getInt("EventNumber");
            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Transaction transaction = new Transaction(TransactionNumber, beneficiaryPerson, indebtedPerson, confirmationDate, statement, amountNPO, amountDiscount, amountProvider, amountToPay, amountPayed, EventNumber, accountNumber);
            return transaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void saveTransaction(Transaction transaction) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT TransactionNumber "
                    + "FROM Transactions "
                    + "WHERE TransactionNumber = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, transaction.getTransactionNumber());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE Transactions " +
                        "SET beneficiaryPerson = ? , " +
                        " indebtedPerson = ? , " +
                        " confirmationDate = ? , " +
                        " amountToPay = ? , " +
                        " amountPayed = ? , " +
                        " amountProvider = ? , " +
                        " amountNPO = ? , " +
                        " amountDiscount = ? , " +
                        " statement = ? , " +
                        " accountNumber = ? , " +
                        " EventNumber = ? " +
                        "WHERE TransactionNumber = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);

                stmt2.setInt(1, transaction.getBeneficiaryPerson().getProviderNumber());
                stmt2.setInt(2, transaction.getIndeptedPerson().getUserNumber());
                stmt2.setInt(3, transaction.getAccountNumber());
                stmt2.setDate(4, (Date) transaction.getConfirmationDate());
                stmt2.setString(5,transaction.getStatement());
                stmt2.setLong(6, transaction.getAmountToPay());
                stmt2.setLong(7, transaction.getAmountPayed());
                stmt2.setLong(8, transaction.getAmountProvider());
                stmt2.setLong(9, transaction.getAmountNPO());
                stmt2.setLong(10, transaction.getAmountDiscount());
                stmt2.setInt(11,transaction.getTransactionNumber());


                stmt2.executeUpdate();
            } else {

                // INSERT
                String sqlInsert = "INSERT into Transactions "
                        + "(TransactionNumber, beneficiaryPerson, indebtedPerson, confirmationDate, statement, amountNPO, amountDiscount, amountProvider, amountToPay, amountPayed, EventNumber) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);

                insertStm.setInt(1,transaction.getTransactionNumber());
                insertStm.setInt(2, transaction.getBeneficiaryPerson().getProviderNumber());
                insertStm.setInt(3, transaction.getIndeptedPerson().getUserNumber());
                insertStm.setInt(4, transaction.getAccountNumber());
                insertStm.setDate(5, (Date) transaction.getConfirmationDate());
                insertStm.setString(6,transaction.getStatement());
                insertStm.setLong(7, transaction.getAmountToPay());
                insertStm.setLong(8, transaction.getAmountPayed());
                insertStm.setLong(9, transaction.getAmountProvider());
                insertStm.setLong(10, transaction.getAmountNPO());
                insertStm.setLong(11, transaction.getAmountDiscount());
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public ArrayList<Transaction> getTransacions() {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT TransactionNumber "
                    + "FROM Transactions";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Transaction> transactions = new ArrayList<Transaction>();
            while (srs.next())
                transactions.add(getTransaction(srs.getInt("TransactionNumber")));
            return transactions;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public void deleteTransaction(Transaction transaction) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM Transactions "
                    + "WHERE TransactionNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, transaction.getTransactionNumber());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}
