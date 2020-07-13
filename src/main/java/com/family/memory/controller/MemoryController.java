package com.family.memory.controller;

import com.family.config.BaseController;
import com.family.config.ResultJson;
import com.family.memory.model.MemoryInfo;
import com.family.memory.model.MemoryPLInfo;
import com.family.memory.model.PlHfInfo;
import com.family.memory.service.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/memory")
public class MemoryController extends BaseController {

    @Autowired
    private MemoryService memoryService;


    @PostMapping("/queryMemoryPageList")
    public ResultJson queryMemoryPageList(@RequestParam(required = false) Integer pageNum,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) String homeXh) {

        return this.sucess( memoryService.queryMemoryPageList( homeXh,pageNum,pageSize ) );
    }

    @PostMapping("/queryMemoryPLPageList")
    public ResultJson queryMemoryPLPageList(@RequestParam(required = false) Integer pageNum,@RequestParam(required = false) Integer pageSize,@RequestParam(required = false) String memoryXh) {

        return this.sucess( memoryService.queryMemoryPLPageList( memoryXh,pageNum,pageSize ) );
    }

    @PostMapping("/addMemory")
    public ResultJson addMemory(HttpServletRequest request, @RequestParam("file")MultipartFile file) {
       String memoryxh=request.getParameter( "memoryxh" );
       String jynr=request.getParameter( "jynr" );
       String fbrxh=request.getParameter( "fbrxh" );
       return this.sucess( memoryService.addMemory( memoryxh,jynr,fbrxh,file ) );
    }

    @PostMapping("/delMemory")
    public ResultJson delMemory(@RequestParam(required = false) String memoryXh) {
        memoryService.delMemory( memoryXh );
        return this.sucess( "删除记忆成功" );
    }

    @PostMapping("/delMemoryPL")
    public ResultJson delMemoryPL(@RequestParam(required = false) String memoryPLXh) {
        memoryService.delMemoryPL( memoryPLXh );
        return this.sucess( "删除评论成功" );
    }

    @PostMapping("/addMemoryPL")
    public ResultJson addMemoryPL(@RequestBody(required = false) MemoryPLInfo memoryPLInfo) {
        return this.sucess( memoryService.addMemoryPL( memoryPLInfo ) );
    }

    @PostMapping("/addMemoryPLHF")
    public ResultJson addMemoryPLHF(@RequestBody(required = false) PlHfInfo plHfInfo) {
        return this.sucess( memoryService.addMemoryPLHF( plHfInfo ) );
    }

    /**
     * @Description:  取消点赞
     * @Author: 杜飞龙
     * @Date: 2020/3/29
     * @param plHfInfo:
     * @return: com.family.config.ResultJson
     **/
    @PostMapping("/delDz")
    public ResultJson delDz(@RequestParam(required = false) String memoryxh,@RequestParam(required = false)String plrxh) {
        memoryService.delDz( memoryxh ,plrxh);
        return this.sucess( );
    }


}