package com.fh.shop.util;

import java.math.BigDecimal;

public class BigDecimalUtil {

    public static BigDecimal mul(String b1,String b2){
        BigDecimal bigDecimal1 = new BigDecimal(b1);
        BigDecimal bigDecimal2 = new BigDecimal(b2);
        BigDecimal multiply = bigDecimal1.multiply(bigDecimal2);
        return multiply;
    }


    public static String add(String b1,String b2){
        BigDecimal bigDecimal1 = new BigDecimal(b1);
        BigDecimal bigDecimal2 = new BigDecimal(b2);
        BigDecimal add = bigDecimal1.add(bigDecimal2);
        return add.toString();
    }
}
