package APPLICATION;

import java.util.Date;

public class Transaction {

    // instantievariabelen
    private static long helpTransactionNumber = 0;
    private int transactionNumber;
    // private Provider.Event event;
    private Provider beneficiaryPerson; // hieruit accountnumber halen
    private User indeptedPerson;
    private String statement;
    private long amountNPO;
    private long amountPlatform;
    private long amountDiscount;
    private long amountProvider;
    private long amountTotal;
    private long amountToPay;
    private long amountPayed;

    private int eventNumber;

    private Date confirmationDate;
    private int accountNumber;

    // constructor


    public Transaction(int transactionNumber, Provider beneficiaryPerson, User indeptedPerson, Date confirmationDate, String statement, long amountNPO, long amountDiscount, long amountProvider, long amountToPay, long amountPayed, int eventNumber, int accountNumber) {
        this.transactionNumber = transactionNumber;
        this.beneficiaryPerson = beneficiaryPerson;
        this.confirmationDate = confirmationDate;
        this.indeptedPerson = indeptedPerson;
        this.statement = statement;
        this.amountNPO = amountNPO;
        this.amountDiscount = amountDiscount;
        this.amountProvider = amountProvider;
        this.amountToPay = amountToPay;
        this.amountPayed = amountPayed;
        this.eventNumber = eventNumber;
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public static long getHelpTransactionNumber() {
        return helpTransactionNumber;
    }

    public static void setHelpTransactionNumber(long helpTransactionNumber) {
        Transaction.helpTransactionNumber = helpTransactionNumber;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public Provider getBeneficiaryPerson() {
        return beneficiaryPerson;
    }

    public void setBeneficiaryPerson(Provider beneficiaryPerson) {
        this.beneficiaryPerson = beneficiaryPerson;
    }

    public User getIndeptedPerson() {
        return indeptedPerson;
    }

    public void setIndeptedPerson(User indeptedPerson) {
        this.indeptedPerson = indeptedPerson;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public long getAmountNPO() {
        return amountNPO;
    }

    public void setAmountNPO(long amountNPO) {
        this.amountNPO = amountNPO;
    }

    public long getAmountPlatform() {
        return amountPlatform;
    }

    public void setAmountPlatform(long amountPlatform) {
        this.amountPlatform = amountPlatform;
    }

    public long getAmountDiscount() {
        return amountDiscount;
    }

    public void setAmountDiscount(long amountDiscount) {
        this.amountDiscount = amountDiscount;
    }

    public long getAmountProvider() {
        return amountProvider;
    }

    public void setAmountProvider(long amountProvider) {
        this.amountProvider = amountProvider;
    }

    public long getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(long amountTotal) {
        this.amountTotal = amountTotal;
    }

    public long getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(long amountToPay) {
        this.amountToPay = amountToPay;
    }

    public long getAmountPayed() {
        return amountPayed;
    }

    public void setAmountPayed(long amountPayed) {
        this.amountPayed = amountPayed;
    }

    public int getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(int eventNumber) {
        this.eventNumber = eventNumber;
    }
}
