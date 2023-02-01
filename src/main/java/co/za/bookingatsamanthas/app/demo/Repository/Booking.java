package co.za.bookingatsamanthas.app.demo.Repository;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;




@Getter @Setter
public class Booking {
    // details of a booking should include when the booking was made
    // who made the booking
    // for whom the booking was made

    @Id
    private ObjectId id;
    private String bookedBy;
    private RoomNumber roomNumber;
    private String bookingDate;
    private String dayOfVisit;
    private String dayOfDeparture;
    private String visitSpecification;
    private String amount;


    public Booking madeBy(String email){
        this.bookedBy = email;
        return this;
    }

    public Booking atRoomNumber(RoomNumber roomNumber){
        this.roomNumber = roomNumber;
        return this;
    }

    public Booking from(String dayofVisit){
        this.dayOfVisit = dayofVisit;
        return this;
    }

    public Booking to(String dayOfDeparture){
        this.dayOfDeparture = dayOfDeparture;
        return this;
    }

    public Booking specifyRequests(String request){
        this.visitSpecification = request;
        return this;
    }

    public Booking ConfirmBooking(){
        return this;
    }

}
