package com.family.memory.model;

import cn.hutool.core.collection.CollectionUtil;
import com.family.config.Constants;
import com.family.config.DateAndTimeUtil;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "T_MEMORY_PL_INFO")
public class MemoryPLInfo {
    @Id
    @Column(name = "XH")
    @GeneratedValue(generator = "JDBC")
    private String xh;

    /**
     * 记忆序号
     */
    @Column(name = "MEMORYXH")
    private String memoryxh;

    /**
     * 记忆内容
     */
    @Column(name = "PLNR")
    private String plnr;

    @Column(name = "CJSJ")
    private Date cjsj;

    /**
     * @Description: 评论时间
     * @Author: 杜飞龙
     * @Date: 2020/3/27
     * @param null:
     * @return: null
     **/
    @Transient
    private String plsj;

    /**
     * @Description: 评论回复数量
     * @Author: 杜飞龙
     * @Date: 2020/3/27
     * @param null:
     * @return: null
     **/
    @Transient
    private Integer reviewnum;

    /**
     * @Description: 评论回复内容 最近5条
     * @Author: 杜飞龙
     * @Date: 2020/3/27
     * @param null:
     * @return: null
     **/
    @Transient
    private List<PlHfInfo> reviewless;

    /**
     * @Description: 评论回复内容 5条所有
     * @Author: 杜飞龙
     * @Date: 2020/3/27
     * @param null:
     * @return: null
     **/
    @Transient
    private List<PlHfInfo> childdata;

    /**
     * 评论人序号
     */
    @Column(name = "PLRXH")
    private String plrxh;

    @Transient
    private  String plrname;

    @Transient
    private  String headurl;

    /**
     * 排序号
     */
    @Column(name = "PXH")
    private String pxh;

    /**
     * 2评论 1点赞
     */
    @Column(name = "TYPE")
    private String type;

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
     * 获取记忆序号
     *
     * @return MEMORYXH - 记忆序号
     */
    public String getMemoryxh() {
        return memoryxh;
    }

    /**
     * 设置记忆序号
     *
     * @param memoryxh 记忆序号
     */
    public void setMemoryxh(String memoryxh) {
        this.memoryxh = memoryxh;
    }

    /**
     * 获取记忆内容
     *
     * @return PLNR - 记忆内容
     */
    public String getPlnr() {
        return plnr;
    }

    /**
     * 设置记忆内容
     *
     * @param plnr 记忆内容
     */
    public void setPlnr(String plnr) {
        this.plnr = plnr;
    }

    /**
     * @return CJSJ
     */
    public Date getCjsj() {
        return cjsj;
    }

    /**
     * @param cjsj
     */
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
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
     * 获取排序号
     *
     * @return PXH - 排序号
     */
    public String getPxh() {
        return pxh;
    }

    /**
     * 设置排序号
     *
     * @param pxh 排序号
     */
    public void setPxh(String pxh) {
        this.pxh = pxh;
    }

    /**
     * 获取1评论 2点赞
     *
     * @return TYPE - 1评论 2点赞
     */
    public String getType() {
        return type;
    }

    /**
     * 设置1评论 2点赞
     *
     * @param type 1评论 2点赞
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getPlsj() {
        return DateAndTimeUtil.dateFormatDay(this.cjsj);
    }

    public void setPlsj(String plsj) {
        this.plsj = plsj;
    }

    public Integer getReviewnum() {
        return reviewnum;
    }

    public void setReviewnum(Integer reviewnum) {
        this.reviewnum = reviewnum;
    }


    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getPlrname() {
        return plrname;
    }

    public void setPlrname(String plrname) {
        this.plrname = plrname;
    }

    public List<PlHfInfo> getReviewless() {
        if(CollectionUtil.isNotEmpty( childdata )){
            if(childdata.size()>5) {
                return childdata.subList( 0, 5 );
            }else{
                return childdata;
            }
        }
        return childdata;
    }

    public void setReviewless(List<PlHfInfo> reviewless) {
        this.reviewless = reviewless;
    }

    public List<PlHfInfo> getChilddata() {
        if(CollectionUtil.isNotEmpty( childdata )){
            childdata.stream().forEach( plHfInfo -> {
                if(plHfInfo.getPlrxh().equals( this.plrxh )){
                    plHfInfo.setHftype( Constants.PL_HF_TYPE_1 );
                }else{
                    plHfInfo.setHftype( Constants.PL_HF_TYPE_0 );
                }
            } );
        }
        return childdata;
    }

    public void setChilddata(List<PlHfInfo> childdata) {
        this.childdata = childdata;
    }
}