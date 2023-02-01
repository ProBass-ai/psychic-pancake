package co.za.bookingatsamanthas.app.demo.Repository;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Room {

    @Id
    private ObjectId id;
    private RoomNumber roomNumber;
    private boolean isRoomAvailable;
    private String forHowLong;

}
