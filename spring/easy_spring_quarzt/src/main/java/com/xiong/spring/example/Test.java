package com.xiong.spring.example;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import org.joda.time.DateTime;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Test {
    // 1.1 -1.30   2.1 - 2.28  //2.1 - 2.28
    // 2.1 -2.28

    public static void main(String[] args) throws ParseException {
        String format = "yyyy-MM-dd";
        String format_HH = "yyyy-MM-dd HH:mm:ss";
        Date date = DateUtil.parse("2021-02-27 23:55:00", format_HH);
//        System.out.println(plusMonth(date,1));
//        System.out.println(DateUtil.beginOfHour(date));
        Calendar calendar = CalendarUtil.calendar(date);
        int dd = calendar.get(Calendar.DATE);
//        CronExpression cronExpression = new CronExpression("0 0 0 27 * ?");
        CronExpression cronExpression = new CronExpression("0 0 0 L 2/3 ?");
        Date nextDate = cronExpression.getNextValidTimeAfter(date);
        System.out.println(DateUtil.format(nextDate,format_HH));
        nextDate = cronExpression.getNextValidTimeAfter(nextDate);
        System.out.println(DateUtil.format(nextDate,format_HH));
        nextDate = cronExpression.getNextValidTimeAfter(nextDate);
        System.out.println(DateUtil.format(nextDate,format_HH));
        nextDate = cronExpression.getNextValidTimeAfter(nextDate);
        System.out.println(DateUtil.format(nextDate,format_HH));
        nextDate = cronExpression.getNextValidTimeAfter(nextDate);
        System.out.println(DateUtil.format(nextDate,format_HH));
        nextDate = cronExpression.getNextValidTimeAfter(nextDate);
        System.out.println(DateUtil.format(nextDate,format_HH));
//        nextDate = withDay(nextDate,30);
//        nextDate.setDate(30);
//        calendar = CalendarUtil.calendar(nextDate);
//        calendar.set(Calendar.DAY_OF_MONTH,dd);
//        nextDate = calendar.getTime();
//        System.out.println(DateUtil.format(nextDate,format_HH));
    }
    public static Date plusMonth(Date time, int months) {
        return new DateTime(time).plusMonths(months).toDate() ;
    }

    public static Date withDay(Date time, int day) {
        return new DateTime(time).withDayOfMonth(getLastDay(time,day)).toDate() ;
    }
    public static int getLastDay(Date time, int day) {
        DateTime lastDate = new DateTime(time).dayOfMonth().withMaximumValue();
        // Print the date and day name.
//        System.out.println("Date = " + lastDate.toString());
//        System.out.println("Date day = " + lastDate.getDayOfMonth());
//        System.out.println("Day  = " + lastDate.dayOfWeek().getAsText());
        return (day > lastDate.getDayOfMonth()) ? lastDate.getDayOfMonth():day;
    }
}
