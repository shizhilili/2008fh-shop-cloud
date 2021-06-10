package com.fh.shop.common;

public enum ResponseEnum {

    PAY_STOCK_IS_NULL(8100,"订单为空请重新选择"),
    PAY_STOCK_IS_ERROR(8000,"支付订单状态为错"),

    ORDER_SKU_STOCK_IS_ERROR(7100,"商品库存为空，请重新选择"),
    ORDER_CART_IS_EMPTY(7102,"购物车为空，请添加商品"),
    Order_IS_YES_NO_CLOSE(7102,"订单交易成功"),
    ORDER_RECIPIENT_IS_NULL(7103,"收件人为空，请添加收件人"),
    ORDER_STATUS_IS_ERROR(7104,"订单状态错误,订单取消"),

    CART_SKU_IS_NULL(3000,"没有改商品"),
    CART_SKU_STATUS_IS_NO(3001,"商品为上架"),
    CART_SKU_STOCK_NOT(3002,"库存不足"),
    CART_SKU_NULL(3003,"购物车为空，尽快购买"),
    CART_SKU_SO_MANNY(3004,"商品限购"),
    CART_SKU_110_error(3005,"非法操作"),
    CART_SKU_BATCH_DELETE_NO_SELECT(3006,"请选择要删除的商品"),

    SMS_CODE_ERROR(5000,"验证码发送失败"),
    SMS_CODE_SUCCESS(5003,"验证码已发送成功，请注意查收"),
    PHONE_ERROR(5001,"手机号不合法"),
    Email_ERROR(5002,"邮箱不合法"),



    MEMBER_PHONE_ISNULL(6001,"手机号不能为空哦"),
    MEMBER_PASSWORD_ISERROR(6002,"密码不一致"),
    MEMBER_CODE_ISERROR(6003,"验证码不一致"),
    MEMBER_CODE_IS_NOT(6030,"验证码过期"),
    MEMBER_URL_NOT_ERROR(6040,"链接出错"),

    MEMBER_CODE_IS_NULL(6009,"信息不能为空"),
    MEMBER_CODE_IS_NO_YES(6100,"验证码不一致"),
    MEMBER_EMAIL_IS_NOT(6101,"邮箱不存在"),
    MEMBER_IS_THIS(6004,"用户已存在"),
    MEMBER_IS_NULL(6000,"信息不能为空"),
    MEMBER_LOJIN_IS_NOLL(6005,"会员信息不能为空"),
    MEMBER_LOJIN_IS_EX(6006,"会员名不正确"),
    MEMBER_LOJIN_PASSWORD_ERROR(6007,"密码有误"),
    MEMBER_LOJIN_STATUS_ERROR(6008,"账号未激活"),

    MEMBER_OLD_PASSWORD_NULL(6200,"旧密码为空"),
    MEMBER_OLD_PASSWORD_ERROR(6300,"旧密码错误"),
    MEMBER_OLD_PASSWORD_YES(6301,"旧密码正确"),

    UPDATE_PASSWORD_IS_NULL(2100,"密码不能为空"),
    UPDATE_CONFRIM_PASSWORD_IS_NULL(2101,"确认密码不能为空"),

    MEMBER_REG_MEMBERNAME_IS_BULL(6010,"用户名不能为空"),
    MEMBER_REG_MEMBERNAME_IS_E(60100,"用户名名已被注册"),
    MEMBER_REG_PHONE_IS_BULL(6011,"手机号不能为空"),
    MEMBER_REG_PHONE_IS_EX(6012,"手机号已被注册"),
    MEMBER_REG_EMAIL_IS_NULL(6013,"邮箱不能为空"),
    MEMBER_REG_EMAIL_IS_EX(6014,"邮箱已存在"),
    MEMBER_REG_EMAIL_MEMBERNAME_IS_YES(6015,"邮箱与用户不匹配"),

    TOKEN_HEADER_IS_NULL(7000,"请求头信息为空"),
    TOKEN_HEADER_IS_ERROR(7005,"重复发送请求"),
    TOKEN_HEADER_IS_FULL(7001,"请求头信息不完整"),
    TOKEN_HEADER_ERROR(7003,"请求头信息异常"),
    TOKEN_IS_NULL(7002,"时间过长，请重新登录"),
    ID_IS_NULL(1011,"id为空"),
    USER_IDS_NULL(1006,"请选择要删除的选项"),
    CATE_ID_NULL(1005,"请选择要删除的"),
    SPEC_ERROR_NULL(1004,"规格不能为空"),
    USER_EMPTY(1000,"用户不能为空"),
    USER_NAME_ERROR(1002,"用户不存在"),
    PASSWORD_IS_ERROR(1003,"密码不正确"),
    PASSS_EMPTY(1001,"密码不能为空");


    private int code;

    private  String  message;


    private ResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
