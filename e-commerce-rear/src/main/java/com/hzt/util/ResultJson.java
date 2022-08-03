package com.hzt.util;

import lombok.Getter;

@Getter
public class ResultJson<T> {
    private Integer code;
   
    private String message;
    private T obj;
     //返回码有特定的值，可以设定枚举类,不可以随便传值哈
     private ResultJson(ResultCode resultCode, T obj) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.obj = obj;
    }
    private ResultJson(ResultCode resultCode, T obj,String message) {
        this.code = resultCode.getCode();
        this.message = message;
        this.obj = obj;
    }
    //写一个调用方法,相当于构造方法的调用
    public static <T> ResultJson<T> getInstance(ResultCode resultCode, T obj) {
        return new ResultJson<>(resultCode, obj);
    }
    //重载方法调用，可以接收前端传过来的其他信息
    public static <T> ResultJson<T> getInstance(ResultCode resultCode, T obj,String message) {
        return new ResultJson<>(resultCode, obj, message);
    }
    //我们更方便一点
    public static <T> ResultJson<T> success(T obj) {
        return getInstance(ResultCode.SUCCESS, obj);
    }
    public static <T> ResultJson<T> success(T obj,String message) {
        return getInstance(ResultCode.SUCCESS, obj,message);
    }
    public static <T> ResultJson<T> error(T obj) {
        return getInstance(ResultCode.ERROR, obj);
    }
    public static <T> ResultJson<T> error(T obj,String message) {
        return getInstance(ResultCode.ERROR, obj,message);
    }
}
