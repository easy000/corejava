package com.easy;

import com.easy.dto.Student;
import com.segi.uhomecp.utils.DateTimeUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 包:       com.easy
 * 类名称:    Test
 * 类描述:
 * 创建人:    wangxiong
 * 创建时间:  2019/8/13 18:36
 * 修改人:    Administrator
 * 修改时间:  2019/8/13 18:36
 * 修改备注:  [说明本次修改内容]
 * 版本:      v1.0
 */
public class Test {
    public static void main(String[] args) {
//        InvocationHandler invoke = (s) -> {
//            System.out.println("1");
//            return 1;
//        };
//        invoke.invoke(1);
//
//        Student stu = new Student();
//        Optional<Student> opt = Optional.ofNullable(stu);
//        if (opt.isPresent()) {};
//        opt.ifPresent(obj -> System.out.println(obj.getAge()));
//        opt.map(obj->obj.getAge() == 0);
        System.out.println(new BigDecimal(0.0).compareTo(BigDecimal.ZERO));
        computeExpDate("2021-09-06", 3, "0");
    }

    public static String computeExpDate(String oldExpDate, Integer payMonths, String isToEndMonth) {
        Date newExpDate = new Date();
        if(StringUtils.isEmpty(oldExpDate)) {
            // 充值前过期时间为空时, 默认为当前日期的前一天
            oldExpDate = DateUtils.formatDateToStringYYMMDD(DateUtils.addDateDay(new Date(), -1));
        } else {
            // 旧失效日期做统一的格式转换
            oldExpDate = StringUtils.replace(oldExpDate, "-", "");
            if (oldExpDate.length() < 8) {
                return null;
            }
            oldExpDate = oldExpDate.substring(0, 8);
            oldExpDate = DateUtils.formatDateToStringYYMMDD(DateUtils.parseStringToDateYYYYMMDD(oldExpDate));
        }
        // 旧过期日期
        Date expireDate = DateUtils.parseStringToDateYYMMDD(oldExpDate);
        // 默认为非自然月充值，失效时间为旧过期日期的同号
        newExpDate = DateUtils.addDateMonth(expireDate, payMonths);

        // 新失效时间的月底日期
        Date lastDayOfNewExpDate = DateUtils.lastDayOfMonth(newExpDate);
        if (DateUtils.isLastDayOfMonth(expireDate)) {
            // 旧过期日期是月底最后一天时, 新过期日期要计算失效时间到月底
            newExpDate = lastDayOfNewExpDate;
        } else {
            // 旧过期日期不是月底最后一天时，则要处理跨2月份和大月30号到小月30号时的特殊情况

            String newExpDateStr = DateUtils.formatDateToStringYY_MM_DD(newExpDate);
            // 充值的所有月份列表
            List<String> yearMonthList = getYearMonthBetween(oldExpDate, newExpDateStr);
            if (expireDate.getDate() == 30 && hasMinMonthBetween(yearMonthList)) {
                // 大月的30号为旧过期时间时，充值时有小月的，则新失效日期到月底最后一天
                newExpDate = lastDayOfNewExpDate;
            } else {
                // 处理跨2月份的情况
                // 获取新旧失效日期之间的2月份集合
                List<String> febMonthList  = getFebBetween(yearMonthList);
                if (CollectionUtils.isNotEmpty(febMonthList)) {
                    // 计算新失效日期时跨2月份的特殊处理
                    // 判断旧失效日期是否和充值的2月份有同号（或者是充值的2月份的最后一天）, 有返回true
                    boolean flag = oldExpDateHasSameDayOfMonthFeb(febMonthList, oldExpDate);
                    if (!flag) {
                        // 旧过期日期和充值路过的2月没有同号时，则新过期日期要计算失效时间到月底
                        newExpDate = lastDayOfNewExpDate;
                    }
                }
            }
        }

        String newExpDateStr = DateUtils.formatDateToStringYY_MM_DD(newExpDate);
        return newExpDateStr;
    }

    /**
     * @title getFebBetween
     * @description 获取2个日期之间的2月份集合
     * @author zhaoqing@segemail.com
     * @date 2020/2/26 12:50
     * @param yearMonthList
     * @return 包含2月份返回true, 否则返回false
     */
    private static List<String> getFebBetween(List<String> yearMonthList) {
        List<String> febMonthList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(yearMonthList)) {
            for (String yearMonth : yearMonthList) {
                String[] tempMonth = yearMonth.split("-");
                if ("02".equals(tempMonth[1])) {
                    febMonthList.add(yearMonth);
                }
            }
        }
        return febMonthList;
    }
    /**
     * @title oldExpDateHasSameDayOfMonthFeb
     * @description 判断旧失效日期是否和充值的2月份有同号（或者是充值的2月份的最后一天）
     * @author zhaoqing@segemail.com
     * @date 2020/2/26 15:05
     * @param febMonthList
     * @param oldExpDate
     * @return 有同号返回true, 否则返回false
     */
    private static boolean oldExpDateHasSameDayOfMonthFeb(List<String> febMonthList, String oldExpDate) {
        int oldExpDateDay = DateTimeUtils.parseDate(oldExpDate).getDayOfMonth();
        for (String febMonth : febMonthList) {
            Date febMonthLastDate = DateUtils.parseStringToDateYYMM(febMonth);
            int febMaxDay = DateUtils.getDaysOfMonth(febMonthLastDate);
            if (oldExpDateDay >= febMaxDay) {
                return false;
            }
        }
        return true;
    }

    /**
     * @title hasMinMonthBetween
     * @description 判断充值的月份中是否包含小月(只有30天的月份)
     * @author zhaoqing@segemail.com
     * @date 2020/2/27 12:16
     * @param yearMonthList
     * @return 包含小月月份返回true，否则返回false
     */
    private static boolean hasMinMonthBetween(List<String> yearMonthList) {
        // 小月月份列表
        List<String> minMonthList = Arrays.asList("04", "06", "09", "11");
        if (CollectionUtils.isNotEmpty(yearMonthList)) {
            for (String yearMonth : yearMonthList) {
                String[] tempMonth = yearMonth.split("-");
                if (minMonthList.contains(tempMonth[1])) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * @title getYearMonthBetween
     * @description 获取2个日期之间的所有月份（包含开始和结束日期的月份）
     * @author zhaoqing@segemail.com
     * @date 2020/2/26 12:30
     * @param minDate 开始日期（格式：yyyy-MM-dd）
     * @param maxDate 结束日期（格式：yyyy-MM-dd）
     * @return 返回数据为yyyy-MM格式的月份list
     */
    public static List<String> getYearMonthBetween(String minDate, String maxDate) {
        List<String> result = new ArrayList<String>();
        try {
            // 格式化为年月
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();

            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                result.add(sdf.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }
        } catch (ParseException e) {
        }
        return result;
    }
}
