package APPLICATION;

import java.util.Date;

public class Review {

    // instantievariabelen
    private static long helpReviewNumber = 0;
    private int reviewNumber;
    private int eventNumber;
    private boolean providerReview;         // is het een provider review?
    private int userNumber;
    private int providerNumber;
    private String subject;
    private int scoreOn10;
    private String description;
    private Date dateOfReviews;

    // constructor

    public Review(int reviewNumber, int eventNumber, boolean providerReview, int userNumber, int providerNumber, String subject, int scoreOn10, String description, Date dateOfReviews) {
        this.reviewNumber = reviewNumber;
        this.eventNumber = eventNumber;
        this.providerReview = providerReview;
        this.userNumber = userNumber;
        this.providerNumber = providerNumber;
        this.subject = subject;
        this.scoreOn10 = scoreOn10;
        this.description = description;
        this.dateOfReviews = dateOfReviews;
    }

    public Review(int reviewNumber,String subject, int scoreOn10, String description, Date dateOfReviews, int eventNumber) {
        this.reviewNumber = reviewNumber;
        this.subject = subject;
        this.eventNumber = eventNumber;
        this.scoreOn10 = scoreOn10;
        this.description = description;
        this.dateOfReviews = dateOfReviews;
    }
    //getters en setters

    public static long getHelpReviewNumber() {
        return helpReviewNumber;
    }

    public static void setHelpReviewNumber(int helpReviewNumber) {
        Review.helpReviewNumber = helpReviewNumber;
    }

    public int getReviewNumber() {
        return reviewNumber;
    }

    public void setReviewNumber(int reviewNumber) {
        this.reviewNumber = reviewNumber;
    }

    public int getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(int eventNumber) {
        this.eventNumber = eventNumber;
    }

    public boolean isProviderReview() {
        return providerReview;
    }

    public void setProviderReview(boolean providerReview) {
        this.providerReview = providerReview;
    }

    public long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public long getProviderNumber() {
        return providerNumber;
    }

    public void setProviderNumber(int providerNumber) {
        this.providerNumber = providerNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getScoreOn10() {
        return scoreOn10;
    }

    public void setScoreOn10(int scoreOn10) {
        this.scoreOn10 = scoreOn10;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfReviews() {
        return dateOfReviews;
    }

    public void setDateOfReviews(Date dateOfReviews) {
        this.dateOfReviews = dateOfReviews;
    }
}
