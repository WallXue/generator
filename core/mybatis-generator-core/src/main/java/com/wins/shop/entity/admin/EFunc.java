/*
 * file comment: EFunc.java
 * Copyright(C) All rights reserved.
 */
package com.wins.shop.entity.admin;
import java.io.Serializable;
import java.util.Date;

public class EFunc implements Serializable {

    /**
     * field comment: 权限类目id
     */
    private Long funcId;

    /**
     * field comment: 权限类目名称
     */
    private String funcName;

    /**
     * field comment: 类目级别 0-顶部导航菜单；1-左侧一级菜单；2-左侧二级菜单
     */
    private Integer funcLevel;

    /**
     * field comment: 父类目id. 0表示公用或者无父类目
     */
    private Long parentFuncId;

    /**
     * field comment: 排序
     */
    private Integer sortId;

    /**
     * field comment: 页面URL
     */
    private String funcUrl;

    /**
     * field comment: url的类型：0-静态页面， 1-ajax请求， 2-动态页面
     */
    private Byte urlType;

    /**
     * field comment: 类目创建时间
     */
    private Date createTime;

    /**
     * field comment: 排序
     */
    private Byte status;

    /**
     * field comment: 类目创建时间
     */
    private Date statusTime;

    private static final long serialVersionUID = 1L;

    public Long getFuncId() {
        return funcId;
    }

    public void setFuncId(Long funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName == null ? null : funcName.trim();
    }

    public Integer getFuncLevel() {
        return funcLevel;
    }

    public void setFuncLevel(Integer funcLevel) {
        this.funcLevel = funcLevel;
    }

    public Long getParentFuncId() {
        return parentFuncId;
    }

    public void setParentFuncId(Long parentFuncId) {
        this.parentFuncId = parentFuncId;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getFuncUrl() {
        return funcUrl;
    }

    public void setFuncUrl(String funcUrl) {
        this.funcUrl = funcUrl == null ? null : funcUrl.trim();
    }

    public Byte getUrlType() {
        return urlType;
    }

    public void setUrlType(Byte urlType) {
        this.urlType = urlType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }
}
