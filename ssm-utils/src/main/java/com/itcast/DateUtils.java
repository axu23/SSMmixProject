package com.itcast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils  {

    /**
     * 将日期转为字符串
     * @param date
     * @param dstr
     * @return
     */
    public static String date2String(Date date,String dstr){
        SimpleDateFormat dateFormat = new SimpleDateFormat(dstr);
        String format = dateFormat.format(date);
        return format;
    }

    /**
     * 将字符串转为日期
     * @param dstr
     * @param date
     * @return
     */
    public static Date string2Date(String tyStr,String date) throws ParseException {
        SimpleDateFormat date1=new SimpleDateFormat(tyStr);
        Date parse = date1.parse(date);
        return parse;
    }
}
