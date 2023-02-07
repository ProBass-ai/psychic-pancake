package co.za.bookingatsamanthas.app.demo.Service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


@Service
public class DateTimeService {
    private LocalDateTime todayNow;
    private LocalDateTime dateOf;
    private Calendar calendar;



    public void setDateOf(int year, Month month, int day, int hour, int minute) {
        dateOf = LocalDateTime.of(year, month, day, hour, minute);
    }


    public String getDateTimeNow() {
        Date date = new Date();

        return LocalDateTime.now().toString();
    }

    public String getTodaysDate(){
        LocalDate localDate = LocalDate.now();
        return localDate.toString();
    }

    public Date getDayOfRequest2(){
        return new Date();
    };

    public int getNthOfDay(Date date) {

        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR);

    }


    @Async
    public Date ConvertToDateObject(LocalDateTime localDateTime){

        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

    }


    @Async
    public ArrayList<Date> getFromAndTo(Date startingDate, Date endingDate) {

        ArrayList<Date> datesInBetween = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startingDate);

        while (calendar.getTime().before(endingDate)) {
            Date result = calendar.getTime();
            datesInBetween.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        datesInBetween.add(endingDate);

        return datesInBetween;
    }

}
