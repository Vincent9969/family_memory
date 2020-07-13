package com.family.img.model;

import com.family.config.Constants;

import java.util.Date;
import javax.persistence.*;

@Table(name = "T_IMG_GROUP_INFO")
public class ImgGroupInfo {
    @Id
    @Column(name = "XH")
    @GeneratedValue(generator = "JDBC")
    private String xh;

    @Column(name = "CJSJ")
    private Date cjsj;

    /**
     * 照片组名称
     */
    @Column(name = "ZPZMC")
    private String zpzmc;

    /**
     * 照片封面图
     */
    @Column(name = "ZPCFMIMGURL")
    private String zpcfmimgurl;

    /**
     * 创建人序号
     */
    @Column(name = "CJRXH")
    private String cjrxh;

    /**
     * 家庭组序号
     */
    @Column(name = "HOMEXH")
    private String homexh;

    /**
     * 排序号
     */
    @Column(name = "PXH")
    private Integer pxh;

    @Transient
    private Integer imgnum;


    /*
     * @Description: 下拉组件 label
     */
    @Transient
    private String label;

    /*
     * @Description: 下拉组件 value
     */
    @Transient
    private String value;

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
     * 获取照片组名称
     *
     * @return ZPZMC - 照片组名称
     */
    public String getZpzmc() {
        return zpzmc;
    }

    /**
     * 设置照片组名称
     *
     * @param zpzmc 照片组名称
     */
    public void setZpzmc(String zpzmc) {
        this.zpzmc = zpzmc;
    }

    /**
     * 获取照片封面图
     *
     * @return ZPCFMIMGURL - 照片封面图
     */
    public String getZpcfmimgurl() {
        return Constants.setImgUrl( zpcfmimgurl );
    }

    /**
     * 设置照片封面图
     *
     * @param zpcfmimgurl 照片封面图
     */
    public void setZpcfmimgurl(String zpcfmimgurl) {
        this.zpcfmimgurl = zpcfmimgurl;
    }

    /**
     * 获取创建人序号
     *
     * @return CJRXH - 创建人序号
     */
    public String getCjrxh() {
        return cjrxh;
    }

    /**
     * 设置创建人序号
     *
     * @param cjrxh 创建人序号
     */
    public void setCjrxh(String cjrxh) {
        this.cjrxh = cjrxh;
    }

    /**
     * 获取家庭组序号
     *
     * @return HOMEXH - 家庭组序号
     */
    public String getHomexh() {
        return homexh;
    }

    /**
     * 设置家庭组序号
     *
     * @param homexh 家庭组序号
     */
    public void setHomexh(String homexh) {
        this.homexh = homexh;
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

    public Integer getImgnum() {
        return imgnum;
    }

    public void setImgnum(Integer imgnum) {
        this.imgnum = imgnum;
    }

    public String getLabel() {
        return this.zpzmc;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.xh;
    }

    public void setValue(String value) {
        this.value = value;
    }
}