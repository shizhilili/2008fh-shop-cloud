package com.fh.shop.common;

public final class SystemConstant {

    public  static final  String  CURR_USER="uesrDB";

    public  static final  String  LOGIN="/login.jsp";

    public  static final  String UPLOAD="/upload/";

    public  static final  String  UPLOAD_EXCEL="e:/upload/";

    public  static final  String  CODE="code";

    public  static final  String  EMAILUUID="statusUrl";

    public static final String  CARTSTATUS="0";

    public  static final  int   ACTION=-1;

    public  static final  int InACTION=1;

    public  static final  int STATUS=1;

    public  static final  String CART_JSON_FIELD="cartVo";

    public  static final  String CART_COUNT_FIELD="totalCount";

    public static final int buildOrder=0;

    public static final int wechat=0;

    public interface  ORDER_STATUS{
        int WAIT_PAY = 0;
        int PAY_SUCCESS =10;
        int TRADE_CLOSE =40;
    }

    public interface MESSAGE_LOG_STATUS{

        int SENDING = 0;       //投递中
        int SEND_SUCCESS = 1;  //投递成功
        int SEND_FALL = 2;      //投递失败
        int CONSUME_SUCCESS = 3; //消费成功
        int EXCHANGE_QUEUE_FAIL = 4; //e->q 交换机到消息队列失败

    }

    public static  final int RETRY_COUNT =3;



}
