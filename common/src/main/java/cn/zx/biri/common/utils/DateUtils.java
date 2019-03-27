package cn.zx.biri.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: xiangXX
 * @Description:
 * @Date Created in 11:22 2019/3/23 0023
 */
public class DateUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String dateToString(Date date){
       return simpleDateFormat.format(date);
    }
}
