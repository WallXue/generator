package com.wins.shop.entity.admin;

import java.io.Serializable;
import java.util.Date;

public class SysRoleAuth implements Serializable {
    private Long raid;

    private Long rid;

    private Long aid;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getRaid() {
        return raid;
    }

    public void setRaid(Long raid) {
        this.raid = raid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}