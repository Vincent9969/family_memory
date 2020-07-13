package com.family.login.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.family.config.Constants;
import com.family.config.DateAndTimeUtil;
import com.family.login.mapper.AccessTokenMapper;
import com.family.login.model.AccessToken;
import com.family.login.service.WeChatService;
import com.family.memory.service.MemoryService;
import com.family.user.mapper.UserInfoMapper;
import com.family.user.model.HomeInfo;
import com.family.user.model.UserInfo;
import com.family.user.service.UserService;
import org.apache.catalina.User;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 杜飞龙
 * @date 2020年 02月27日 21:45:00
 * @jdk 1.8
 */
@Service
public class WeChatServiceImpl implements WeChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger( WeChatServiceImpl.class );

    @Value("${wx.APPID}")
    public String APPID;

    @Value("${wx.SECRET}")
    public String APPSECRET;

    @Autowired
    private UserService userService;

    @Autowired
    private MemoryService memoryService;

    @Autowired
    private AccessTokenMapper accessTokenMapper;

    /**
     * @param data: avatarUrl: "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTK4CicE4eBicL2YnvkGky9rmqplnecTuDdsv3KR2NjlicwPH3lYn1DV1ib7kLXH4Cjbz6icJIZoZPTu8ww/132"
     *              city: "Bristol"
     *              code: "071QhZwf2KUIzF0tl3wf2WMgxf2QhZwu"
     *              country: "China"
     *              gender: 1
     *              language: "zh_CN"
     *              nickName: "大龙子"
     *              province: "Hubei"
     *              homexh:家庭组号
     * @Description:
     * @Author: 杜飞龙
     * @Date: 2020/3/24
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     **/
    @Override
    public UserInfo getSession(Map<String, Object> data) {
        if (CollectionUtil.isEmpty( data )) {
            return null;
        }
        //微信的接口
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID +
                "&secret=" + APPSECRET + "&js_code=" + data.get( "code" ) + "&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        //进行网络请求,访问url接口
        ResponseEntity<String> responseEntity = restTemplate.exchange( url, HttpMethod.GET, null, String.class );
        UserInfo user = null;
        //根据返回值进行后续操作
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {

            String sessionData = responseEntity.getBody();
            //解析从微信服务器获得的openid和session_key;
            Map<String, Object> result = JSONObject.parseObject( sessionData );
            //下面就可以写自己的业务代码了
            String openid = MapUtil.getStr( result, "openid" );
            //通过获取到的openid进行操作
            user = userService.getUserByOpenid( openid );
            //注册用户
            if (user == null) {
                user = new UserInfo();
                user.setHeadurl( MapUtil.getStr( data, "avatarUrl" ) );
                user.setNickname( MapUtil.getStr( data, "nickName" ) );
                user.setSex( "1".equals( MapUtil.getStr( data, "gender" ) ) ? "男" : "女" );
                user.setOpenid( openid );
                user.setCjcs( 0 );
                user.setXh( IdUtil.objectId() );
                user.setCjsj( new Date() );
                //被邀请的用户
                if(data.containsKey( "homexh" ) && StringUtils.isNotEmpty( MapUtil.getStr( data, "homexh" ) )){
                    user.setHomegroupxh( MapUtil.getStr( data, "homexh" ) );
                }else{
                    HomeInfo home = userService.saveDefultHome();
                    user.setHomegroupxh( home.getXh() );
                }
                userService.addUser( user );
            }

        }
        return user;
    }

    @Override
    public String getAccessToken(String openid) {
        //先从数据库里面查看是否存在有效的凭证
        List<AccessToken> list = accessTokenMapper.selectAll();
        AccessToken accessToken = null;
        if (CollectionUtil.isNotEmpty( list )) {
            accessToken = list.get( 0 );
        }
        String token = null;
        if (null == accessToken || accessToken.getExpiresIn().getTime() < new Date().getTime()) {
            String apiurl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

            apiurl = apiurl.replace( "APPID", APPID ).replace( "APPSECRET", APPSECRET );

            RestTemplate restTemplate = new RestTemplate();
            //进行网络请求,访问url接口
            ResponseEntity<String> responseEntity = restTemplate.exchange( apiurl, HttpMethod.GET, null, String.class );

            //根据返回值进行后续操作
            if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {

                String jsonData = responseEntity.getBody();
                //解析从微信服务器获得的openid和session_key;
                Map<String, Object> data = JSONObject.parseObject( jsonData );
                //下面就可以写自己的业务代码了
                if ("0".equals( MapUtil.getStr( data, "errcode" ) )) {
                    token = MapUtil.getStr( data, "access_token" );
                    if (null == accessToken) {
                        accessToken = new AccessToken();
                        accessToken.setXh( IdUtil.objectId() );
                        accessToken.setCjsj( new Date() );
                        accessToken.setAccessToken( token );
                        accessToken.setExpiresIn( DateAndTimeUtil.addDateMinut( Constants.ADD_HOURS ) );
                        if (accessTokenMapper.insert( accessToken ) < 0) {
                            LOGGER.error( "保存accessToken失败,accessToken:" + token );
                        } else {
                            LOGGER.info( "保存accessToken成功,accessToken:" + token );
                        }
                    }
                } else {
                    LOGGER.error( "获取AccessToken失败,错误码：" + MapUtil.getStr( data, "errcode" ) + ",错误消息：" + MapUtil.getStr( data, "errmsg" ) );
                    throw new RuntimeException( "获取AccessToken失败" );
                }
            } else {
                LOGGER.error( "获取AccessToken失败,错误码：" + responseEntity.getStatusCode() );
            }
        } else {
            token = accessToken.getAccessToken();
        }
        return token;
    }

    @Override
    public void sendTemplateMsg(String openid) {

    }
}