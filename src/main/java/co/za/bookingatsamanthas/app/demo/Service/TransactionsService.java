package co.za.bookingatsamanthas.app.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;



@Service
public class TransactionsService {
    // class is meant to control methods for making transactions
    // e.g. calculate the cost of booking a room a certain number of days
    // : calculate the cost of refund considering request time frame
    // (nthOfDay+100)/12

    float finalCost;
    int numberOfDaysIn;

    @Autowired
    DateTimeService dth;

    Date startingDate;
    Date endingDate;


    @Async
    public TransactionsService numberOfDays(int numberOfDaysIn){
        this.numberOfDaysIn = numberOfDaysIn;
        return this;
    }

    @Async
    public TransactionsService From(String startingDate){

        this.startingDate = dth.parseDateString(startingDate);
        return this;
    }

    @Async
    public TransactionsService To(String endingDate){
        this.endingDate = dth.parseDateString(endingDate);
        return this;
    }

    @Async
    public String Costs(){
        dth = new DateTimeService();
        ArrayList<Date> dates = dth.getFromAndTo(startingDate, endingDate);

        for (Date date: dates) {
            finalCost += (float) (dth.getNthOfDay(date) + 100) / 12;
        }

        return String.valueOf(finalCost);
    }


}
