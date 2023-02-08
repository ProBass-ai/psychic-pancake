package co.za.bookingatsamanthas.app.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class BookingDTO {
    public String email;
    public String checkInDate;
    public String checkOutDate;
    public String price;

    // constructor and getters
}