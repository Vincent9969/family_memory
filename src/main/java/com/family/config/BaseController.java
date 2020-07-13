package com.family.config;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {



    /**
     * 请求
     */
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    public ResultJson sucess() {
        return new ResultJson(Constants.HTTP_STATUS_SUCCESS, null);
    }

    public ResultJson sucess(String msg) {
        return new ResultJson(Constants.HTTP_STATUS_SUCCESS, msg);
    }

    public ResultJson sucess(Object data) {
        return new ResultJson(Constants.HTTP_STATUS_SUCCESS, data,"操作成功");
    }
    public ResultJson sucess(Object data,String msg) {
        return new ResultJson(Constants.HTTP_STATUS_SUCCESS, data,msg);
    }

    /**
     * 获取request
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
