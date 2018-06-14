package com.yefan.study.boot.client.enums;

public enum ResultCode {

    UNKOWN_ERROR(-1, "未知错误"),

    SUCCESS(0, "成功"),

    FAIL(1, "失败"),

    NO_AVABLIE_DATA(2, "暂无数据");

    private Integer code;
    private String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
