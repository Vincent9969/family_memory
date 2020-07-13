package com.family.user.controller;

import com.family.config.BaseController;
import com.family.config.ResultJson;
import com.family.login.service.WeChatService;
import com.family.user.model.HomeInfo;
import com.family.user.model.UserInfo;
import com.family.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    @PostMapping("/getUser")
    public ResultJson getUser(@RequestParam String userXh) {

        return this.sucess( userService.getUserByUserXh( userXh ) );
    }

    @PostMapping("/getHome")
    public ResultJson getHome(@RequestParam String homeXh) {

        return this.sucess( userService.getHomeByHomeXh( homeXh ) );
    }

    @PostMapping("/saveUser")
    public ResultJson saveUser(@RequestBody UserInfo user) {
        userService.saveUser( user );
        return this.sucess(user);
    }

    @PostMapping("/saveHome")
    public ResultJson saveHome(@RequestBody HomeInfo homeInfo) {
        userService.saveHome( homeInfo );
        return this.sucess(homeInfo);
    }

}