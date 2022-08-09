package com.sastit.joinus.model.response;

public class Response<T> {
    private String status;
    private String msg;
    private Integer code;
    private T data;

    public Response(String status, String msg, Integer code, T data) {
        this.status = status;
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static <T extends ResponseData> Response<T> success(T data) {
        return new Response<>("ok", "请求成功", 20000, data);
    }

    public static <T extends ResponseData> Response<T> failure(T data, String msg, Integer code) {
        return new Response<>("fail", msg, code, data);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
