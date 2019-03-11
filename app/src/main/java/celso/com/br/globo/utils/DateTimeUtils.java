package celso.com.br.globo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeUtils {

    public static Calendar convertGregorianToCalendar(GregorianCalendar gregorian) {

        if (gregorian == null)
            return null;

        Calendar ca = Calendar.getInstance(gregorian.getTimeZone());
        ca.set(Calendar.DAY_OF_MONTH, gregorian.get(Calendar.DAY_OF_MONTH));
        ca.set(Calendar.MONTH, gregorian.get(Calendar.MONTH));
        ca.set(Calendar.YEAR, gregorian.get(Calendar.YEAR));

        return ca;
    }

    public static Calendar clearTimeCalendar(Calendar date) {
        if (date != null) {
            date.set(Calendar.HOUR_OF_DAY, 0);
            date.set(Calendar.MINUTE, 0);
            date.set(Calendar.SECOND, 0);
            date.set(Calendar.MILLISECOND, 0);
        }

        return date;
    }

    public static Calendar schedulesInAccordance(Calendar date) {
        if (date != null) {
            date.set(Calendar.HOUR_OF_DAY, 12);
            date.set(Calendar.MINUTE, 0);
            date.set(Calendar.SECOND, 0);
            date.set(Calendar.MILLISECOND, 0);
        }
        return date;
    }

    public enum Pattern {
        DATE_FULL_ENGLISH_WITHOUT_SLASH("yyyy-MM-dd"),
        DATE_FULL_ENGLISH("yyyy/MM/dd"),
        DATE_FULL_PORTUGUESE("dd/MM/yyyy"),
        DATETIME_FULL_ENGLISH_WITHOUT_SLASH("yyyy-MM-dd HH:mm:ss"),
        DATETIME_LONG_PORTUGUESE_HHMM("dd/MM/yyyy - HH:mm"),
        DATETIME_FULL_PORTUGUESE("dd/MM/yyyy HH:mm:ss"),
        //DATETIME_FULL("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DATETIME_FULL("yyyy-MM-dd'T'HH:mm:ssZ");


        private final String text;

        Pattern(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static Calendar convertToCalendar(String date, Pattern pattern) {
        Calendar calendar = Calendar.getInstance();

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern.toString());
            calendar.setTime(simpleDateFormat.parse(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return calendar;
    }

    public static Date convertToDate(String date, Pattern pattern) {

        if (date == null || date.isEmpty())
            return new Date();

        DateFormat format = new SimpleDateFormat(pattern.toString());
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String applyDateTimeWithHourMinute(Date date, boolean addTime) {
        String dateFormatted = null;

        if (date == null)
            return "";

        SimpleDateFormat spd = new SimpleDateFormat(Pattern.DATETIME_LONG_PORTUGUESE_HHMM.toString());
        spd.setTimeZone(TimeZone.getTimeZone("GMT"));;
        dateFormatted = spd.format(date.getTime());

        if (!addTime)
            dateFormatted = dateFormatted.split(" ")[0];

        return dateFormatted;
    }

    public static String applyDateTimeWithHourMinute(Calendar date, boolean addTime) {
        String dateFormatted = null;

        if (date == null)
            return "";

        SimpleDateFormat spd = new SimpleDateFormat(Pattern.DATETIME_LONG_PORTUGUESE_HHMM.toString());
        spd.setTimeZone(date.getTimeZone());
        dateFormatted = spd.format(date.getTime());

        if (!addTime)
            dateFormatted = dateFormatted.split(" ")[0];

        return dateFormatted;
    }

    public static String getDateSystem() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(Pattern.DATETIME_LONG_PORTUGUESE_HHMM.toString());

        return sdf.format(date);
    }

    public static String applyDatePattern(Calendar calendar) {
        return applyPattern(calendar, Pattern.DATE_FULL_PORTUGUESE);
    }

    public static String applyPattern(Calendar calendar, Pattern pattern) {
        String formattedDate = "";

        if (calendar != null) {
            formattedDate = applyPattern(calendar.getTime(), pattern);
        }

        return formattedDate;
    }

    public static String applyDatePattern(Date date) {
        return applyPattern(date, Pattern.DATE_FULL_PORTUGUESE);
    }

    public static String applyPattern(Date date, Pattern pattern) {
        String formattedDate = "";

        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern.toString(), LocaleUtil.getDefaultLocale());
            formattedDate = sdf.format(date);
        }

        return formattedDate;
    }

    public static String applyDateTimePattern(Date date) {
        return applyPattern(date, Pattern.DATETIME_FULL_PORTUGUESE);
    }

    public static String applyDateTimePattern(Calendar calendar) {
        return applyPattern(calendar, Pattern.DATETIME_FULL_PORTUGUESE);
    }

    public static Calendar getNewDate(Calendar calendar) {
        Calendar date = Calendar.getInstance();
        date = DateTimeUtils.clearTimeCalendar(date);

        if (calendar != null) {
            date.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
            date.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
            date.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
        }

        return date;
    }


}
