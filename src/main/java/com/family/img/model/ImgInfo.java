package com.family.img.model;

import com.family.config.Constants;

import java.util.Date;
import javax.persistence.*;

@Table(name = "T_IMG_INFO")
public class ImgInfo {
    @Id
    @Column(name = "XH")
    @GeneratedValue(generator = "JDBC")
    private String xh;

    /**
     * 照片组序号
     */
    @Column(name = "ZPZXH")
    private String zpzxh;

    /**
     * 图片描述
     */
    @Column(name = "IMGDESC")
    private String imgdesc;

    /**
     * 图片url
     */
    @Column(name = "IMGURL")
    private String imgurl;

    @Column(name = "CJSJ")
    private Date cjsj;

    @Column(name = "PXH")
    private Integer pxh;

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
     * 获取照片组序号
     *
     * @return ZPZXH - 照片组序号
     */
    public String getZpzxh() {
        return zpzxh;
    }

    /**
     * 设置照片组序号
     *
     * @param zpzxh 照片组序号
     */
    public void setZpzxh(String zpzxh) {
        this.zpzxh = zpzxh;
    }

    /**
     * 获取图片描述
     *
     * @return IMGDESC - 图片描述
     */
    public String getImgdesc() {
        return imgdesc;
    }

    /**
     * 设置图片描述
     *
     * @param imgdesc 图片描述
     */
    public void setImgdesc(String imgdesc) {
        this.imgdesc = imgdesc;
    }

    /**
     * 获取图片url
     *
     * @return IMGURL - 图片url
     */
    public String getImgurl() {
        return Constants.setImgUrl( imgurl );
    }

    /**
     * 设置图片url
     *
     * @param imgurl 图片url
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
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
     * @return PXH
     */
    public Integer getPxh() {
        return pxh;
    }

    /**
     * @param pxh
     */
    public void setPxh(Integer pxh) {
        this.pxh = pxh;
    }
}