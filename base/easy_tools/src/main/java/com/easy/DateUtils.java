package com.easy;

import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("Duplicates")
public class DateUtils {

    public static final String FORMAT_YYYY_MM_DD          = "yyyy-MM-dd";
    public static final String FORMAT_YYYYMM              = "yyyyMM";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YYYY_MM_DD_HH_MM    = "yyyyMMddHHmm";
    public static final String FORMAT_YYYYMMDDHHMMSS      = "yyyyMMddHHmmss";
    public static final String FORMAT_YYYYMMDD            = "yyyyMMdd";
    public static final String FORMAT_YYYYMMDDHH          = "yyyyMMddHH";

    public static final String FORMAT_YYYY_MM = "yyyy-MM";
    public static final String FORMAT_YYYY_MM_DD_T_HH_MM_SS_000_0000 = "yyyy-MM-dd HH:mm:ss.000+0000";

    /**
     * 获取当前日期
     *
     * @return
     */
    public static Date nowDate() {
        return new Date();
    }

    /**
     * 获取日期为YYYY-MM-DD格式
     *
     * @return
     */
    public static String formatDateToStringYYMMDD(Date date) {
        return formatDateToString(date, FORMAT_YYYY_MM_DD);
    }

    /**
     * 获取日期为YYYY-MM-DD HH:mm格式
     *
     * @return
     */
    public static String formatDateToStringYYMMDDHHMM(Date date) {
        return formatDateToString(date, FORMAT_YYYY_MM_DD_HH_MM);
    }

