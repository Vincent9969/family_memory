package com.family.login.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "T_ACCESS_TOKEN")
public class AccessToken {
    @Id
    @Column(name = "XH")
    @GeneratedValue(generator = "JDBC")
    private String xh;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    /**
     * 有效时间
     */
    @Column(name = "EXPIRES_IN")
    private Date expiresIn;

    /**
     * 创建时间
     */
    @Column(name = "CJSJ")
    private Date cjsj;

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
     * @return ACCESS_TOKEN
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * 获取有效时间
     *
     * @return EXPIRES_IN - 有效时间
     */
    public Date getExpiresIn() {
        return expiresIn;
    }

    /**
     * 设置有效时间
     *
     * @param expiresIn 有效时间
     */
    public void setExpiresIn(Date expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * 获取创建时间
     *
     * @return CJSJ - 创建时间
     */
    public Date getCjsj() {
        return cjsj;
    }

    /**
     * 设置创建时间
     *
     * @param cjsj 创建时间
     */
    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }
}