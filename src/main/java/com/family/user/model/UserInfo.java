package com.family.user.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "T_USER")
public class UserInfo {
    @Id
    @Column(name = "XH")
    @GeneratedValue(generator = "JDBC")
    private String xh;

    @Column(name = "OPENID")
    private String openid;

    /**
     * 昵称
     */
    @Column(name = "NICKNAME")
    private String nickname;

    /**
     * 头像
     */
    @Column(name = "HEADURL")
    private String headurl;

    /**
     * 生日
     */
    @Column(name = "BIRTHDAY")
    private String birthday;

    @Column(name = "MOBILE")
    private String mobile;

    /**
     * 抽奖次数
     */
    @Column(name = "CJCS")
    private Integer cjcs;


    @Column(name = "CJSJ")
    private Date cjsj;

    /**
     * 家庭组号
     */
    @Column(name = "HOMEGROUPXH")
    private String homegroupxh;

    @Column(name = "HOMEGROUPNAME")
    private String homegroupname;

    @Column(name = "SEX")
    private String sex;

    /**
     * 备注
     */
    @Column(name = "DSC")
    private String desc;

    @Column(name = "XGSJ")
    private Date   xgsj;

    /**
     * @return XH
     */
    public String getXh() {
        return xh;
    }

    /**
     * @param xh
     */
    public void setXh(String xh) {
        this.xh = xh;
    }

    /**
     * @return OPENID
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取昵称
     *
     * @return NICKNAME - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取头像
     *
     * @return HEADURL - 头像
     */
    public String getHeadurl() {
        return headurl;
    }

    /**
     * 设置头像
     *
     * @param headurl 头像
     */
    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    /**
     * 获取生日
     *
     * @return BIRTHDAY - 生日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置生日
     *
     * @param birthday 生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return MOBILE
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return CJCS
     */
    public Integer getCjcs() {
        return cjcs;
    }

    /**
     * @param cjcs
     */
    public void setCjcs(Integer cjcs) {
        this.cjcs = cjcs;
    }

    /**
     * 获取抽奖次数
     *
     * @return CJSJ - 抽奖次数
     */
    public Date getCjsj() {
        return cjsj;
    }

    /**
     * 设置抽奖次数
     *
     * @param cjsj 抽奖次数
     */
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    /**
     * 获取家庭组号
     *
     * @return HOMEGROUPXH - 家庭组号
     */
    public String getHomegroupxh() {
        return homegroupxh;
    }

    /**
     * 设置家庭组号
     *
     * @param homegroupxh 家庭组号
     */
    public void setHomegroupxh(String homegroupxh) {
        this.homegroupxh = homegroupxh;
    }

    /**
     * @return SEX
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取备注
     *
     * @return DESC - 备注
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置备注
     *
     * @param desc 备注
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @param xgsj
     */
    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    /**
     *
     * @return xgsj - 抽奖次数
     */
    public Date getXgsj() {
        return xgsj;
    }


    public String getHomegroupname() {
        return homegroupname;
    }

    public void setHomegroupname(String homegroupname) {
        this.homegroupname = homegroupname;
    }
}