package co.za.bookingatsamanthas.app.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class RefundService {
    @Autowired
    DateTimeService dateTimeService;

    boolean RequestedWithin14Days;
    boolean RequestedWithin7Days;
    boolean RequestedFrom2Days;

    String dayOfRequest;


    public void GetRefundAmout(){
        dayOfRequest = dateTimeService.getTodaysDate();

    }


    public static String CalculateAmount(int dayDifference, float amount){

        if (!(dayDifference <= 7)){
            return String.valueOf(amount);
        } else if (dayDifference <= 7 && dayDifference > 2){
            return String.valueOf(amount / 2);
        } else {
            return String.valueOf(0.00);
        }
    }

}
