package com.family.jh.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "T_JH_INFO")
public class JhInfo {
    @Id
    @Column(name = "XH")
    @GeneratedValue(generator = "JDBC")
    private String xh;

    @Column(name = "CJSJ")
    private Date cjsj;

    /**
     * 计划标题
     */
    @Column(name = "JHBT")
    private String jhbt;

    /**
     * 计划内容
     */
    @Column(name = "JHNR")
    private String jhnr;

    /**
     * 计划创建人
     */
    @Column(name = "CJRXH")
    private String cjrxh;

    /**
     * 是否实现 0没实现 1实现
     */
    @Column(name = "SJSX")
    private String sjsx;

    /**
     * 实现时间
     */
    @Column(name = "SXSJ")
    private Date sxsj;

    /**
     * 计划是否超过计划实现时间 0没超过 1超过
     */
    @Column(name = "JHSFYQ")
    private String jhsfyq;

    @Column(name = "DSC")
    private String desc;

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
     * 获取计划标题
     *
     * @return JHBT - 计划标题
     */
    public String getJhbt() {
        return jhbt;
    }

    /**
     * 设置计划标题
     *
     * @param jhbt 计划标题
     */
    public void setJhbt(String jhbt) {
        this.jhbt = jhbt;
    }

    /**
     * 获取计划内容
     *
     * @return JHNR - 计划内容
     */
    public String getJhnr() {
        return jhnr;
    }

    /**
     * 设置计划内容
     *
     * @param jhnr 计划内容
     */
    public void setJhnr(String jhnr) {
        this.jhnr = jhnr;
    }

    /**
     * 获取计划创建人
     *
     * @return CJRXH - 计划创建人
     */
    public String getCjrxh() {
        return cjrxh;
    }

    /**
     * 设置计划创建人
     *
     * @param cjrxh 计划创建人
     */
    public void setCjrxh(String cjrxh) {
        this.cjrxh = cjrxh;
    }

    /**
     * 获取是否实现 0没实现 1实现
     *
     * @return SJSX - 是否实现 0没实现 1实现
     */
    public String getSjsx() {
        return sjsx;
    }

    /**
     * 设置是否实现 0没实现 1实现
     *
     * @param sjsx 是否实现 0没实现 1实现
     */
    public void setSjsx(String sjsx) {
        this.sjsx = sjsx;
    }

    /**
     * 获取实现时间
     *
     * @return SXSJ - 实现时间
     */
    public Date getSxsj() {
        return sxsj;
    }

    /**
     * 设置实现时间
     *
     * @param sxsj 实现时间
     */
    public void setSxsj(Date sxsj) {
        this.sxsj = sxsj;
    }

    /**
     * 获取计划是否超过计划实现时间 0没超过 1超过
     *
     * @return JHSFYQ - 计划是否超过计划实现时间 0没超过 1超过
     */
    public String getJhsfyq() {
        return jhsfyq;
    }

    /**
     * 设置计划是否超过计划实现时间 0没超过 1超过
     *
     * @param jhsfyq 计划是否超过计划实现时间 0没超过 1超过
     */
    public void setJhsfyq(String jhsfyq) {
        this.jhsfyq = jhsfyq;
    }

    /**
     * @return DESC
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}