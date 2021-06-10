package com.fh.shop.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String FULL_YEAR="yyyy-MM-dd HH:mm:ss";
    public static String Y_M_D="yyyy-MM-dd";
    public static String Y_M="yyyy-MM";

    public static String date2str(Date date,String pattern){
        if (date ==null){
            return "";
        }
        SimpleDateFormat sim=new SimpleDateFormat(pattern);
        return sim.format(date);
    }

    public static Date str2Date(String date,String pattern){
        if (StringUtils.isEmpty(date)){
            return null;
        }
        SimpleDateFormat sim=new SimpleDateFormat(pattern);
        try {
            return sim.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
