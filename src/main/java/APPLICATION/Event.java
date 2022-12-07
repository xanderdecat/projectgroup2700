package APPLICATION;

import java.net.URL;
import java.time.LocalDateTime;

public class Event {
    private enum stage {created, acceptedByProvider, transactionCompleted, last48hoursBeforeStart, ended}

    // instantievariabelen
    private static long helpEventNumber = 0;
    private int eventNumber;
    private int eventUserNumber;
    private String eventName;
    private String streetName;
    private int houseNumber;
    private int ZIP;
    private String city;
    private String country;
    private LocalDateTime startDate;
    private LocalDateTime confirmationDate;     // 2 dagen voor startDate
    private LocalDateTime endDate;
    private double eventDuration;       // in uren
    private String description;
    private URL linkToPage;

    // constructor for DAO
    public Event(int eventNumber, int eventUserNumber, String eventName, String streetName, int houseNumber, int ZIP, String city, String country, LocalDateTime startDate, LocalDateTime confirmationDate, LocalDateTime endDate, double eventDuration, String description, URL linkToPage) {
        this.eventNumber = eventNumber;
        this.eventUserNumber = eventUserNumber;
        this.eventName = eventName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.ZIP = ZIP;
        this.city = city;
        this.country = country;
        this.startDate = startDate;
        this.confirmationDate = confirmationDate;
        this.endDate = endDate;
        this.eventDuration = eventDuration;
        this.description = description;
        this.linkToPage = linkToPage;
    }

    // getters en setters
    public static long getHelpEventNumber() {
        return helpEventNumber;
    }

    public static void setHelpEventNumber(long helpEventNumber) {
        Event.helpEventNumber = helpEventNumber;
    }

    public int getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(int eventNumber) {
        this.eventNumber = eventNumber;
    }

    public int getEventUserNumber() {
        return eventUserNumber;
    }

    public void setEventUserNumber(int eventUserNumber) {
        this.eventUserNumber = eventUserNumber;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getZIP() {
        return ZIP;
    }

    public void setZIP(int ZIP) {
        this.ZIP = ZIP;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(LocalDateTime confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getEventDuration() {
        return eventDuration;
    }

    public void setEventDuration(double eventDuration) {
        this.eventDuration = eventDuration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getLinkToPage() {
        return linkToPage;
    }

    public void setLinkToPage(URL linkToPage) {
        this.linkToPage = linkToPage;
    }


    /*
    // constructor
    public Event(long eventUserNumber, ArrayList<Provider> eventProviderNumbers, long eventNumber, String eventName, Location location, String streetName, int houseNumber, int ZIP, String city, String country, LocalDateTime startDate, LocalDateTime endDate, String description, URL linkToPage, ArrayList<Review> reviews) {
        this.eventUserNumber = eventUserNumber;
        this.eventProviderNumbers = eventProviderNumbers;
        this.eventNumber = helpEventNumber + 1;
        this.eventName = eventName;
        this.location = location;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.ZIP = ZIP;
        this.city = city;
        this.country = country;
        this.startDate = startDate;
        this.confirmationDate = startDate.minusDays(2);
        this.endDate = endDate;
        this.eventDuration = calculateDuration(startDate, endDate);
        this.description = description;
        this.linkToPage = linkToPage;
        this.reviews = reviews;
    }

    // constructor om een event aan te maken met minimale informatie
    public Event(long eventUserNumber, String eventName, String city, String country, LocalDateTime startDate, LocalDateTime endDate, String description, URL linkToPage) {
        this.eventUserNumber = eventUserNumber;
        this.eventNumber = helpEventNumber +1;
        this.eventName = eventName;
        this.city = city;
        this.country = country;
        this.startDate = startDate;
        this.confirmationDate = startDate.minusDays(2);
        this.endDate = endDate;
        this.eventDuration = calculateDuration(startDate, endDate);
        this.description = description;
        this.linkToPage = linkToPage;
    }

    // emptyEvent
    public Event(long eventUserNumber, String eventName, LocalDateTime startDate, LocalDateTime endDate, String description, URL linkToPage) {
        this.eventUserNumber = eventUserNumber;
        this.eventNumber = helpEventNumber++;
        this.eventName = eventName;
        this.confirmationDate = startDate.minusDays(2);
        this.startDate = startDate;
        this.endDate = endDate;
        this.eventDuration = calculateDuration(startDate, endDate);
        this.description = description;
        this.linkToPage = linkToPage;
        this.reviews = new ArrayList<>();
    }
    */

    /*
    // methodes
    public long calculateDuration (LocalDateTime startDate, LocalDateTime endDate) {
        Duration tijd = Duration.between(startDate, endDate);
        if (tijd.toHours() > 24)
            return tijd.toDays();
        return tijd.toHours();
    }

     */






}