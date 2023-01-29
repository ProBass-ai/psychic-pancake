package co.za.bookingatsamanthas.app.demo.Controller;

import co.za.bookingatsamanthas.app.demo.Repository.Booking;
import co.za.bookingatsamanthas.app.demo.Repository.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class RequestController {
    @GetMapping("/rooms")
    public List<Room> getRooms() {
        // code to retrieve rooms from the database and return as a response
    }

    @PostMapping("/book")
    public ResponseEntity<Room> bookRoom(@RequestBody Booking booking) {
        // code to handle booking a room
    }

    @PutMapping("/reschedule")
    public ResponseEntity<Room> rescheduleBooking(@RequestBody RescheduleRequest rescheduleRequest) {
        // code to handle rescheduling a booking
    }

    @DeleteMapping("/cancel")
    public ResponseEntity<Void> cancelBooking(@RequestParam("bookingId") String bookingId) {
        // code to handle cancelling a booking
    }
}
