package com.fh.shop.common;

import java.io.Serializable;

public class TResponse implements Serializable {

    private int responseCode;

    private String responseMsg;

    private Object data;

    private TResponse(){

    }

    private TResponse(int responseCode,String responseMsg,Object data){
        this.responseCode=responseCode;
        this.responseMsg=responseMsg;
        this.data=data;
    }

    public static TResponse success(){
        return new TResponse(10,"success",null);
    }

    public static TResponse error(){
        return new TResponse(999,"error",null);
    }

    public static TResponse success(Object data){
        return new TResponse(10,"success",data);
    }

    public static TResponse error(int code,String msg){
        return new TResponse(code,msg,null);
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public Object getData() {
        return data;
    }
}
