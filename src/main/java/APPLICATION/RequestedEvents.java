package APPLICATION;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class RequestedEvents {      //klasse gebruikt om events aan providers aan te bieden
    private enum Service {music, material, service, location}
    private User organizer;
    private Provider requestedProvider;


    private LocalDateTime datum;
    private int duration;
    private String eventName;
    private String streetName;
    private int houseNumber;
    private int ZIP;
    private String city;
    private String country;
    private LocalDateTime startDate;
    private LocalDateTime confirmationDate;
    private LocalDateTime endDate;
    private String description;
    private URL linkToPage;

}
