package co.za.bookingatsamanthas.app.demo.Service;

import co.za.bookingatsamanthas.app.demo.Repository.Booking;
import co.za.bookingatsamanthas.app.demo.Repository.UserProfile;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.bson.Document;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class DatabaseService {
    @Autowired
    public MongoTemplate mongoTemplate;


    @Async
    public Document saveNewBooking(String booking){

        Document doc = Document.parse(booking);
        return mongoTemplate.insert(doc, "bookings");

    }

    @Async
    public Booking rescheduleBooking(Booking newBooking){

        Query query = new Query();
        query.addCriteria(Criteria.where("bookedBy").is(newBooking.getBookedBy()));

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

        return deleteResult;
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


}