    /**
     * 获取日期为YYYY-MM-DD格式
     *
     * @return
     */
    public static String formatDateToStringYYMMDDHHmmss(Date date) {
        return formatDateToString(date, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取日期为YYYY-MM格式
     *
     * @return
     */
    public static String formatDateToStringYYMM(Date date) {
        return formatDateToString(date, FORMAT_YYYYMM);
    }

    /**
     * 获取日期为YYYYMMDD格式
     *
     * @return
     */
    public static String formatDateToStringYYYYMMDD(Date date) {
        return formatDateToString(date, FORMAT_YYYYMMDD);
    }

    /**
     * 获取日期为YYYY-MM格式
     *
     * @return
     */
    public static String formatDateToStringYY_MM(Date date) {
        return formatDateToString(date, FORMAT_YYYY_MM);
    }

    /**
     * 获取日期为YYYY-MM-dd格式
     *
     * @return
     */
    public static String formatDateToStringYY_MM_DD(Date date) {
        return formatDateToString(date, FORMAT_YYYY_MM_DD);
    }

    /**
     * 获取日期为YYYYMMDDHHMMSS格式
     *
     * @return
     */
    public static String formatDateToStringYYYYMMDDHHMMSS(Date date) {
        return formatDateToString(date, FORMAT_YYYYMMDDHHMMSS);
    }


    /**
     * 根据日期格式，获取日期字符串
     *
     * @return
     */
    public static String formatDateToString(Date date, String format) {
        if (date == null)
            return "";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 根据日期格式，获取日期字符串
     *
     * @return
     */
    public static String nowDateToStringYYMMDDHHmmss() {
        Date date = nowDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
        try {
            return dateFormat.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 根据日期格式，获取日期字符串
     *
     * @return
     */
    public static String nowDateToStringYYMM() {
        Date date = nowDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_YYYYMM);
        try {
            return dateFormat.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 根据日期格式，获取日期字符串
     *
     * @return
     */
    public static String nowDateToString(String format) {
        Date date = nowDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 获取指定日期当前周的周一
     * @param date
     * @return
     */
    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 获取指定日期当前周的周日
     * 中国的周日实际上是下周
     * @param date
     * @return
     */
    public static Date getNextWeekSunday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getThisWeekMonday(date));
        cal.add(Calendar.DATE, 6);
        return cal.getTime();
    }


    /**
     * 获取今年的第一个月份
     *
     * @return
     */
    public static String getCurrentYearFirstMonth() {
        Calendar cla = Calendar.getInstance();
        return cla.get(Calendar.YEAR) + "01";
    }

    /**
     * 根据日期字符，转化日期格式为：YYYY-MM-DD
     *
     * @param value
     * @return
     */
    public static Date parseStringToDateYYMMDD(String value) {
        return parseStringToDate(value, FORMAT_YYYY_MM_DD);
    }

    /**
     * 根据日期字符，转化日期格式为：YYYYMMDD
     *
     * @param value
     * @return
     */
    public static Date parseStringToDateYYYYMMDD(String value) {
        return parseStringToDate(value, FORMAT_YYYYMMDD);
    }

    /**
     * 根据日期字符，转化日期格式为：YYYYMM
     *
     * @param value
     * @return
     */
    public static Date parseStringToDateYYYYMMT(String value) {
        return parseStringToDate(value, FORMAT_YYYYMM);
    }

    /**
     * 根据日期字符，转化日期格式为：yyyyMMddHHmmss
     *
     * @param value
     * @return
     */
    public static Date parseStringToDateYYYYMMDDHHMMSS(String value) {
        return parseStringToDate(value, FORMAT_YYYYMMDDHHMMSS);
    }

    /**
     * 根据日期字符，转化日期格式为：YYYY-MM
     *
     * @param value
     * @return
     */
    public static Date parseStringToDateYYMM(String value) {
        return parseStringToDate(value, FORMAT_YYYY_MM);
    }

    /**
     * 根据日期字符，转化日期格式为：YYYYMMDD
     *
     * @param value
     * @return
     */
    public static Date parseStringToDateYYYYMM(String value) {
        return parseStringToDate(value, FORMAT_YYYY_MM);
    }

    /**
     * 根据日期字符，转化日期格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param value
     * @return
     */
    public static Date parseStringToDateYYMMDDHHmmss(String value) {
        return parseStringToDate(value, FORMAT_YYYY_MM_DD_HH_MM_SS);
    }

    public static Date parseDateDayEnd(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
        String dateStr = sdf.format(date) + " 23:59:59";
        return sdf2.parse(dateStr);
    }

    public static Date parseDateDayBegin(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
        SimpleDateFormat sdf2 = new SimpleDateFormat(FORMAT_YYYY_MM_DD_HH_MM_SS);
        String dateStr = sdf.format(date) + " 00:00:00";
        return sdf2.parse(dateStr);
    }

//    /**
//     * 根据日期字符和转化日格，转化成日期
//     * @param value
//     * @param format
//     * @return
//     */
//    public static Date parseStringToDateYYMMDD(String value,String format){
//        if(StringUtils.isEmpty(value)){
//            return null;
//        }
//        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
//        try{
//            return dateFormat.parse(value);
//        }catch(Exception ex){
//            ex.printStackTrace();
//            return null;
//        }
//    }

    public static Date parseStringToDate(String value, String format) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(value);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date parseNowDateYYMMDD() {
        String value = formatDateToStringYYMMDD(nowDate());
        return parseStringToDateYYMMDD(value);
    }

    /**
     * 向某个日期，添加天数
     *
     * @param date 日期
     * @param day  天数
     * @return 返回添加天数之后的日期
     */
    public static Date addDateDay(Date date, int day) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    /**
     * 取某个日期是星期几
     *
     * @param date
     * @param day
     * @return
     */
    public static String getDayOfWeek(Date date, int day) {
        Calendar calendar = Calendar.getInstance();   //创建一个日历对象
        calendar.setTime(date);             //用当前时间初始化日历时间
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);

        return String.valueOf(calendar.get(Calendar.DAY_OF_WEEK) - 1);
    }

    /**
     * 取某个时间月份
     *
     * @param date
     * @param day
     * @return
     */
    public static String getMonthOfYear(Date date, int day) {
        Calendar calendar = Calendar.getInstance();   //创建一个日历对象
        calendar.setTime(date);             //用当前时间初始化日历时间
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + day);
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);

        return month;
    }

    /**
     * 向某个日期，添加月份
     *
     * @param date  日期
     * @param month 天数
     * @return 返回添加月份之后的日期
     */
    public static Date addDateMonth(Date date, int month) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    /**
     * 获得当前的小时
     *
     * @return
     */
    public static int getCurrentHour() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 比较两个日期的相差天数，是不是指定的相差天数，如果是返回true;
     * @param fromDate 开始日期
     * @param toDate 结束日期
     * @param day  天数
     * @return
     */
    public static boolean compareDay(Date fromDate, Date toDate, int day) {
        if (fromDate == null || toDate == null) {
            return false;
        }
        return addDateDay(fromDate, day).after(toDate);
    }

    /**
     * 创建token　Expire时间的天数
     *
     * @param day
     * @return
     */
    public static Date createTokenExpireDate(int day) {
        return addDateDay(nowDate(), day);
    }

    /**
     * //比较时间大小
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static int comperDate(Date fromDate, Date toDate, String flag) {

        if (fromDate == null || toDate == null) {
            return 1;
        }
        int retInt = 0;
        SimpleDateFormat f = null;
        if ("0".equals(flag)) {
            f = new SimpleDateFormat(FORMAT_YYYYMMDD);
        } else if ("1".equals(flag)) {
            f = new SimpleDateFormat(FORMAT_YYYYMMDDHH);
        }

        //	SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");

        int fromDateNumber = Integer.parseInt(f.format(fromDate).toString());

        int toDateNumber = Integer.parseInt(f.format(toDate).toString());
        //System.out.println("fromDateNumber+==" + fromDateNumber + "toDateNumber==="+ toDateNumber);
        retInt = fromDateNumber - toDateNumber;

        return retInt;
    }

    public static String getDateInterval(Date date, int billingType) {

        String retStr = "";
        String year = formatDateToStringYY_MM(date).substring(0, 4);
        int month = Integer.parseInt(getMonthOfYear(new Date(), 0));
        int maxDay = 0;
        Calendar calendar = Calendar.getInstance();
        if (billingType == 1) {//按月
            calendar.set(Integer.parseInt(year), month - 1, 1);
            maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            retStr = formatDateToStringYY_MM(date) + "-01," + formatDateToStringYY_MM(date) + "-" + maxDay;
        } else if (billingType == 3) {//按季度
            if (month >= 1 && month <= 3) {
                calendar.set(Integer.parseInt(year), 2, 1);
                maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                retStr = year + "-01-01," + year + "-03-" + maxDay;
            } else if (month >= 4 && month <= 6) {
                calendar.set(Integer.parseInt(year), 5, 1);
                maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                retStr = year + "-04-01," + year + "-06-" + maxDay;
            } else if (month >= 7 && month <= 9) {
                calendar.set(Integer.parseInt(year), 8, 1);
                maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                retStr = year + "-07-01," + year + "-09-" + maxDay;
            } else if (month >= 10 && month <= 12) {
                calendar.set(Integer.parseInt(year), 11, 1);
                maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                retStr = year + "-10-01," + year + "-12-" + maxDay;
            }
        } else if (billingType == 6) {//半年
            if (month >= 1 && month <= 6) {
                calendar.set(Integer.parseInt(year), 5, 1);
                maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                retStr = year + "-01-01," + year + "-06-" + maxDay;
            } else if (month >= 6 && month <= 12) {
                calendar.set(Integer.parseInt(year), 11, 1);
                maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                retStr = year + "-07-01," + year + "-12-" + maxDay;
            }
        } else if (billingType == 12) {//半年
            if (month >= 1 && month <= 12) {
                calendar.set(Integer.parseInt(year), 11, 1);
                maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                retStr = year + "-01-01," + year + "-12-" + maxDay;
            }
        }
        return retStr;
    }

    /**
     * 账期日期字符串转yyyy-MM
     */
    public static String parseStrCycleToYYMMStrDate(String strCycle) {
        if (strCycle == null || strCycle.length() < 6)
            return "";
        String year = strCycle.substring(0, 4);
        String month = strCycle.substring(4, 6);
        return year + "-" + month;
    }

    public static String parseStrCycleToYYMMDDStrDate(String strCycle) {
        if (strCycle == null || strCycle.length() < 6)
            return "";
        String year = strCycle.substring(0, 4);
        String month = strCycle.substring(4, 6);
        String day = strCycle.substring(6, 8);
        return year + "." + month + "." + day;
    }

    private static boolean isNumericForStartAndEnd(String str) {
        Pattern pattern = Pattern.compile("((\\d{4}|\\d{2})-((0?([1-9]))|(1[0|1|2]))-((0?[1-9])|([1|2]([0-9]))|(3[0|1])))");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }


    /**
     * 获取指定日期的当月总天数
     *
     * @param date
     * @return
     */
    public static int getMonthTotalDayNum(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期的当月最后一天
     */
    public static final Date lastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    public static final Date lastDayOfLastMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH,-1);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    public static final Date firstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    /**
     * 是否是本月最后一天
     *
     * @param date
     * @return
     */
    public static final boolean isLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE) == cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 是否是本月第一天
     *
     * @param date
     * @return
     */
    public static final boolean isFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE) == cal.getActualMinimum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 日期增加月份数后的新日期
     *
     * @param date
     * @return
     */
    public static final Date addMonthNumbersOfDate(Date date, int monthNumbers) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, monthNumbers);
        return cal.getTime();
    }

    /**
     * 日期增加月份数后的新日期（只计算月头）
     *
     * @param date
     * @return
     */
    public static final Date addMonthNumbersOfDateMonthHead(Date date, int monthNumbers) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, monthNumbers);
        int value = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    /**
     * 日期增加月份数后的新日期（只计算月尾）
     *
     * @param date
     * @return
     */
    public static final Date addMonthNumbersOfDateMonthTail(Date date, int monthNumbers) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, monthNumbers);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return cal.getTime();
    }

    /**
     * 计算两个日期相差天数
     *
     * @param early
     * @param late
     * @return
     */
    public static final int daysBetween(Date early, Date late) {

        java.util.Calendar calst = java.util.Calendar.getInstance();
        java.util.Calendar caled = java.util.Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //设置时间为0时
        calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
        calst.set(java.util.Calendar.MINUTE, 0);
        calst.set(java.util.Calendar.SECOND, 0);
        caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
        caled.set(java.util.Calendar.MINUTE, 0);
        caled.set(java.util.Calendar.SECOND, 0);
        //得到两个日期相差的天数
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }

    /**
     * 计算两个日期相差分钟
     *
     * @param early
     * @param late
     * @return
     */
    public static final int minutesBetween(Date early, Date late) {

        java.util.Calendar calst = java.util.Calendar.getInstance();
        java.util.Calendar caled = java.util.Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //得到两个日期相差的分钟数
        int minutes = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 60;

        return minutes;
    }

    /**
     * 计算两个日期相差小时
     *
     * @param early
     * @param late
     * @return
     */
    public static final int hoursBetween(Date early, Date late) {
        java.util.Calendar calst = java.util.Calendar.getInstance();
        java.util.Calendar caled = java.util.Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //得到两个日期相差的分钟数
        int hours = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 60 / 60;

        return hours;
    }

    /**
     * 给某给时间增加分钟
     *
     * @param early
     * @param minutes
     * @return
     */
    public static final String hourBetween(Date early, int minutes) {

        java.util.Calendar calst = java.util.Calendar.getInstance();
        calst.setTime(early);
        calst.add(Calendar.MINUTE, minutes);//30分钟后的时间
        String lateDate = formatDateToStringYYMMDDHHmmss(calst.getTime());

        return lateDate;
    }


    /**
     * 获取当前月份的总天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //得到一个月最最后一天日期(31/30/29/28)
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return value;
    }

    /**
     * 获取当前月已过天数
     * @param date
     * @return
     */
    public static int getDaysOfMonthNow(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return Integer.parseInt(sdf.format(date));
    }

    public static List<String> getDayListOfMonth(Date date){
        List<String> list = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=0;i<maxDay;i++){
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            list.add(sdf.format(cal.getTime()));
            cal.add(Calendar.DATE,1);
        }
        return list;
    }

    public static List<String> getWorkdayOfMonth(Date date){
        List<String> list = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=0;i<maxDay;i++){
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek != Calendar.SUNDAY && dayOfWeek != Calendar.SATURDAY){
                list.add(sdf.format(cal.getTime()));
            }
            cal.add(Calendar.DATE,1);
        }
        return list;
    }

    public static List<String> getWorkdayOfMonthNow(Date date){
        List<String> list = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int nowDay = cal2.get(Calendar.DAY_OF_MONTH);
        for(int i=0;i<nowDay;i++){
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek != Calendar.SUNDAY && dayOfWeek != Calendar.SATURDAY){
                list.add(sdf.format(cal.getTime()));
            }
            cal.add(Calendar.DATE,1);
        }
        return list;
    }

    public static int getWorkdayCountOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);

        int count = 0;
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=0;i<maxDay;i++){
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek != Calendar.SUNDAY && dayOfWeek != Calendar.SATURDAY){
                count++;
            }
            cal.add(Calendar.DATE,1);
        }

        return count;
    }

    /**
     * 获取本月1号到指定时间的工作日天数
     * @param date
     * @return
     */
    public static int getWorkdayCountOfMonthNow(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);

        int count = 0;
        int nowDay = cal2.get(Calendar.DAY_OF_MONTH);
        for(int i=0;i<nowDay;i++){
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek != Calendar.SUNDAY && dayOfWeek != Calendar.SATURDAY){
                count++;
            }
            cal.add(Calendar.DATE,1);
        }

        return count;
    }

    public static List<String> getHolidayOfMonth(Date date){
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);

        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=0;i<maxDay;i++){
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY){
                list.add(sdf.format(cal.getTime()));
            }
            cal.add(Calendar.DATE,1);
        }

        return list;
    }

    /**
     * 今日之前 本月休息日集合
     * @param date
     * @return
     */
    public static List<String> getHolidayOfMonthNow(Date date){
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);

        int nowDay = cal2.get(Calendar.DAY_OF_MONTH);
        for(int i=0;i<nowDay;i++){
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY){
                list.add(sdf.format(cal.getTime()));
            }
            cal.add(Calendar.DATE,1);
        }

        return list;
    }

    public static int getHolidayCountOfMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);

        int count = 0;
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=0;i<maxDay;i++){
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY){
                count++;
            }
            cal.add(Calendar.DATE,1);
        }

        return count;
    }

    public static int getHolidayCountOfMonthNow(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date);

        int count = 0;
        int nowDay = cal2.get(Calendar.DAY_OF_MONTH);
        for(int i=0;i<nowDay;i++){
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            if(dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY){
                count++;
            }
            cal.add(Calendar.DATE,1);
        }

        return count;
    }

    /**
     * //比较时间大小
     *
     * @param fromDate
     * @param toDate
     * @return
     */
    public static Long comperDateTwo(Date fromDate, Date toDate, String flag) {

        if (fromDate == null || toDate == null) {
            return 1L;
        }

        Long retInt = null;
        SimpleDateFormat f = null;
        if ("0".equals(flag)) {
            f = new SimpleDateFormat(FORMAT_YYYYMMDD);
        } else if ("1".equals(flag)) {
            f = new SimpleDateFormat(FORMAT_YYYYMMDDHH);
        }

        Long fromDateNumber = Long.parseLong(f.format(fromDate).toString());

        Long toDateNumber = Long.parseLong(f.format(toDate).toString());
        //System.out.println("fromDateNumber+==" + fromDateNumber + "toDateNumber==="+ toDateNumber);
        retInt = fromDateNumber.longValue() - toDateNumber.longValue();

        return retInt;
    }

    /**
     * 2个时间相差的月数
     * @return
     */
    public static int getDateSpace(String fromDate, String toDate) {

        int result = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        Calendar fromDateCd = Calendar.getInstance();
        Calendar toDateCd = Calendar.getInstance();

        try {
            fromDateCd.setTime(sdf.parse(fromDate));
            toDateCd.setTime(sdf.parse(toDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result = (toDateCd.get(Calendar.YEAR) - fromDateCd.get(Calendar.YEAR)) * 12 + (toDateCd.get(Calendar.MONTH) - fromDateCd.get(Calendar.MONTH));

        return result == 0 ? 1 : (Math.abs(result) + 1);

    }

    public static Date stampToDate(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        try {
            long lt = new Long(s);
            Date date = new Date(lt);
            return date;
        } catch (Throwable e) {

        }
        return null;

    }

}
