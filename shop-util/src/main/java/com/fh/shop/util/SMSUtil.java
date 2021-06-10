package com.fh.shop.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SMSUtil {

    public static final String URL="https://api.netease.im/sms/sendcode.action";
    public static final String APPKEY="408af5e9b0d302515e5d53fdc605e2e7";
    public static final String APPSECRET="131ccd64fd30";

    public static String send(String phone) {
        Map<String,String> headers=new HashMap<>();
        String nonce = UUID.randomUUID().toString();
        String curTime = System.currentTimeMillis() + "";
        String checkSum = CheckSumBuilder.getCheckSum(APPSECRET, nonce, curTime);
        headers.put("AppKey",APPKEY);
        headers.put("Nonce",nonce);
        headers.put("CurTime",curTime);
        headers.put("CheckSum",checkSum);
        Map<String,String> params=new HashMap<>();
        params.put("mobile",phone);
        String res = HttpClientUtil.sendPost(URL, headers, params);
        System.out.println(res);
        return res;
    }
}
