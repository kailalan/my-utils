package history.calendar;

import java.util.*;
import java.text.SimpleDateFormat;

public class CalendarUtil {
    public static void main(String[] args) {
        // calendar cal = calendar.getInstance();
        // //设置时间2016-08-31
        // cal.set(2016,calendar.FEBRUARY,28);
        
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // System.out.println(sdf.format(cal.getTime()));

        // calendar cal2 = calendar.getInstance();

        // cal2.set(cal.get(calendar.YEAR),cal.get(calendar.MONTH),cal.get(calendar.DAY_OF_MONTH)+2);
        // System.out.println(sdf.format(cal2.getTime()));

        // cal2.set(2016,calendar.AUGUST,360);
        // System.out.println(sdf.format(cal2.getTime()));    
        
        // Date date =new Date();
        // cal.setTime(date);
        // System.out.println(sdf.format(cal.getTime()));   
          


        int year = 2018;
    	int month = 9;
    	int date = 6;
    	
    	
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, year);
    	calendar.set(Calendar.MONTH, month-1);
    	calendar.set(Calendar.DATE,date);
    	Date dateTime = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");   
        String dateString = sdf.format(dateTime);
        System.out.println(dateString);   
    }
}