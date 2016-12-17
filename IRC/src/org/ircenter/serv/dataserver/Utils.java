package org.ircenter.serv.dataserver;

/**
 * User: Seledkov Kostyantyn
 * Date: 15.05.12
 * Time: 8:08
 */
public class Utils {
    private static String digit2(long x) {
        return x < 10 ? "0" + x : "" + x;
    }

    public static String toTime(long millis) {
        long seconds   = millis / 1000;
        long minutes   = seconds / 60;
        long hours     = minutes / 60;
        long days      = hours / 24;
        long hour      = hours % 24;
        long minute    = minutes % 60;
        long second    = seconds % 60;

        return digit2(days) + " " + digit2(hour) + ":" + digit2(minute) + ":" + digit2(second);
    }

    public static String floatToString2(float r) {
        double d = Math.pow(10, 2);

        r = (float) (Math.round(r * d) / d);
        return Float.toString(r);
    }

    public static String doubleToString2(double  r) {
        return String.format("%.2f", r).replace(',', '.');
    }

}
