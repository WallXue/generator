package com.wins.shop.entity.admin;
import java.io.Serializable;
import java.util.Date;

public class EFunc implements Serializable {

    private Long funcId;

    private String funcName;

    private Integer funcLevel;

    private Long parentFuncId;

    private Integer sortId;

    private String funcUrl;

    private Byte urlType;

    private Date createTime;

    private Byte status;

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
