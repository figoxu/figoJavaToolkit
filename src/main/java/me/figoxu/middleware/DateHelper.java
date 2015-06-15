package me.figoxu.middleware;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: figo.xu
 * Date: 12-11-5
 * Time: 下午2:08
 */
public class DateHelper {

    public static int parseDayInt(Date date){
        if(date==null){
            return 0;
        }
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dayString = format.format(date);
        return Integer.valueOf(dayString);
    }

    public static Date parseExtDate(String dayString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        dayString = dayString.replaceAll("T", " ");
        return format.parse(dayString);
    }



    /**
     * @param date
     * @return
     * @should test
     */
    public static Date getMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * @should test
     * 得到本月的最后一天
     */
    public static Date getMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * @param curMonthDay
     * @return
     * @should test
     */
    public static Date getNextMonth(Date curMonthDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curMonthDay);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date nextMonthDay = calendar.getTime();
        return nextMonthDay;
    }

    public static Date getNextYear(Date curMonthDay) {
        return getYearChange(curMonthDay,1);
    }

    public static Date getYearChange(Date curMonthDay, int yearCount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(curMonthDay);
        calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+yearCount);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date nextMonthDay = calendar.getTime();
        return nextMonthDay;
    }

    /**
     * @param date
     * @param dateDifferent
     * @return
     * @should test
     */
    public static Date getDateWithDateDifferent(Date date, int dateDifferent) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + dateDifferent);
        return calendar.getTime();
    }

    /**
     * @param date
     * @return
     * @should 获取明天
     */
    public static Date getTomorrow(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        return calendar.getTime();
    }

    /**
     * @param date
     * @return
     * @should 获取昨天
     */
    public static Date getYesterday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        return calendar.getTime();
    }
    public static Date getWeekBefore(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 7);
        return calendar.getTime();
    }

    public static Date getBeforeDay(Date date, int before) {
        Date date4Op = new Date(date.getTime());
        for (int i = 0; i < before; i++) {
            date4Op = getYesterday(date4Op);
        }
        return date4Op;
    }


    /**
     * 获取指定日期的零时零分零秒
     *
     * @param date
     * @return
     */
    public static Date getGivenDateStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     *获取指定日期的23时59分59秒
     * @param date
     * @return
     */
    public static Date getGivenDateEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * 获取指定时间的前一个整时
     *
     * @param date
     * @return
     */
    public static Date getGivenDateLastHourTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取yyyy-MM-dd的字符串  eg：2014-02-09
     */
    public static String getDateString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
    /**
     * 获取yyyy-MM-dd hh:mm:ss的字符串  eg：2014-02-09 17:33:22
     */
    public static String getTimeString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
    }

    public static void main(String[] args) {
        Date yesterday = getWeekBefore(new Date());
        System.out.println(yesterday);
    }
}
