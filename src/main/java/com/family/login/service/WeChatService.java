package com.family.login.service;

import com.family.user.model.UserInfo;

import java.util.Map;

/**
 * @author 杜飞龙
 * @date 2020年 02月27日 21:44:34
 * @jdk 1.8
 */
public interface WeChatService {
    /**
     * @Description: 获取openid sessionkey
     * @Author: 杜飞龙
     * @Date: 2020/2/27
     * @param  data:
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     **/
    UserInfo getSession(Map<String,Object> data);

    /**
     * @Description: 获取getAccessToken凭证
     * @Author: 杜飞龙
     * @Date: 2020/2/27
     * @param openid:
     * @return: java.lang.String
     **/
    String getAccessToken(String openid);


    /**发送模板消息
     * @Description:
     * @Author: 杜飞龙
     * @Date: 2020/2/27
     * @param openid:
     * @return: void
     **/
    void sendTemplateMsg(String openid);
}