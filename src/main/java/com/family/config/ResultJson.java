package com.family.config;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResultJson {
    private String status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    public ResultJson(String status, Object data) {
        this.status = status;
        this.data = data;
    }
    public ResultJson(String status, Object data,String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public ResultJson(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
