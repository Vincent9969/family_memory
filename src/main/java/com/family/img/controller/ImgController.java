package com.family.img.controller;

import com.family.config.BaseController;
import com.family.config.ResultJson;
import com.family.img.service.ImgService;
import com.family.user.model.HomeInfo;
import com.family.user.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/img")
public class ImgController extends BaseController {

    @Autowired
    private ImgService imgService;


    @PostMapping("/queryImgGroupInfoPageList")
    public ResultJson queryImgGroupInfoPageList(@RequestParam(required = false) Integer pageNum,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) String homexh) {

        return this.sucess( imgService.queryImgGroupInfoPageList( homexh,pageNum,pageSize ) );
    }

    @PostMapping("/addImgGroup")
    public ResultJson addImgGroup(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String zpzmc=request.getParameter( "zpzmc" );
        String cjrxh=request.getParameter( "cjrxh" );
        String homexh=request.getParameter( "homexh" );
        return this.sucess( imgService.addImgGroup( zpzmc,cjrxh,homexh,file ));
    }

    @PostMapping("/addImg")
    public ResultJson addImg(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String zpzxh=request.getParameter( "zpzxh" );
        String imgdesc=request.getParameter( "imgdesc" );
        imgService.addImg( zpzxh,imgdesc,file );
        return this.sucess( );
    }

    @PostMapping("/queryImgGroupInfoAll")
    public ResultJson queryImgGroupInfoAll(@RequestParam("homexh") String homexh) {
        return this.sucess( imgService.queryImgGroupInfoAll( homexh ) );
    }

    @PostMapping("/getImgListByGroupXh")
    public ResultJson getImgListByGroupXh(@RequestParam("zpzxh") String zpzxh) {
        return this.sucess( imgService.getImgListByGroupXh( zpzxh ) );
    }

}