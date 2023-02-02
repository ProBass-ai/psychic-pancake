package co.za.bookingatsamanthas.app.demo.Service;

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
    DateTimeService dth;
    Date startingDate;
    Date endingDate;



    public TransactionsService numberOfDays(int numberOfDaysIn){
        this.numberOfDaysIn = numberOfDaysIn;
        return this;
    }


    public TransactionsService From(Date startingDate){
        this.startingDate = startingDate;
        return this;
    }


    public TransactionsService To(Date endingDate){
        this.endingDate = endingDate;
        return this;
    }


    public String Costs(){
        dth = new DateTimeService();
        ArrayList<Date> dates = dth.getFromAndTo(startingDate, endingDate);

        for (Date date: dates) {
            finalCost += (float) (dth.getNthOfDay(date) + 100) / 12;
        }

        return String.valueOf(finalCost);
    }


}
