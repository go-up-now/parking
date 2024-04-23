
package parking.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    
    public static Date StringtoDate(String date, String pattern) throws ParseException{
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.parse(date);
    }
    
    public static String DatetoString(Date date, String pattern){
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }
    
    public static Date addDays(Date date, int day){
        date.setTime(date.getTime()+(day*24*60*60*1000));
        return date;
    }
}
