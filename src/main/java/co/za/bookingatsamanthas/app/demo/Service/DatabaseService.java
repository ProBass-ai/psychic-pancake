package co.za.bookingatsamanthas.app.demo.Service;

import co.za.bookingatsamanthas.app.demo.DTO.RoomDTO;
import co.za.bookingatsamanthas.app.demo.Repository.Booking;
import co.za.bookingatsamanthas.app.demo.DTO.BookingDTO;
import co.za.bookingatsamanthas.app.demo.Repository.Room;
import co.za.bookingatsamanthas.app.demo.Repository.UserProfile;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.bson.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;


@Service
public class DatabaseService {
    @Autowired
    public MongoTemplate mongoTemplate;


    @Async
    public Document saveNewBooking(String booking){
        // to be refactored

        Document doc = Document.parse(booking);

        String roomNumber = (String) doc.get("roomNumber");

        makeRoomUnavailable(roomNumber);

        return mongoTemplate.insert(doc, "bookings");

    }

    @Async
    public Booking rescheduleBooking(Booking newBooking){

        Query query = new Query();
        query.addCriteria(Criteria.where("bookedBy").is(newBooking.getBookedBy()));
//                .addCriteria(Criteria.where("bookingDate").is(newBooking.getBookingDate()));

        Booking booking = mongoTemplate.findOne(query, Booking.class, "bookings");
        assert booking != null;

        booking.setDayOfVisit(newBooking.getDayOfVisit());
        booking.setDayOfDeparture(newBooking.getDayOfDeparture());

        return mongoTemplate.save(booking, "bookings");
    }

    @Async
    public DeleteResult cancelBooking(Booking booking){

        Query query = new Query();

        query.addCriteria(Criteria.where("bookedBy").is(booking.getBookedBy()))
                .addCriteria(Criteria.where("dayOfVisit").is(booking.getDayOfVisit()))
                .addCriteria(Criteria.where("dayOfDeparture").is(booking.getDayOfDeparture()));


        DeleteResult deleteResult = mongoTemplate.remove(query, Booking.class, "bookings");

        makeRoomAvailable(booking.getRoomNumber());

        return deleteResult;
    }


    @Async
    private void makeRoomAvailable(String roomNumber){

        Query query = new Query();
        query.addCriteria(Criteria.where("roomNumber").is(roomNumber));

        Room room = mongoTemplate.findOne(query, Room.class, "rooms");
        assert room != null;

        room.setIsRoomAvailable("true");

        mongoTemplate.save(room, "rooms");

    }

    @Async
    private void makeRoomUnavailable(String roomNumber){
        Query query = new Query();
        query.addCriteria(Criteria.where("roomNumber").is(roomNumber));

        Room room = mongoTemplate.findOne(query, Room.class, "rooms");
        assert room != null;

        room.setIsRoomAvailable("false");

        mongoTemplate.save(room, "rooms");
    }


    @Async
    public Document saveNewUSer(String document) {

        Document doc = Document.parse(document);
        return mongoTemplate.insert(doc, "profiles");

    }

    public Document deleteUser(String userEmail){

        // retrieve user profile from database
        // then delete it

        return null;
    }


    public Document updateUserProfile(String email, HashMap<String, String> newValues){
        // perform updates on a users profile

        UserProfile user = getUserByEmail(email);

        UserProfile updated = updateUserObject(user, newValues);

        mongoTemplate.save(updated, "profiles");

        return null;
    }


    @Async
    public UserProfile updateUserObject(UserProfile user, HashMap<String, String> newValues){

        int mapLength = newValues.size();

        for (int i = 0; i < mapLength; i++) {

            if (newValues.containsKey("name")){

                user.setName(newValues.get("name"));
            }

            if (newValues.containsKey("surname")) {
                user.setSurname(newValues.get("surname"));
            }

            if (newValues.containsKey("phoneNumber")) {
                user.setPhoneNumber(newValues.get("phoneNumber"));
            }
        }

        return user;
    }


    @Async
    public UserProfile getUserByEmail(String email) {

        Query query = new Query();

        query.addCriteria(Criteria.where("email").is(email));

        try {

            UserProfile user = mongoTemplate.findOne(query, UserProfile.class, "profiles");
            assert user != null;

            return user;

        } catch (NullPointerException npe){
            // report back to frontend
//            throw (npe);
        }

        return null;
    }


    @Async
    public BookingDTO[] getPreviousBookings(String userEmail){
        Query query = new Query();
        query.addCriteria(Criteria.where("bookedBy").is(userEmail));

        Booking[] querriedBookings = new ArrayList<>(
                mongoTemplate.find(query, Booking.class, "bookings")
        ).toArray(new Booking[0]);

        BookingDTO[] bookingDTOS = new BookingDTO[querriedBookings.length];

        for (int i = 0; i < querriedBookings.length; i++) {

            BookingDTO bookingDTO = new BookingDTO(
                    querriedBookings[i].getBookedBy(),
                    querriedBookings[i].getDayOfVisit(),
                    querriedBookings[i].getDayOfDeparture(),
                    querriedBookings[i].getAmount()
            );

            bookingDTOS[i] = bookingDTO;
        }

        return bookingDTOS;
    }


    @Async
    public RoomDTO[] getAvalableRooms(){

        Query query = new Query();
        query.addCriteria(Criteria.where("isRoomAvailable").is(true));

        Room[] querriedRooms = new ArrayList<>(
                mongoTemplate.find(query, Room.class, "rooms")
        ).toArray(new Room[0]);

        RoomDTO[] roomDTOS = new RoomDTO[querriedRooms.length];

        for (int i = 0; i < querriedRooms.length; i++) {
            RoomDTO room = new RoomDTO(
                    querriedRooms[i].getRoomNumber(),
                    querriedRooms[i].getIsRoomAvailable()
            );

            roomDTOS[i] = room;
        }

        return roomDTOS;
    }






//    @Async
//    public ArrayList<Room> getAvailableRooms(){
//        Query query = new Query();
//        query.addCriteria(Criteria.where("isRoomAvailable").is("true"));
//
//        ArrayList<Room> availableRooms = new ArrayList<>(
//                mongoTemplate.find(query, Room.class, "rooms")
//        );
//
//        return availableRooms;
//    }

    @Async
    public void getProfileInfo(){

    }
}
