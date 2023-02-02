package co.za.bookingatsamanthas.app.demo.Controller;

import co.za.bookingatsamanthas.app.demo.Repository.Booking;
import co.za.bookingatsamanthas.app.demo.Repository.UserProfile;
import co.za.bookingatsamanthas.app.demo.Service.DatabaseService;
import com.google.gson.Gson;
import freemarker.template.utility.NullArgumentException;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Base64;
import java.util.HashMap;






@Controller
@RequestMapping("/")
public class RestController {

    @Autowired
    Gson gson;


    @Autowired
    DatabaseService dbService;



    @RequestMapping(value = "/new-account", method = RequestMethod.POST)
    public ResponseEntity createNewAccount(@RequestBody String newUserData){

        UserProfile newUser = gson.fromJson(newUserData, UserProfile.class);
        Document inserted = dbService.saveNewUSer(newUserData);

        if (inserted.isEmpty()){
            throw new NullArgumentException("Could not save profile!\nProfile values: " + newUserData);
        }

        return new ResponseEntity<>(inserted.toJson(), HttpStatus.OK);
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login(@RequestBody HashMap<String, String> requestBody){

        String email = requestBody.get("email");

        // must return customers history
        // go to database
        // get any booking that contains the customers email(as emails can's be repeated)
        // get any booking the customer cancelled
        // get return rooms and their availability status
        // send them over to front-end

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/create-new-booking", method = RequestMethod.POST)
    public ResponseEntity createNewBooking(@RequestBody Booking booking){

        Document doc = dbService.saveNewBooking(gson.toJson(booking));

        return new ResponseEntity<>(doc.toJson(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update-booking", method = RequestMethod.PUT)
    public ResponseEntity updateBooking(@RequestBody HashMap<String, Object> bookingString){

        String responseBody = gson.toJson(dbService.rescheduleBooking(deserilizeBooking(bookingString)));

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete-booking", method = RequestMethod.DELETE)
    public ResponseEntity deleteBooking(@RequestBody Booking booking) {

        boolean operationResult = dbService.cancelBooking(booking).wasAcknowledged();

        // need to fix validation method

        if (operationResult){
            return new ResponseEntity<>(Boolean.toString(operationResult), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(Boolean.toString(operationResult), HttpStatus.EXPECTATION_FAILED);
        }

    }

    @PostMapping("/upload")
    public void uploadImage(@RequestParam("imageData") String imageData) {
        // Use Base64 decoder to get the binary image data
        byte[] imageBytes = Base64.getDecoder().decode(imageData.split(",")[1]);

        // Save the image to disk or do whatever you want with it.
        // ...
    }


    private Booking deserilizeBooking(HashMap<String, Object> attr){

        int mapLength = attr.size();
        Booking booking = new Booking();

        for (int i = 0; i < mapLength; i++) {

            if (attr.containsKey("bookedBy")){
                booking.setBookedBy((String) attr.get("bookedBy"));
            }

            if (attr.containsKey("dayOfVisit")){
                booking.setDayOfVisit((String) attr.get("dayOfVisit"));
            }

            if (attr.containsKey("dayOfDeparture")){
                booking.setDayOfDeparture((String) attr.get("dayOfDeparture"));
            }


        }

        return booking;
    }

}
