package com.family.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.family.config.BaseController;
import com.family.config.ResultJson;
import com.family.login.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@RestController
@RequestMapping("/")
public class WeChatLoginController extends BaseController {

    @Autowired
    private WeChatService weChatService;

    @PostMapping("/login")
    public ResultJson login(@RequestBody Map<String,Object> data) {

        return this.sucess( weChatService.getSession( data ) );
    }


    @GetMapping("/showImage/{url}")
    public void showImage(@PathVariable("url") String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {
        //读取本地图片输入流
//        String path="/usr/imageswall/img/";
        String path="D://";
        FileInputStream inputStream = new FileInputStream(path+url+".jpg");
        int i = inputStream.available();
        //byte数组用于存放图片字节数据
        byte[] buff = new byte[i];
        inputStream.read(buff);
        //记得关闭输入流
        inputStream.close();
        //设置发送到客户端的响应内容类型
        response.setContentType("image/*");
        OutputStream out = response.getOutputStream();
        out.write(buff);
        //关闭响应输出流
        out.close();
    }

}