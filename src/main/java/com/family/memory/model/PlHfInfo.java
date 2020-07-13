package com.family.memory.model;

import com.family.config.DateAndTimeUtil;

import java.util.Date;
import javax.persistence.*;

@Table(name = "T_PL_HF_INFO")
public class PlHfInfo {
    @Id
    @Column(name = "XH")
    @GeneratedValue(generator = "JDBC")
    private String xh;

    /**
     * 动态序号
     */
    @Column(name = "MEMORYXH")
    private String memoryxh;

    /**
     * 评论序号（永远是一级评论）
     */
    @Column(name = "PLXH")
    private String plxh;

    /**
     * 评论人序号
     */
    @Column(name = "PLRXH")
    private String plrxh;

    /**
     * 回复人序号
     */
    @Column(name = "HFRXH")
    private String hfrxh;

    /**
     * 回复内容
     */
    @Column(name = "HFNR")
    private String hfnr;

    /**
     * 回复时间
     */
    @Column(name = "CJSJ")
    private Date cjsj;

    @Transient
    private String hfrname;

    /**
     * @Description: 评论人头像
     * @Author: 杜飞龙
     * @Date: 2020/3/28
     * @param null:
     * @return: null
     **/
    @Transient
    private String hfrheadurl;


    @Transient
    private String hfsj;

    /**
     * @Description: 回复类型 1回复最高级评论人 0回复其他评论人
     * @Author: 杜飞龙
     * @Date: 2020/3/28
     * @param null:
     * @return: null
     **/
    @Transient
    private String hftype;

    @Transient
    private String plrname;

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
     * 获取动态序号
     *
     * @return MEMORYXH - 动态序号
     */
    public String getMemoryxh() {
        return memoryxh;
    }

    /**
     * 设置动态序号
     *
     * @param memoryxh 动态序号
     */
    public void setMemoryxh(String memoryxh) {
        this.memoryxh = memoryxh;
    }

    /**
     * 获取评论序号（永远是一级评论）
     *
     * @return PLXH - 评论序号（永远是一级评论）
     */
    public String getPlxh() {
        return plxh;
    }

    /**
     * 设置评论序号（永远是一级评论）
     *
     * @param plxh 评论序号（永远是一级评论）
     */
    public void setPlxh(String plxh) {
        this.plxh = plxh;
    }

    /**
     * 获取评论人序号
     *
     * @return PLRXH - 评论人序号
     */
    public String getPlrxh() {
        return plrxh;
    }

    /**
     * 设置评论人序号
     *
     * @param plrxh 评论人序号
     */
    public void setPlrxh(String plrxh) {
        this.plrxh = plrxh;
    }

    /**
     * 获取回复人序号
     *
     * @return HFRXH - 回复人序号
     */
    public String getHfrxh() {
        return hfrxh;
    }

    /**
     * 设置回复人序号
     *
     * @param hfrxh 回复人序号
     */
    public void setHfrxh(String hfrxh) {
        this.hfrxh = hfrxh;
    }

    /**
     * 获取回复内容
     *
     * @return HFNR - 回复内容
     */
    public String getHfnr() {
        return hfnr;
    }

    /**
     * 设置回复内容
     *
     * @param hfnr 回复内容
     */
    public void setHfnr(String hfnr) {
        this.hfnr = hfnr;
    }

    /**
     * 获取回复时间
     *
     * @return CJSJ - 回复时间
     */
    public Date getCjsj() {
        return cjsj;
    }

    /**
     * 设置回复时间
     *
     * @param cjsj 回复时间
     */
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public String getHfrname() {
        return hfrname;
    }

    public void setHfrname(String hfrname) {
        this.hfrname = hfrname;
    }

    public String getPlrname() {
        return plrname;
    }

    public void setPlrname(String plrname) {
        this.plrname = plrname;
    }

    public String getHfrheadurl() {
        return hfrheadurl;
    }

    public void setHfrheadurl(String hfrheadurl) {
        this.hfrheadurl = hfrheadurl;
    }

    public String getHfsj() {
        return DateAndTimeUtil.dateFormatDay( this.cjsj );
    }

    public void setHfrsj(String hfrsj) {
        this.hfsj = hfrsj;
    }

    public String getHftype() {
        return hftype;
    }

    public void setHftype(String hftype) {
        this.hftype = hftype;
    }
}