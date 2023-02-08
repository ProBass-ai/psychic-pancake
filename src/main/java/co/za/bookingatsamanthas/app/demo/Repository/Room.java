package co.za.bookingatsamanthas.app.demo.Repository;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;


@Getter @Setter
public class Room {

    @Id
    private ObjectId id;
    private String roomNumber;
    private boolean isRoomAvailable;

}
