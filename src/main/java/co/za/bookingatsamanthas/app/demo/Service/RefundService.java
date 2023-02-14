package co.za.bookingatsamanthas.app.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;


@Service
public class RefundService {
    @Autowired
    DateTimeService dateTimeService;



//    @Async
//    public String GetRefundAmout(String dayOfBooking, float bookingAmount){
//
//        int nthOfBookingDate = dateTimeService.getNthOfDay(dayOfBooking);
//        int nthOfCancelationDate = dateTimeService.getNthOfDay(dateTimeService.getTodaysDate());
//        int dayDifference = nthOfCancelationDate - nthOfBookingDate;
//
//        return CalculateAmount(dayDifference, bookingAmount);
//
//    }


    @Async
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
