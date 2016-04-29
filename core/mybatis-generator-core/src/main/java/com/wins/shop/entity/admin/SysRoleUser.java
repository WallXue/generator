package com.wins.shop.entity.admin;

import java.io.Serializable;
import java.util.Date;

public class SysRoleUser implements Serializable {
    private Long rsuid;

    private Long rid;

    private Long suid;

    private Date createTime;

    private String rname;

    private SysRole role;

    private static final long serialVersionUID = 1L;

    public Long getRsuid() {
        return rsuid;
    }

    public void setRsuid(Long ruid) {
        this.rsuid = ruid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getSuid() {
        return suid;
    }

    public void setSuid(Long uid) {
        this.suid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

}