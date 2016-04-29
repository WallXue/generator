/*
 * file comment: SysRole.java
 * Copyright(C) All rights reserved.
 * 2016-04-29 Created
 */
package com.wins.shop.entity.admin;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysRole implements Serializable {

    /**
     * field comment: 角色ID
     */
    private Long rid;

    /**
     * field comment: 角色名称
     */
    private String rname;

    /**
     * field comment: 角色描述
     */
    private String rdesc;

    /**
     * field comment: 角色创建时间
     */
    private Date createTime;

    /**
     * field comment: 状态，1、可用；2、冻结
     */
    private Byte state;

    /**
     * field comment: 角色对应的权限名称(以逗号分隔)
     */
    private String authPath;

    private Date lastUpdate;

    private Long modifier;

    private static final long serialVersionUID = 1L;

    //用于显示
    private List<SysRoleAuth> sraList;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc == null ? null : rdesc.trim();
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

    public String getAuthPath() {
        return authPath;
    }

    public void setAuthPath(String authPath) {
        this.authPath = authPath == null ? null : authPath.trim();
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

    public List<SysRoleAuth> getSraList() {
        return sraList;
    }

    public void setSraList(List<SysRoleAuth> sraList) {
        this.sraList = sraList;
    }
}
