package co.za.bookingatsamanthas.app.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;


@Service
public class RefundService {
    @Autowired
    DateTimeService dateTimeService;

    Date dayOfRequest;

    Date dayOfBooking;


    public String GetRefundAmout(LocalDateTime day, float bookingAmount){

        dayOfRequest = dateTimeService.getDayOfRequest2();
        dayOfBooking = dateTimeService.ConvertToDateObject(day);


        int nthOfBookingDate = dateTimeService.getNthOfDay(dayOfBooking);
        int nthOfCancelationDate = dateTimeService.getNthOfDay(dayOfRequest);
        int dayDifference = nthOfCancelationDate - nthOfBookingDate;


        return CalculateAmount(dayDifference, bookingAmount);

    }

    public String CalculateAmount(int dayDifference, float amount){

        if (!(dayDifference <= 7)){
            return String.valueOf(amount);
        } else if (dayDifference <= 7 && dayDifference > 2){
            return String.valueOf(amount / 2);
        } else {
            return String.valueOf(0.00);
        }
    }

}
