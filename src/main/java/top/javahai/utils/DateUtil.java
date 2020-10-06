package top.javahai.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Hai
 * @date 2020/10/6 - 14:05
 */
public class DateUtil {
    private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentTime(Date date){
        return simpleDateFormat.format(date);
    }
}
