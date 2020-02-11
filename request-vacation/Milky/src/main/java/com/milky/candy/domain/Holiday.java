package com.milky.candy.domain;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.ibm.icu.util.ChineseCalendar;

public class Holiday {
	
//    public static void main(String[] args) throws Exception {
//    	
//      long date = DateUtils.parseDate("20200127", "yyyyMMdd").getTime();
//      
//      boolean isLegalHoliday = isLegalHoliday(date);
//      boolean isWeekend = isWeekend(date);
//      boolean isAlternative = isAlternative(date);
//      boolean isHoliday = isHoliday(date);
//     
//      System.out.println(isLegalHoliday);
//      System.out.println(isWeekend);
//      System.out.println(isAlternative);
//      System.out.println(isHoliday);
//  }
 
  public static boolean isHoliday(long date) {
      return isLegalHoliday(date) || isWeekend(date) || isAlternative(date);
  }
 
  public static String getLunarDate(long date) {
      ChineseCalendar cc = new ChineseCalendar(new java.util.Date(date));
      String m = String.valueOf(cc.get(ChineseCalendar.MONTH) + 1);
      m = StringUtils.leftPad(m, 2, "0");
      String d = String.valueOf(cc.get(ChineseCalendar.DAY_OF_MONTH));
      d = StringUtils.leftPad(d, 2, "0");
     
      return m + d;
  }
 
  public static boolean isLegalHoliday(long date) {
      boolean result = false;
      
      String endOfDay = null;
      String yearString = DateFormatUtils.format(date, "YYYY");
      int year = Integer.parseInt(yearString);
      
      if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
    	  endOfDay = "1230";
      } else {
    	  endOfDay = "1231";
      }
      
      String[] solar = {"0101", "0301", "0505", "0606", "0815", "1003", "1009", "1225"};
      String[] lunar = {"0101", "0102", "0408", "0814", "0815", "0816", endOfDay};

      List<String> solarList = Arrays.asList(solar);
      List<String> lunarList = Arrays.asList(lunar);
     
      String solarDate = DateFormatUtils.format(date, "MMdd");
      ChineseCalendar cc = new ChineseCalendar(new java.util.Date(date));
     
      String m = String.valueOf(cc.get(ChineseCalendar.MONTH) + 1);
      m = StringUtils.leftPad(m, 2, "0");
      String d = String.valueOf(cc.get(ChineseCalendar.DAY_OF_MONTH));
      d = StringUtils.leftPad(d, 2, "0");
     
      String lunarDate = m + d;
     
      if (solarList.indexOf(solarDate) >= 0) {
          return true;
      }
      if (lunarList.indexOf(lunarDate) >= 0) {
          return true;
      }
     
      return result;
  }

  public static boolean isWeekend(long date) {
      boolean result = false;
     
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(date);
     
      int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
      if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
          result = true;
      }
     
      return result;
  }
 
  public static boolean isAlternative(long date) {
      boolean result = false;
     
      String year = DateFormatUtils.format(date, "yyyy");
      java.util.Date d = null;
      try {
          d = DateUtils.parseDate(year+"0505", "yyyyMMdd");
      } catch (ParseException e) {}
      if (isWeekend(d.getTime()) == true) {
          d = DateUtils.addDays(d, 1);
      }
      if (isWeekend(d.getTime()) == true) {
          d = DateUtils.addDays(d, 1);
      }
      if (DateUtils.isSameDay(new java.util.Date(date), d) == true) {
          result = true;
      }
     
      String lunarDate = getLunarDate(date);
      Calendar calendar = Calendar.getInstance();
      d = new java.util.Date(date);
      if (StringUtils.equals(lunarDate, "0103")) {
         
          d = DateUtils.addDays(d, -1);
          calendar.setTime(d);
          if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
              return true;
          }
         
          d = DateUtils.addDays(d, -1);
          calendar.setTime(d);
          if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
              return true;
          }
         
          d = DateUtils.addDays(d, -1);
          calendar.setTime(d);
          if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
              return true;
          }
      }
     
      d = new java.util.Date(date);
      if (StringUtils.equals(lunarDate, "0817")) {
          d = DateUtils.addDays(d, -1);
          calendar.setTime(d);
          if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
              return true;
          }
         
          d = DateUtils.addDays(d, -1);
          calendar.setTime(d);
          if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
              return true;
          }
         
          d = DateUtils.addDays(d, -1);
          calendar.setTime(d);
          if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
              return true;
          }
      }
     
      return result;
  }
}
