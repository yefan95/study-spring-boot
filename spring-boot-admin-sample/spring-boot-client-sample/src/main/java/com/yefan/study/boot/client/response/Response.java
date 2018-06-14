package com.yefan.study.boot.client.response;


import com.yefan.study.boot.client.enums.ResultCode;

public class Response {

    public static Result ok() {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMsg());
        return result;
    }


    public static Result ok(String msg) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(msg);
        return result;
    }

    public static Result ok(String msg, Object data) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(msg);
        result.setData(data);
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setCode(ResultCode.FAIL.getCode());
        result.setMessage(ResultCode.FAIL.getMsg());
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ResultCode.FAIL.getCode());
        result.setMessage(msg);
        return result;
    }


}
