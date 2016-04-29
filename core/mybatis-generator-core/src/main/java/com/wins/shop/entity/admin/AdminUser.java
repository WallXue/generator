package com.wins.shop.entity.admin;

import java.io.Serializable;
import java.util.Date;

public class AdminUser implements Serializable {
    private Long suid;

    private String suname;

    private String saccount;

    private String passwd;

    private Date createTime;

    private Byte state;

    private Date lastUpdate;

    private Long modifier;

    private String rname;

    private static final long serialVersionUID = 1L;

    public Long getSuid() {
        return suid;
    }

    public void setSuid(Long suid) {
        this.suid = suid;
    }

    public String getSuname() {
        return suname;
    }

    public void setSuname(String suname) {
        this.suname = suname == null ? null : suname.trim();
    }

    public String getSaccount() {
        return saccount;
    }

    public void setSaccount(String saccount) {
        this.saccount = saccount == null ? null : saccount.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getModifier() {
        return modifier;
    }

    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }
}