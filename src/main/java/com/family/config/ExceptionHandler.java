package com.family.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultJson runtimeExceptionHandler(HttpServletResponse rsp, Exception e) {
        e.printStackTrace();
        rsp.setHeader(Constants.HTTP_DATA_STATUS_NAME, Constants.HTTP_STATUS_ERROR);
        return new ResultJson(Constants.HTTP_STATUS_ERROR, e.getMessage());
    }
}
