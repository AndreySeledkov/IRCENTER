package org.ircenter.server.util;


import java.awt.image.BufferedImage;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public final class TextUtil {

    public static final int[] DAY_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final Map<Locale, String[]> mapMonthNames = new HashMap<Locale, String[]>();

    private static TimeZone serverTimeZone = TimeZone.getDefault();



    public static TimeZone getTimeZone() {
        return serverTimeZone;
    }

    public static void setTimeZone(TimeZone zone) {
        serverTimeZone = zone;
    }

    public static boolean isLeapYear(int year) {
        int res = (4 - year % 4) / 4 - (100 - year % 100) / 100 + (400 - year % 400) / 400;
        return res == 1;
    }

    public static BufferedImage normalizeImageSize(BufferedImage image) {
        return ImageUtils.normalizeImageSize(image, 240);
    }

    public static int trimstr2int(String str, int defaultValue) {
        int res = defaultValue;
        try {
            res = Integer.parseInt(str.trim());
        } catch (Exception ex) {
        }
        return res;
    }

    public static int str2int(String str, int defaultValue) {
        int res = defaultValue;
        try {
            res = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
        }
        return res;
    }

    public static long str2long(String str, long defaultValue) {
        long res = defaultValue;
        try {
            res = Long.parseLong(str);
        } catch (NumberFormatException ex) {
        }
        return res;
    }

    public static Date string2Date(String date, Calendar calendar) {
        return parseDate(date, calendar, THREAD_LOCAL_DATE);
    }

    public static Date string2DateTime(String date, Calendar calendar) {
        return parseDate(date, calendar, THREAD_LOCAL_DATE_TIME);
    }

    private static Date parseDate(String date, Calendar calendar, ThreadLocal<FormatterData> threadLocalFormatData) {
        FormatterData formatterData = threadLocalFormatData.get();
        if (calendar == null) {
            calendar = formatterData.defaultCalendar;
        }

        SimpleDateFormat format = formatterData.format;
        format.setCalendar(calendar);
        try {
            return format.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static Calendar createDefaultCalendar() {
        return Calendar.getInstance(serverTimeZone);
    }

    private static class FormatterData {
        private final Calendar defaultCalendar;
        private final SimpleDateFormat format;

        public FormatterData(Calendar defaultCalendar, SimpleDateFormat format) {
            this.defaultCalendar = defaultCalendar;
            this.format = format;
        }
    }

    private static final ThreadLocal<FormatterData> THREAD_LOCAL_TIME = new ThreadLocal<FormatterData>() {
        @Override
        protected FormatterData initialValue() {
            return new FormatterData(createDefaultCalendar(), new SimpleDateFormat("HH:mm"));
        }};
    private static final ThreadLocal<FormatterData> THREAD_LOCAL_DATE = new ThreadLocal<FormatterData>() {
        @Override
        protected FormatterData initialValue() {
            return new FormatterData(createDefaultCalendar(), new SimpleDateFormat("yyyy-MM-dd"));
        }};
    private static final ThreadLocal<FormatterData> THREAD_LOCAL_DATE_TIME = new ThreadLocal<FormatterData>(){
        @Override
        protected FormatterData initialValue() {
            return new FormatterData(createDefaultCalendar(), new SimpleDateFormat("yyyy-MM-dd HH:mm"));
        }};

    private static String formatDate(Date date, Calendar calendar, ThreadLocal<FormatterData> threadLocalFormatData) {
        if (date == null) {
            return "";
        }

        FormatterData formatterData = threadLocalFormatData.get();

        if (calendar == null)
            calendar = formatterData.defaultCalendar;

        SimpleDateFormat format = formatterData.format;
        format.setCalendar(calendar);

        return format.format(date);
    }

    /**
     * "yyyy-MM-dd"
     * @param date
     * @param calendar
     * @return
     */
    public  static String dateToString(Date date, Calendar calendar) {
        return formatDate(date, calendar, THREAD_LOCAL_DATE);
    }

    public static String datetimeToString(java.util.Date date, Calendar calendar) {
        return formatDate(date, calendar, THREAD_LOCAL_DATE_TIME);
    }

    public  static String timeToString(java.util.Date date, Calendar calendar) {
        return formatDate(date, calendar, THREAD_LOCAL_TIME);
    }

    public static String floatToString2(float r) {
        return String.format("%.2f", r).replace(',', '.');
//        return floatToString(r, 2);
    }

    public static String replaceHTMLtext(String text) {
        if (text == null) {
            return null;
        }
        text = replaceAll(text, "&", "&amp;");
        text = replaceAll(text, "<", "&lt;");
        text = replaceAll(text, ">", "&gt;");
        return text;
    }

    public static String replaceAll(String str, String pattern, String replacement) {
        if (pattern.isEmpty()) {
            return str;
        }

        int s = 0;
        int e = str.indexOf(pattern, s);
        if (e < 0) {
            return str;
        }

        StringBuilder result = new StringBuilder(str.length() + replacement.length());
        while (e >= 0) {
            result.append(str.substring(s, e));
            result.append(replacement);
            s = e + pattern.length();
            e = str.indexOf(pattern, s);
        }

        result.append(str.substring(s));
        return result.toString();
    }

    public static StringBuilder replaceAll(StringBuilder str, String pattern, String replacement) {
        int len = pattern.length();
        if (len == 0) {
            return str;
        }
        int rlen = replacement.length();
        int s = str.indexOf(pattern, 0);
        while (s >= 0) {
            str.replace(s, s + len, replacement);
            s = str.indexOf(pattern, s + rlen);
        }
        return str;
    }

    public static String removeLineBreaks(String s) {
        if (s == null) {
            return null;
        }

        s = replaceAll(s, "\r\n", " ");
        s = replaceAll(s, "\n\r", " ");
        s = replaceAll(s, "\r", " ");
        s = replaceAll(s, "\n", " ");

        return s.trim();
    }

    private static final Calendar calendar = Calendar.getInstance();
    public static java.util.Date previousDate(int days) {
        synchronized (calendar) {
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.DAY_OF_YEAR, -days);
            return calendar.getTime();
        }
    }

    private static final long TIME_20090101;
    private static final long MILLIS_IN_DAY;
    static
    {
        Calendar c = Calendar.getInstance();
        c.set(2009, 0, 1, 0, 0);
        TIME_20090101 = c.getTimeInMillis();
        MILLIS_IN_DAY = TimeUnit.DAYS.toMillis(1);
    }

    public static long diffNowSince20090101() {
        long t1 = System.currentTimeMillis();
        long d = (t1 - TIME_20090101) / MILLIS_IN_DAY;
        return d;
    }

    public static long diffSince20090101(Date date) {
        long t1 = date.getTime();
        long d = (t1 - TIME_20090101) / MILLIS_IN_DAY;
        return d;
    }

    public static Date now() {
        return new Date(System.currentTimeMillis());
    }


//    /**
//     * "country, region, city"
//     * @param buf
//     * @param city
//     * @return buf
//     */
//    public static StringBuilder formatPlaceFull(StringBuilder buf, City city) {
//        if (city.getCityId() == 0) {
//            return buf.append(MUtil.replaceHTMLtext(city.getCityName()));
//        }
//        Region region = city.getRegion();
//        Country country = region.getCountry();
//
//        if (country.getRegions().size() > 1) {
//            return buf.append(country.getShortNameOrFull()).append(", ").append(region.getShortNameOrFull()).append(", ").append(city.getCityName());
//        } else {
//            return formatPlaceShort(buf, city);
//        }
//    }
//
//    /**
//     * "country, city"
//     * @param buf
//     * @param city
//     * @return buf
//     */
//    public static StringBuilder formatPlaceShort(StringBuilder buf, City city) {
//        if (city.getCityId() == 0) {
//            return buf.append(MUtil.replaceHTMLtext(city.getCityName()));
//        }
//        Region region = city.getRegion();
//        Country country = region.getCountry();
//
//        return buf.append(country.getShortNameOrFull()).append(", ").append(city.getCityName());
//    }
//
//    /**
//     * "country, city"
//     * @param buf
//     * @param city
//     * @return buf
//     */
//    public static StringBuffer formatPlaceShort(StringBuffer buf, City city) {
//        Region region = city.getRegion();
//        Country country = region.getCountry();
//
//        return buf.append(country.getShortNameOrFull()).append(", ").append(city.getCityName());
//    }

    public static synchronized String[] getMonthNames(Locale locale) {
        String r[] = mapMonthNames.get(locale);

        if (r == null) {
            DateFormatSymbols dfs = DateFormatSymbols.getInstance(locale);
            if (dfs == null) {
                dfs = DateFormatSymbols.getInstance(Locale.getDefault());
            }

            r = dfs.getMonths();

            mapMonthNames.put(locale, r);
        }

        return r;
    }

    private static String digit2(long x) {
        return (x < 10) ? "0" + x : "" + x;
    }

    public static String toTime(long milis) {
        long seconds   = milis / 1000;
        long minutes   = seconds / 60;
        long hours     = minutes / 60;
        long days      = hours / 24;
        long hour      = hours % 24;
        long minute    = minutes % 60;
        long second    = seconds % 60;

        return digit2(days) + " " + digit2(hour) + ":" + digit2(minute) + ":" + digit2(second);
    }



    /**
     * inclusive, [start..end)
     * @param <E>
     * @param list
     * @param start
     * @param end
     * @return
     */
    public static <E> List<E> getSafeSubList(List<E> list, int start, int end) {
        if (start < 0) {
            start = 0;
        }
        if (end > list.size()) {
            end = list.size();
        }
        if (start > end) {
            start = end;
        }
        return list.subList(start, end);
    }

    /**
     *
     * @param <E>
     * @param list
     * @param page
     * @param perPage
     * @return getSafeSubList(list, page * perPage, (page + 1) * perPage);
     */
    public static <E> List<E> getSafePage(List<E> list, int page, int perPage) {
        return getSafeSubList(list, page * perPage, (page + 1) * perPage);
    }

    public static boolean stringEquals(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        } else if (s1 != null && s2 != null) {
            return s1.equals(s2);
        } else {
            return false;
        }
    }

    public static boolean objectEquals(Object o1, Object o2) {
        if (o1 == null && o2 == null) {
            return true;
        } else if (o1 != null && o2 != null) {
            return o1.equals(o2);
        } else {
            return false;
        }
    }


    public static boolean isBirthday(Calendar calendar, Date birthday, int daysAfterNow) {
        calendar.setTime(birthday);
        int m1 = calendar.get(Calendar.MONTH);
        int d1 = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DAY_OF_YEAR, daysAfterNow);
        int m2 = calendar.get(Calendar.MONTH);
        int d2 = calendar.get(Calendar.DAY_OF_MONTH);

        return (d1 == d2) && (m1 == m2);
    }

    public static int getAge(Date birthday) {
        Calendar currentCalendar = Calendar.getInstance();
        Calendar userBirth = Calendar.getInstance();

        userBirth.setTime(birthday);

        currentCalendar.add(Calendar.DAY_OF_MONTH, -userBirth.get(Calendar.DAY_OF_MONTH));
        currentCalendar.add(Calendar.MONTH, -userBirth.get(Calendar.MONTH));
        currentCalendar.add(Calendar.YEAR, -userBirth.get(Calendar.YEAR));

        return currentCalendar.get(Calendar.YEAR);
    }

    public static int compareDouble(double a, double b) {
        return a > b ? 1 : a < b ? -1 : 0;
    }

    /**
     * @return
     * <br>+1, if a &gt; b,
     * <br>-1, if a &lt; b,
     * <br> 0, if a==b
     */
    public static int compareLong(long a, long b) {
        return a > b ? 1 : a < b ? -1 : 0;
    }

    public static int compareString(String a, String b) {
        if (a != null) {
            return a.compareTo(b);
        } else if (b != null) {
            return b.compareTo(a);
        }
        return 0;
    }

    public static String md5(byte[] bytes) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(bytes);
            String hash = new BigInteger(1, digest).toString(16).toLowerCase();
            while(hash.length() < 32 ) {
                hash = "0" + hash;
            }
            return hash;
        } catch (NoSuchAlgorithmException ex) {
            return "";
        }
    }

    public static Date validateDate(Date date, int minYear, int maxYear) {
        Calendar c = createDefaultCalendar();
        c.setTime(date);
        int y = c.get(Calendar.YEAR) ;
        if (c.get(Calendar.YEAR) < minYear) {
            c.set(Calendar.YEAR, minYear);
        } else if (c.get(Calendar.YEAR) > maxYear) {
            c.set(Calendar.YEAR, maxYear);
        }
        return c.getTime();
    }

    public static int getPageCount(int items, int perPage)  {
        return items / perPage + (items % perPage != 0 ? 1 : 0);
    }

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        lat1 = lat1 * Math.PI / 180;
        lng1 = lng1 * Math.PI / 180;
        lat2 = lat2 * Math.PI / 180;
        lng2 = lng2 * Math.PI / 180;
        double res = Math.sin((lat2 - lat1) / 2) * Math.sin((lat2 - lat1) / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin((lng2 - lng1) / 2) * Math.sin((lng2 - lng1) / 2);
        return 2 * Math.atan2(Math.sqrt(res), Math.sqrt(1 - res)) * 6372795;
    }
}