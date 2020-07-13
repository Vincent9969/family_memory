package com.family.user.service;

import com.family.user.model.HomeInfo;
import com.family.user.model.UserInfo;

/**
 * @author 杜飞龙
 * @date 2020年 03月20日 22:10:52
 * @jdk 1.8
 */
public interface UserService {

    public void addUser(UserInfo user);

    public void saveUser(UserInfo user);

    public UserInfo getUserByOpenid(String openid);

    public UserInfo getUserByUserXh(String xh);

    public HomeInfo getHomeByHomeXh(String xh);

    public void addHome(HomeInfo home);

    public void saveHome(HomeInfo home);

    public HomeInfo saveDefultHome();
}