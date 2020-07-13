package com.family.user.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "T_HOME_INFO")
public class HomeInfo {
    @Id
    @Column(name = "XH")
    @GeneratedValue(generator = "JDBC")
    private String xh;

    @Column(name = "HONENAME")
    private String honename;

    @Column(name = "CJSJ")
    private Date cjsj;

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
     * @return HONENAME
     */
    public String getHonename() {
        return honename;
    }

    /**
     * @param honename
     */
    public void setHonename(String honename) {
        this.honename = honename;
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