package com.family.memory.model;

import com.family.config.Constants;
import com.family.config.DateAndTimeUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.*;

@Table(name = "T_MEMORY_INFO")
public class MemoryInfo {
    @Id
    @Column(name = "XH")
    @GeneratedValue(generator = "JDBC")
    private String xh;

    /**
     * 记忆发生日期
     */
    @Column(name = "JYFSSJ")
    private Date jyfssj;

    /**
     * 记忆内容
     */
    @Column(name = "JYNR")
    private String jynr;

    @Column(name = "CJSJ")
    private Date cjsj;

    /**
     * 图片集  ，隔开
     */
    @Column(name = "IMGURLS")
    private String imgurls;

    /**
     * 发表人
     */
    @Column(name = "FBRXH")
    private String fbrxh;

    @Transient
    private  String nickname;

    @Transient
    private  String headurl;

    /**
     * @Description: 是否点赞
     * @Author: 杜飞龙
     * @Date: 2020/3/27
     * @param null:
     * @return: null
     **/
    @Transient
    private  String islike;
    /**
     * 排序号
     */
    @Column(name = "PXH")
    private Integer pxh;

    /**
     * @Description: 不是表字段 动态发表于几分钟 小时 前
     * @Author: 杜飞龙
     * @Date: 2020/3/27
     * @param null:
     * @return: null
     **/
    @Transient
    private String timestamp;

    /**
     * @Description: 不是表字段 动态合集 内容 + 图片  text /images
     * @Author: 杜飞龙
     * @Date: 2020/3/27
     * @param null:
     * @return: null
     **/
    @Transient
    private Map<String,Object> content;

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
     * 获取记忆发生日期
     *
     * @return JYFSSJ - 记忆发生日期
     */
    public Date getJyfssj() {
        return jyfssj;
    }

    /**
     * 设置记忆发生日期
     *
     * @param jyfssj 记忆发生日期
     */
    public void setJyfssj(Date jyfssj) {
        this.jyfssj = jyfssj;
    }

    /**
     * 获取记忆内容
     *
     * @return JYNR - 记忆内容
     */
    public String getJynr() {
        return jynr;
    }

    /**
     * 设置记忆内容
     *
     * @param jynr 记忆内容
     */
    public void setJynr(String jynr) {
        this.jynr = jynr;
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
     * 获取图片集  ，隔开
     *
     * @return IMGURLS - 图片集  ，隔开
     */
    public String getImgurls() {
        return imgurls;
    }

    /**
     * 设置图片集  ，隔开
     *
     * @param imgurls 图片集  ，隔开
     */
    public void setImgurls(String imgurls) {
        this.imgurls = imgurls;
    }

    /**
     * 获取发表人
     *
     * @return FBRXH - 发表人
     */
    public String getFbrxh() {
        return fbrxh;
    }

    /**
     * 设置发表人
     *
     * @param fbrxh 发表人
     */
    public void setFbrxh(String fbrxh) {
        this.fbrxh = fbrxh;
    }

    /**
     * 获取排序号
     *
     * @return PXH - 排序号
     */
    public Integer getPxh() {
        return pxh;
    }

    /**
     * 设置排序号
     *
     * @param pxh 排序号
     */
    public void setPxh(Integer pxh) {
        this.pxh = pxh;
    }


    public String getTimestamp() {
        return DateAndTimeUtil.getTimestampForMemory(this.cjsj,new Date());
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Map<String, Object> getContent() {
        Map<String, Object> content=new HashMap<>(  );
        content.put( "text",this.jynr );
        if(StringUtils.isNotEmpty( this.imgurls )){
            content.put( "images", Constants.setImgUrl(this.imgurls.split( "," )) );
        }else{
            content.put( "images",new String[] {} );
        }
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }
}