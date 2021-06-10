package com.fh.shop.util;

public class
KeyUtil {

    public static String getksy(Long id){
        return "mamber:"+id;
    }

    public static String buildImageCodeKey(String id){
        return "image:code:"+id;
    }

    public static String ActionKey(String id){
        return "member:active:"+id;
    }


    public static String
    buildCartKey(Long id){
        return "Cart:"+id;
    }

    public static String buildTokenKey(String token ){
        return "token:"+token;
    }

}
