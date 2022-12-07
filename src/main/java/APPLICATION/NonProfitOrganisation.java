package APPLICATION;

public class NonProfitOrganisation {


    // enum voor goede doelen
    public enum cause {Education, AnimalRights, ClimateChange, HumanRights, Poverty, Research, Healthcare, Other}      // nog aan te vullen

    // instantievariabelen
    private static long helpNonPONumber = 0;
    private int nonPONumber;
    private String NPOName;
    private String description;
    private String accountNumber;
    private cause causeOfNPO;

    // constructor for NonProfitOrganisationDAO
    public NonProfitOrganisation(int nonPONumber, String NPOName, String description, String accountNumber, cause causeOfNPO) {
        this.nonPONumber = nonPONumber;
        this.NPOName = NPOName;
        this.description = description;
        this.accountNumber = accountNumber;
        this.causeOfNPO = causeOfNPO;
    }

    // getters en setters
    public static long getHelpNonPONumber() {
        return helpNonPONumber;
    }

    public static void setHelpNonPONumber(long helpNonPONumber) {
        NonProfitOrganisation.helpNonPONumber = helpNonPONumber;
    }

    public int getNonPONumber() {
        return nonPONumber;
    }

    public void setNonPONumber(int nonPONumber) {
        this.nonPONumber = nonPONumber;
    }

    public String getNPOName() {
        return NPOName;
    }

    public void setNPOName(String NPOName) {
        this.NPOName = NPOName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public cause getCauseOfNPO() {
        return causeOfNPO;
    }

    public void setCauseOfNPO(cause causeOfNPO) {
        this.causeOfNPO = causeOfNPO;
    }
}
