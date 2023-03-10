package co.za.bookingatsamanthas.app.demo.Repository;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;



@Getter @Setter
public class Booking {
    // details of a booking should include when the booking was made
    // who made the booking
    // for whom the booking was made

    @Id
    private ObjectId id;
    private String bookedBy;
    private String roomNumber;
    private String bookingDate;
    private String dayOfVisit;
    private String dayOfDeparture;
    private String visitSpecification;
    private String amount;


}
