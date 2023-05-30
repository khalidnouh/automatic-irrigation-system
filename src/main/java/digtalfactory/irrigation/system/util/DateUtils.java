package digtalfactory.irrigation.system.util;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DateUtils {

    public  Date getIncreamentedDate(Date date,Integer increamentedDays)  {
        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // manipulate date
        c.add(Calendar.DAY_OF_MONTH, increamentedDays);
        // convert calendar to date
        Date currentDatePlusOne = c.getTime();
        //format new date
        //convert string date and return
        return currentDatePlusOne;
    }
    public List<Date> getDaysBetweenTwoDates(Date date1,Date date2,Integer irrigateSchedulerInDays) {
        List<Date>dateList=new ArrayList<>();
//        Long difference = date1.getTime() - date2.getTime();
//        float daysBetween = (difference / (1000 * 60 * 60 * 24));
        //round float to the nearest integer.
//        return Math.round(daysBetween);
        Date tempDate=date1;
        while (tempDate.before(date2)||tempDate.compareTo(date2)==0){
           dateList.add(tempDate);
           tempDate= getIncreamentedDate(tempDate,irrigateSchedulerInDays);
        }
        return dateList;
    }
}
