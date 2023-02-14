package co.za.bookingatsamanthas.app.demo.Repository;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;


@Getter @Setter
public class Room {

    @Id
    private ObjectId id;
    private String roomNumber;
    private String isRoomAvailable;

}
