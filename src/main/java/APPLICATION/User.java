package APPLICATION;

import java.util.ArrayList;
import java.util.Date;

public class User {

    // instantievariabelen
    private static int helpUserNumber = 0;
    private int userNumber;                    // unique key
    private String firstName;
    private String lastName;
    private String name;
    private Date dateOfBirth;
    private int age;                            // derived from dateOfBirth
    private String email;           // multiple emails
    private String phoneNumber;     // multiple phoneNumbers
    private ArrayList<Event> actualEvents;
    private ArrayList<Event> history;
    private ArrayList<Review> reviews;

    // constructor
    public User(String firstName, String lastName, Date dateOfBirth, String email, String phoneNumber) {
        this.userNumber = helpUserNumber++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.name = firstName + " " + lastName;
        this.dateOfBirth = dateOfBirth;
        //this.age = Period.between(dateOfBirth, LocalDate.now()).getYears();      // correct
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.actualEvents = new ArrayList<>();
        this.history = new ArrayList<>();
        this.reviews = new ArrayList<>();
        // userDAO.update()
    }

    // constructor for UserDAO
    public User(int userNumber, String firstName, String lastName, Date dateOfBirth, int age, String email, String phoneNumber) {
        this.userNumber = userNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }


    // methodes
    /*
    public Event createEmptyEvent (String eventName, LocalDateTime startDate, LocalDateTime endDate, String description, URL linkToPage) {
        Event newEvent = new Event(getUserNumber(), eventName, startDate, endDate, description, linkToPage);
        actualEvents.add(newEvent);
        return newEvent;
    }

     */

    // getters en setters
    public static int getHelpUserNumber() {
        return helpUserNumber;
    }

    public static void setHelpUserNumber(int helpUserNumber) {
        User.helpUserNumber = helpUserNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmails(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Event> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Event> history) {
        this.history = history;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

}
