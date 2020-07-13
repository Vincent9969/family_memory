package com.family.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.family.config.Constants;
import com.family.user.mapper.HomeInfoMapper;
import com.family.user.mapper.UserInfoMapper;
import com.family.user.model.HomeInfo;
import com.family.user.model.UserInfo;
import com.family.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

/**
 * @author 杜飞龙
 * @date 2020年 03月20日 22:12:11
 * @jdk 1.8
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private HomeInfoMapper homeInfoMapper;

    @Override
    public void addUser(UserInfo user) {
        if(userInfoMapper.insert( user )<0){
            throw new RuntimeException( "添加用户失败" );
        }
    }

    @Override
    public void saveUser(UserInfo user) {
        user.setXgsj( new Date(  ) );
        if(userInfoMapper.updateByPrimaryKey( user )<0){
            throw new RuntimeException( "修改用户失败" );
        }
    }

    @Override
    public UserInfo getUserByOpenid(String openid) {
        if(StringUtils.isEmpty( openid )){
            return null;
        }
        Example entity=new Example( UserInfo.class);
        Example.Criteria c=entity.createCriteria();
        c.andEqualTo( "openid", openid);
        UserInfo user=userInfoMapper.selectOneByExample( entity );
        return user;
    }

    @Override
    public UserInfo getUserByUserXh(String xh) {
        return userInfoMapper.selectByPrimaryKey( xh );
    }

    @Override
    public HomeInfo getHomeByHomeXh(String xh) {
        return homeInfoMapper.selectByPrimaryKey( xh );
    }

    /**新增家庭组
     * @Description:
     * @Author: 杜飞龙
     * @Date: 2020/3/25
     * @param home:
     * @return: void
     **/
    @Override
    public void addHome(HomeInfo home) {
        if(homeInfoMapper.insert( home )<0){
            throw new RuntimeException( "添加家庭组失败" );
        }
    }

    @Override
    public void saveHome(HomeInfo home) {
        if(homeInfoMapper.updateByPrimaryKey( home )<0){
            throw new RuntimeException( "修改家庭组失败" );
        }
    }

    /**
     * @Description: 添加默认家庭组
     * @Author: 杜飞龙
     * @Date: 2020/3/25

     * @return: com.family.user.model.HomeInfo
     **/
    @Override
    public HomeInfo saveDefultHome() {
        HomeInfo home=new HomeInfo();
        home.setXh( IdUtil.objectId() );
        home.setHonename( Constants.HOME_NAME_DEFULT );
        home.setCjsj( new Date(  ) );
        home.setDesc( Constants.HOME_DESC_DEFULT );
        addHome(home);
        return home;
    }

}