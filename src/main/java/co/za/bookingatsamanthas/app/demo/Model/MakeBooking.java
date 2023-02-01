package co.za.bookingatsamanthas.app.demo.Model;

import co.za.bookingatsamanthas.app.demo.Repository.Booking;
import co.za.bookingatsamanthas.app.demo.Service.DatabaseService;
import com.google.gson.Gson;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;



public class MakeBooking {
    @Autowired
    DatabaseService dbService;

    @Autowired
    Gson gson;

    public Document newBooking(Booking booking){

        String bookingDoc = gson.toJson(booking);
        return dbService.saveNewBooking(bookingDoc);

    }

    public boolean deleteBooking(Booking booking){
        return dbService.cancelBooking(booking).wasAcknowledged();
    }

    public void rescheduleBooking(){

    }

}
